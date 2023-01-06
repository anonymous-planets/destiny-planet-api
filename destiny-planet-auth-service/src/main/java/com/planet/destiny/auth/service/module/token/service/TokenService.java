package com.planet.destiny.auth.service.module.token.service;


import com.planet.destiny.auth.service.module.token.model.AdminTokenEntity;
import com.planet.destiny.auth.service.module.token.repository.AdminTokenRepository;
import com.planet.destiny.core.api.module.token.item.TokenDto;
import com.planet.destiny.core.api.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "tokenService")
@RequiredArgsConstructor
public class TokenService {

    private final JwtTokenProvider jwtTokenProvider;

    private final AdminTokenRepository adminTokenRepository;


    public TokenDto.TokenRes adminTokenIssue(TokenDto.TokenIssueReq reqDto) {
        TokenDto.TokenRes token = TokenDto.TokenRes.builder().build();
        // 1. 토큰 생성
        jwtTokenProvider.createAccessToken(reqDto, token);
        return token;
    }

    public TokenDto.TokenRes adminTokenReissue(TokenDto.TokenReIssueReq reqDto) {
        // 1. refresh토큰 DB조회
        return null;
    }
}
