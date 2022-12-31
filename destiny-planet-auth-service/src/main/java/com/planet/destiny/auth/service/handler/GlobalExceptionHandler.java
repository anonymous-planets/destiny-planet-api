package com.planet.destiny.auth.service.handler;

import com.planet.destiny.auth.service.exception.token.TokenException;
import com.planet.destiny.core.api.constant.ErrorCode;
import com.planet.destiny.core.api.items.wrapper.response.ErrorSet;
import com.planet.destiny.core.api.items.wrapper.response.RestEmptyResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.application.name}")
    private String serviceName;

    @ExceptionHandler(value = TokenException.class)
    protected ResponseEntity<RestEmptyResponse> handleTokenException(TokenException e, HttpServletRequest request) {
        log.error("[ handleTokenException ] e : {}", e);
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus()).body(
            RestEmptyResponse.error(
                    ErrorSet.builder()
                            .errorCode(e.getErrorCode())
                            .title(e.getTitle())
                            .message(e.getMessage())
                            .alertMessage(e.getAlertMessage())
                            .serviceName(serviceName)
                            .path(request.getRequestURI())
                            .build()
            )
        );
    }
}
