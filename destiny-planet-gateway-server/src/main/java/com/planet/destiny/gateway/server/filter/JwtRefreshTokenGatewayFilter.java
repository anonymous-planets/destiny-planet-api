package com.planet.destiny.gateway.server.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.planet.destiny.gateway.server.item.SimpleRestResponse;
import com.planet.destiny.gateway.server.util.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpAsyncRequestControl;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * refresh token 검증 Filter
 */

@Slf4j
@Component
public class JwtRefreshTokenGatewayFilter extends AbstractGatewayFilterFactory<JwtRefreshTokenGatewayFilter.Config> {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtRefreshTokenGatewayFilter(JwtTokenProvider jwtTokenProvider) {
        super(Config.class);
        this.jwtTokenProvider = jwtTokenProvider;
    }



    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // refreshToken 추출
            String refreshToken = String.valueOf(request.getCookies().get("refreshToken"));

            // refreshToken 존재 여부 확인
            if(refreshToken == null) {
                return jwtTokenProvider.onError(
                        response
                        , "실패"
                        , SimpleRestResponse.ErrorSet.builder()
                                        .name("Not Found Token")
                                        .code("C001")
                                        .title("[Gateway] Refresh Token 정보가 존재 하지 않습니다.")
                                        .message("Cookie에 Refresh Token값이 없습니다.")
                                        .alertMessage("정보가 만료 되었습니다. 다시 로그인 해주세요.")
                                .path(request.getPath().value())
                                .build()
                        , HttpStatus.BAD_REQUEST
                );
            }

            // refreshToken 검증
            Map<String, String> errorMap = jwtTokenProvider.validationToken(refreshToken);
            if(errorMap != null) {
                return jwtTokenProvider.onError(
                        response
                        , errorMap.get("message")
                        , SimpleRestResponse.ErrorSet.builder()
                                .name(errorMap.get("name"))
                                .code(errorMap.get("code"))
                                .title(errorMap.get("title"))
                                .message(errorMap.get("message"))
                                .alertMessage(errorMap.get("alertMessage"))
                                .path(request.getPath().value())
                                .build()
                        , HttpStatus.BAD_REQUEST
                );
            }

            return chain.filter(exchange);
        });
    }

    public static class Config {

    }

}
