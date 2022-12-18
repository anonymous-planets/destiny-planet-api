package com.planet.destiny.auth.service.exception.token;

import com.planet.destiny.auth.service.constant.ErrorCodeAuth;

public class TokenExpiredException extends TokenException {
    public TokenExpiredException(String title, String message, String alertMessage) {
        super(ErrorCodeAuth.TOKEN_EXPIRED, title, message, alertMessage);
    }

    public TokenExpiredException(String message, String alertMessage) {
        this(ErrorCodeAuth.TOKEN_EXPIRED.getTitle(), message, alertMessage);
    }

    public TokenExpiredException(String alertMessage) {
        this(ErrorCodeAuth.TOKEN_EXPIRED.getMessage(), alertMessage);
    }

    public TokenExpiredException() {
        this(ErrorCodeAuth.TOKEN_EXPIRED.getAlertMessage());
    }
}
