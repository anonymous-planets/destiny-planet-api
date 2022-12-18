package com.planet.destiny.gateway.server.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planet.destiny.gateway.server.item.SimpleRestResponse;
import com.planet.destiny.gateway.server.util.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;


@Slf4j
@Component
public class JwtAuthenticationGatewayFilter extends AbstractGatewayFilterFactory<JwtAuthenticationGatewayFilter.Config> {

    private final JwtTokenProvider jwtTokenProvider;

    public static final String BEARER_PREFIX = "Bearer ";

    public JwtAuthenticationGatewayFilter(JwtTokenProvider jwtTokenProvider) {
        super(Config.class);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            /**
             * Request Header에 Authorization 존재 여부 확인
             */
            if(!containsAuthorization(request)) {
                return jwtTokenProvider.onError(
                        response
                        , "실패"
                        , SimpleRestResponse.ErrorSet.builder()
                                        .name("Bad Request")
                                        .code("C001")
                                        .title("[Gateway] Header정보에 Authorization필드가 존재 하지 않습니다.")
                                        .message("Header정보에 Authorization 필드가 존재 하지 않습니다.")
                                        .alertMessage("정보가 만료 되었습니다. 다시 로그인 해주세요.")
                                .path(request.getPath().value())
                                .build()
                        , HttpStatus.BAD_REQUEST
                        );
            }

            /**
             * Request Header에 AccessToken 존재 여부 확인
             */
            String accessToken = extractAccessToken(request);
            if(!StringUtils.hasText(accessToken)) {
                return jwtTokenProvider.onError(
                        response
                        , "실패"
                        , SimpleRestResponse.ErrorSet.builder()
                                        .name("Bad Request")
                                        .code("C001")
                                        .title("[Gateway] Header정보에 있는 Authorization필드에 AccessToken 값이 존재 하지 않습니다.")
                                        .message("Header에 AccessToken이 존재 하지 않습니다.")
                                        .alertMessage("정보가 만료 되었습니다. 다시 로그인 해주세요.")
                                .path(request.getPath().value())
                                .build()
                        , HttpStatus.UNAUTHORIZED
                        );
            }

            /**
             * AccessToken 정보 Validation
             */
            Map<String, String> errorMap = jwtTokenProvider.validationToken(accessToken);
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

            /**
             * Header에 memberIdx, Role 정보 추가
             */
            addAuthorizationHeaders(request, jwtTokenProvider.getMemberIdx(accessToken), jwtTokenProvider.getRole(accessToken));
            // 요청
            return chain.filter(exchange);
        });
    }


    /**
     * Header에 Authorization 필드 있는지 확인
     * @param request
     * @return
     */
    private boolean containsAuthorization(ServerHttpRequest request) {
        return request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }


    /**
     * Header에 AccessToken값 추출
     * @param request
     * @return
     */
    private String extractAccessToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return "";
    }

    /**
     * 타 서비스로 보낼 Request Header에 MemberId, Role 추가
     * @param request
     * @param memberId
     * @param role
     */
    private void addAuthorizationHeaders(ServerHttpRequest request, String memberId, String role) {
        request.mutate()
                .header("X-Authorization-Id", memberId)
                .header("X-Authorization-Role", role)
                .build()
        ;
    }

    public static class Config {

    }
}
