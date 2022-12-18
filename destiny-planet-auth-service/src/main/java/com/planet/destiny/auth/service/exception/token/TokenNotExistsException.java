package com.planet.destiny.auth.service.exception.token;

import com.planet.destiny.auth.service.constant.ErrorCodeAuth;

public class TokenNotExistsException extends TokenException {

    public TokenNotExistsException(String title, String message, String alertMessage) {
        super(ErrorCodeAuth.TOKEN_NOT_EXISTS, title, message, alertMessage);
    }

    public TokenNotExistsException(String message, String alertMessage) {
        this(ErrorCodeAuth.TOKEN_NOT_EXISTS.getTitle(), message, alertMessage);
    }

    public TokenNotExistsException(String alertMessage) {
        this(ErrorCodeAuth.TOKEN_NOT_EXISTS.getMessage(), alertMessage);
    }

    public TokenNotExistsException() {
        this(ErrorCodeAuth.TOKEN_NOT_EXISTS.getAlertMessage());
    }
}
