package com.planet.destiny.gateway.server.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.planet.destiny.gateway.server.item.SimpleRestResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final String AUTHORITIES_KEY = "role";
    private final Key key;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * AccessToken Validation
     * @param token
     * @return
     */
    public Map<String, String> validationToken(String token) {
        Map<String, String> errorMap = new HashMap<>();
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return null;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다.");
            errorMap.put("name", e.getClass().getSimpleName());
            errorMap.put("code", "C002");
            errorMap.put("title", "[Gateway] 해당 토큰은 잘못된 토큰입니다.");
            errorMap.put("message", "잘못된 JWT 서명입니다");
            errorMap.put("alertMessage", "정보가 만료 되었습니다. 다시 로그인 해주세요.");
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다.");
            errorMap.put("name", e.getClass().getSimpleName());
            errorMap.put("code", "C003");
            errorMap.put("title", "[Gateway] 만료된 JWT 토큰입니다.");
            errorMap.put("message", "토큰이 만료 되었습니다.");
            errorMap.put("alertMessage", "정보가 만료 되었습니다. 다시 로그인 해주세요.");
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다.");
            errorMap.put("name", e.getClass().getSimpleName());
            errorMap.put("code", "C005");
            errorMap.put("title", "[Gateway] 지원 되지 않는 JWT 토큰입니다.");
            errorMap.put("message", "해당 토큰은 지원되지 않는 JWT 토큰입니다.");
            errorMap.put("alertMessage", "정보가 만료 되었습니다. 다시 로그인 해주세요.");
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못되었습니다.");
            errorMap.put("name", e.getClass().getSimpleName());
            errorMap.put("code", "C004");
            errorMap.put("title", "[Gateway] JWT 토큰이 잘못되었습니다.");
            errorMap.put("message", "해당 토큰은 잘못된 JWT 서명입니다.");
            errorMap.put("alertMessage", "정보가 만료 되었습니다. 다시 로그인 해주세요.");
        }
        return errorMap;
    }

    public Mono<Void> onError(ServerHttpResponse response, String message, SimpleRestResponse.ErrorSet error, HttpStatus status) {
        // StatusCode값 설정
        response.setStatusCode(status);
        // Content-Type 설정
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        DataBuffer buffer = null;
        try{
            buffer = response.bufferFactory().wrap(new ObjectMapper().writeValueAsBytes(SimpleRestResponse.error(message, error)));
            return response.writeWith(Mono.just(buffer));
        }catch(Exception e) {
            e.printStackTrace();
            return response.writeWith(Mono.just(buffer));
        }
    }

    private byte[] defaultError() throws IOException {
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(SimpleRestResponse.error("", null));
            return bos.toByteArray();
        }
    }

    /**
     * Claims에서 MemberIdx 추출
     * @param accessToken
     * @return
     */
    public String getMemberIdx(String accessToken) {
        return this.parseClaims(accessToken).getSubject();
    }

    /**
     * Claims에서 Role 추출
     * @param accessToken
     * @return
     */
    public String getRole(String accessToken) {
        // fixme: AUTHORITIES_KEY 값이 존재 하지 않을시 toString과정에서 NullPointerException 발생
        return this.parseClaims(accessToken).get(AUTHORITIES_KEY).toString();
    }


    /**
     * AccessToken에서 Body 추출
     * @param accessToken
     * @return
     */
    private Claims parseClaims(String accessToken) {
        try{
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
