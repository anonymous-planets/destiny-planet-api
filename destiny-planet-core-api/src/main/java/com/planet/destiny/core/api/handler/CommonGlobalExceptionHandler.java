package com.planet.destiny.core.api.handler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.planet.destiny.core.api.constant.ErrorCode;
import com.planet.destiny.core.api.exception.BadRequestException;
import com.planet.destiny.core.api.exception.BusinessException;
import com.planet.destiny.core.api.items.wrapper.response.ErrorSet;
import com.planet.destiny.core.api.items.wrapper.response.RestEmptyResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
                                .name(e.getClass().getSimpleName())
                                .serviceName(serviceName)
                                .path(request.getRequestURI())
                                .build()
                )
        );
    }

    // TODO : 예외처리 할 방법 생각하기
    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<RestEmptyResponse> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        log.error("[ handleConstraintViolationException ] ConstraintViolationException : {}", e);
        return ResponseEntity.status(ErrorCode.BAD_REQUEST.getStatus()).body(
                RestEmptyResponse.error(
                        ErrorSet.builder()
                                .errorCode(ErrorCode.BAD_REQUEST)
                                .serviceName(serviceName)
                                .path(request.getRequestURI())
                                .build()
                )
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<RestEmptyResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("[ MethodArgumentNotValidException ] MethodArgumentNotValidException : {}", e);

        JsonArray errorFieldArray = new JsonArray();
        for(FieldError fieldError : e.getFieldErrors()) {
            JsonObject fieldErrorObj = new JsonObject();
            fieldErrorObj.addProperty("Field", fieldError.getField());
            fieldErrorObj.addProperty("Value", String.valueOf(fieldError.getRejectedValue()));
            fieldErrorObj.addProperty("ErrorMessage", fieldError.getDefaultMessage());

            errorFieldArray.add(fieldErrorObj);
        }
        Gson gson = new Gson();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                RestEmptyResponse.error(
                        ErrorSet.builder()
                                .errorCode(ErrorCode.BAD_REQUEST)
                                .name(e.getClass().getSimpleName())
                                .title("Method Argument Not Valid Exception")
                                .message(gson.toJson(errorFieldArray))
                                .alertMessage("")
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
                                .name(e.getClass().getSimpleName())
                                .title(e.getTitle())
                                .message(e.getMessage())
                                .alertMessage(e.getAlertMessage())
                                .serviceName(serviceName)
                                .path(request.getRequestURI())
                                .build()
                )
            );
    }

    @ExceptionHandler(value = BadRequestException.class)
    protected ResponseEntity<RestEmptyResponse> handleBadRequestException(BadRequestException e, HttpServletRequest request) {
        log.error("[ handleBadRequestException ] BadRequestException : {}", e);
        return ResponseEntity.status(e.getErrorCode().getStatus()).body(
                RestEmptyResponse.error(
                        ErrorSet.builder()
                                .errorCode(e.getErrorCode())
                                .name(e.getClass().getSimpleName())
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
