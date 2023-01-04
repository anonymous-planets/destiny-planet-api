package com.planet.destiny.auth.service.exception.token;

import com.planet.destiny.auth.service.constant.ErrorCodeAuth;
import com.planet.destiny.core.api.constant.ErrorCodeType;
import com.planet.destiny.core.api.exception.BusinessException;


/**
 * 기본 적인 토큰 에러
 */
public class TokenException extends BusinessException {

    public TokenException(ErrorCodeType errorCode, String title, String message, String alertMessage) {
        super(errorCode, title, message, alertMessage);
    }

    public TokenException(ErrorCodeType errorCode, String title, String message) {
        this(errorCode, title, message, errorCode.getAlertMessage());
    }

    public TokenException(ErrorCodeType errorCode, String title) {
        this(errorCode, title, errorCode.getMessage());
    }

    public TokenException(ErrorCodeType errorCode) {
        this(errorCode, errorCode.getTitle());
    }

    public TokenException() {
        this(ErrorCodeAuth.TOKEN_ERROR);
    }

    public TokenException(String alertMessage) {
        this(ErrorCodeAuth.TOKEN_ERROR, ErrorCodeAuth.TOKEN_ERROR.getTitle(), ErrorCodeAuth.TOKEN_ERROR.getMessage(), alertMessage);
    }
}
