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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Value("${spring.application.name}")
    private String serviceName;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 필요한 권한이 없이 접근하려 할때 403
        response.setStatus(ErrorCode.FORBIDDEN.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        try(OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os,
                    RestEmptyResponse.error(
                            ErrorSet.builder()
                                    .errorCode(ErrorCode.FORBIDDEN)
                                    .serviceName(serviceName)
                                    .title("접근 권한이 없습니다.")
                                    .message("해당 사용자는 접근 권한이 업는 사용자 압니다.")
                                    .alertMessage("접근이 불가능합니다.")
                                    .path(request.getRequestURI())
                                    .build()
                    )

            );
            os.flush();
        }
    }
}
