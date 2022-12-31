package com.planet.destiny.core.api.handler;

import com.planet.destiny.core.api.constant.ErrorCode;
import com.planet.destiny.core.api.exception.BusinessException;
import com.planet.destiny.core.api.items.wrapper.response.ErrorSet;
import com.planet.destiny.core.api.items.wrapper.response.RestEmptyResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonGlobalExceptionHandler {

    @Value("${spring.application.name}")
    private String serviceName;

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<RestEmptyResponse> handleException(Exception e, HttpServletRequest request) {
        log.error("[ handleException ] Exception : {}", e);
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus()).body(
                RestEmptyResponse.error(
                        ErrorSet.builder()
                                .errorCode(ErrorCode.INTERNAL_SERVER_ERROR)
                                .serviceName(serviceName)
                                .path(request.getRequestURI())
                                .build()
                )
        );
    }

    @ExceptionHandler(value = BusinessException.class)
    protected ResponseEntity<RestEmptyResponse> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error("[ handleBusinessException ] BusinessException : {}", e);
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(
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
