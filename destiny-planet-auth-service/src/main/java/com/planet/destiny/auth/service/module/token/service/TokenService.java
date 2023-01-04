package com.planet.destiny.auth.service.module.token.service;


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

    public TokenDto.TokenRes adminTokenIssue() {
        return null;
    }

    public TokenDto.TokenRes adminTokenReissue(TokenDto.TokenReIssueReq reqDto) {
        return null;
    }
}
