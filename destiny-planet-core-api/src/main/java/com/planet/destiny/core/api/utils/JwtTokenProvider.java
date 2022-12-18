package com.planet.destiny.core.api.utils;


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

@Slf4j
@Component(value = "jwtTokenProvider")
public class JwtTokenProvider {

    public static final String AUTHORITIES_KEY = "role";
    public static final String GRANT_TYPE = "Bearer";

    public static final Long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30L;

    public static final Long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 30L;

    private final Key key;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto.TokenRes createAccessToken(int memberIdx, long now, List<String> roles, TokenDto.TokenRes token) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(memberIdx));

//        claims.put(AUTHORITIES_KEY, roles.stream().collect(Collectors.joining(",")));

        Date expiration = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(now))     // 발행 일자
                .setExpiration(expiration)      // 만료 일자
                .signWith(key, SignatureAlgorithm.HS512)    // 알고리즘
                .compact()
                ;

        return token.setAccessToken(accessToken, expiration.getTime());
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
        return token.setRefreshToken(refreshToken, expiration.getTime());
    }
}
