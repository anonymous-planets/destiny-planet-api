package com.planet.destiny.auth.service.exception.token;

import com.planet.destiny.auth.service.constant.ErrorCodeAuth;

public class TokenIllegalArgumentException extends TokenException {

    public TokenIllegalArgumentException(String title, String message, String alertMessage) {
        super(ErrorCodeAuth.TOKEN_ILLEGAL, title, message, alertMessage);
    }

    public TokenIllegalArgumentException(String message, String alertMessage) {
        this(ErrorCodeAuth.TOKEN_ILLEGAL.getTitle(), message, alertMessage);
    }

    public TokenIllegalArgumentException(String alertMessage) {
        this(ErrorCodeAuth.TOKEN_ILLEGAL.getMessage(), alertMessage);
    }

    public TokenIllegalArgumentException() {
        this(ErrorCodeAuth.TOKEN_ILLEGAL.getAlertMessage());
    }




}
