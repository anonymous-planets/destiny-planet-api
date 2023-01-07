package com.planet.destiny.auth.service.module.token.service;


import com.planet.destiny.auth.service.constant.ErrorCodeAuth;
import com.planet.destiny.auth.service.exception.member.MemberNotFoundException;
import com.planet.destiny.auth.service.module.member.model.AdminMemberEntity;
import com.planet.destiny.auth.service.module.member.repository.AdminMemberRepository;
import com.planet.destiny.auth.service.module.token.model.AdminTokenEntity;
import com.planet.destiny.auth.service.module.token.repository.AdminTokenRepository;
import com.planet.destiny.core.api.module.token.item.TokenDto;
import com.planet.destiny.core.api.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service(value = "tokenService")
@RequiredArgsConstructor
public class TokenService {

    private final JwtTokenProvider jwtTokenProvider;

    private final AdminTokenRepository adminTokenRepository;

    private final AdminMemberRepository adminMemberRepository;


    public TokenDto.TokenRes adminTokenIssue(TokenDto.TokenIssueReq reqDto) {
        TokenDto.TokenRes token = TokenDto.TokenRes.builder().build();
        // 1. 토큰 생성
        // AccessToken 생성
        jwtTokenProvider.createAccessToken(reqDto, token);

        // RefreshToken 생성
        jwtTokenProvider.createRefreshToken(reqDto, token);

        // DB에 저장
        AdminMemberEntity admin = adminMemberRepository.findByIdx(reqDto.getMemberIdx()).orElseThrow(() -> new MemberNotFoundException());
        AdminTokenEntity tokenEntity = adminTokenRepository.findByAdmin(admin).orElse(AdminTokenEntity.createToken(admin, token)).updateToken(admin, token);
        adminTokenRepository.save(tokenEntity);
        return token;
    }

    public TokenDto.TokenRes adminTokenReissue(TokenDto.TokenReIssueReq reqDto) {
        // 1. refresh토큰 DB조회
        return null;
    }
}
