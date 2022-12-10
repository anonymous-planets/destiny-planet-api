package com.planet.destiny.auth.service.constant;

import com.planet.destiny.core.api.constant.ErrorCodeType;
import org.springframework.http.HttpStatus;

public enum ErrorCodeAuth implements ErrorCodeType {
    TOKEN_NOT_EXISTS(HttpStatus.BAD_REQUEST, "AUTH_001", "토큰이 존재 하지 않습니다.", "다시 로그인해주세요.", "")
    , TOKEN_ILLEGAL(HttpStatus.BAD_REQUEST, "AUTH_002", "해당 토큰은 잘못 된 토큰입니다.", "다시 로그인해주세요.", "")

    ;

    private final HttpStatus status;

    private final String code;

    private final String title;

    private final String message;

    private final String alertMessage;

    ErrorCodeAuth(final HttpStatus status, final String code, final String title, final String message, final String alertMessage) {
        this.status = status;
        this.code = code;
        this.title = title;
        this.message = message;
        this.alertMessage = alertMessage;
    }


    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getAlertMessage() {
        return this.alertMessage;
    }
}
