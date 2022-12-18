package com.planet.destiny.auth.service.constant;

import com.planet.destiny.core.api.constant.ErrorCodeType;
import org.springframework.http.HttpStatus;

public enum ErrorCodeAuth implements ErrorCodeType {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "C001", "잘못된 요청입니다.", "잘못된 요청입니다, API주소, Reqest데이터를 확인해주세요.", "문제가 발생했습니다. 잠시 후 다시 시도해주세요.")
    , TOKEN_ERROR(HttpStatus.BAD_REQUEST, "C0001", "토큰이 잘못되었습니다.", "잘못된 토큰입니다. 다시 로그인 요청 해주세요.", "문제가 발생했습니다. 다시 로그인 해주세요.")
    , TOKEN_NOT_EXISTS(HttpStatus.BAD_REQUEST, "C002", "토큰이 존재 하지 않습니다.", "토큰이 존재 하지 않습니다. 다시 로그인 요청 해주세요.", "문제가 발생했습니다. 다시 로그인해주세요.")
    , TOKEN_ILLEGAL(HttpStatus.BAD_REQUEST, "C003", "해당 토큰은 잘못 된 토큰입니다.", "잘못된 토큰입니다. 다시 로그인 요청 해주세요.", "문제가 발생했습니다. 다시 로그인해주세요.")
    , TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "C004", "해당 토큰은 만료된 토큰입니다.", "해당 토큰은 만료되었습니다. 토큰 재발행 또는 다시 로그인 요청해주세요.", "문제가 발생했습니다. 다시 로그인해주세요.")
    , TOKEN_MALFORMED(HttpStatus.BAD_REQUEST, "C005", "해당 토큰은 잘못된 JWT 서명입니다.", "해동 토큰은 잘못된 유효하지 않은 토큰입니다. 토큰 재발행 또는 다시 로그인 요청해주세요.", "문제가 발생했습니다. 다시 로그인해주세요.")
    , TOKEN_UNSUPPORTED(HttpStatus.BAD_REQUEST, "C006", "해당 토큰은 지원 되지 않는 JWT 토큰입니다.", "해당 토큰은 잘못된 유효하지 않은 토큰입니다. 토큰 재발행 또는 다시 로그인 요청해주세요.", "문제가 발생했습니다. 다시 로그인해주세요.")

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
