package com.planet.destiny.core.api.utils;


import com.planet.destiny.core.api.constant.member.AdminRoleType;
import com.planet.destiny.core.api.module.token.item.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component(value = "jwtTokenProvider")
public class JwtTokenProvider {

    public static final String AUTHORITIES_KEY = "role";
    public static final String GRANT_TYPE = "Bearer ";

    public static final Long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30L;

    public static final Long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 30L;

    private final Key key;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto.TokenRes createAccessToken(TokenDto.TokenIssueReq reqDto, TokenDto.TokenRes token) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(reqDto.getMemberIdx()));

        claims.put(AUTHORITIES_KEY, reqDto.getRoles().stream().map(item -> item.getName()).collect(Collectors.joining(",")));

        Date expiration = new Date(reqDto.getCurrentDateTime() + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(reqDto.getCurrentDateTime()))     // 발행 일자
                .setExpiration(expiration)      // 만료 일자
                .signWith(key, SignatureAlgorithm.HS512)    // 알고리즘
                .compact()
                ;

        return token.setAccessTokenInfo(accessToken, expiration.getTime(), ACCESS_TOKEN_EXPIRE_TIME);
    }

    public TokenDto.TokenRes createRefreshToken(long now, TokenDto.TokenRes token) {
        Claims claims = Jwts.claims();

        Date expiration = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(now))
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
        return token.setRefreshTokenInfo(refreshToken, expiration.getTime(), REFRESH_TOKEN_EXPIRE_TIME);
    }
}
