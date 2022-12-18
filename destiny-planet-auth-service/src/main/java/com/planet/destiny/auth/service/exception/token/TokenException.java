package com.planet.destiny.auth.service.exception.token;

import com.planet.destiny.auth.service.constant.ErrorCodeAuth;
import com.planet.destiny.core.api.exception.BusinessException;


/**
 * 기본 적인 토큰 에러
 */
public class TokenException extends BusinessException {

    public TokenException(ErrorCodeAuth errorCodeAuth, String title, String message, String alertMessage) {
        super(errorCodeAuth, title, message, alertMessage);
    }

    public TokenException(ErrorCodeAuth errorCodeAuth) {
        this(errorCodeAuth, errorCodeAuth.getTitle(), errorCodeAuth.getMessage(), errorCodeAuth.getAlertMessage());
    }

    public TokenException(ErrorCodeAuth errorCodeAuth, String alertMessage) {
        this(errorCodeAuth, errorCodeAuth.getTitle(), errorCodeAuth.getMessage(), errorCodeAuth.getAlertMessage());
    }

    public TokenException() {
        this(ErrorCodeAuth.TOKEN_ERROR, ErrorCodeAuth.TOKEN_ERROR.getTitle(), ErrorCodeAuth.TOKEN_ERROR.getMessage(), ErrorCodeAuth.TOKEN_ERROR.getAlertMessage());

    }

}
