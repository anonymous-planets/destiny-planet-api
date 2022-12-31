package com.planet.destiny.core.api.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planet.destiny.core.api.constant.ErrorCode;
import com.planet.destiny.core.api.items.wrapper.response.ErrorSet;
import com.planet.destiny.core.api.items.wrapper.response.RestEmptyResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Value("${spring.application.name}")
    private String serviceName;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(ErrorCode.UNAUTHORIZED.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        try(OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os,
                    RestEmptyResponse.error(
                            ErrorSet.builder()
                                    .errorCode(ErrorCode.UNAUTHORIZED)
                                    .serviceName(serviceName)
                                    .title("인증 정보가 없습니다.")
                                    .message("인증 정보가 없습니다. 다시 로그인 필요합니다.")
                                    .alertMessage("로그인 정보가 없습니다. 다시 로그인 해주세요.")
                                    .path(request.getRequestURI())
                                    .build()
                    )
            );
            os.flush();
        }
    }
}
