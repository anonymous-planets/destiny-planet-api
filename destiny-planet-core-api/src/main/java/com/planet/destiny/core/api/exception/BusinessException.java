package com.planet.destiny.core.api.exception;

import com.planet.destiny.core.api.constant.ErrorCode;
import com.planet.destiny.core.api.constant.ErrorCodeType;
import com.planet.destiny.core.api.items.wrapper.response.ErrorSet;

public class BusinessException extends RuntimeException {

    protected ErrorCodeType errorCode;
    protected String title;
    protected String message;
    protected String alertMessage;

    public BusinessException(ErrorCodeType errorCode, String title, String message, String alertMessage) {
        super(title);
        this.errorCode = errorCode;
        this.title = title;
        this.message = message;
        this.alertMessage = alertMessage;
    }


    public BusinessException(ErrorCodeType errorCode, String title, String message) {
        this(errorCode, title, message, errorCode.getAlertMessage());
    }

    public BusinessException(ErrorCodeType errorCode, String title) {
        this(errorCode, title, errorCode.getMessage());
    }

    public BusinessException(ErrorCodeType errorCode) {
        this(errorCode, errorCode.getTitle(), errorCode.getMessage());
    }

    public BusinessException() {
        this(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    public BusinessException(String alertMessage) {
        this(ErrorCode.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getTitle(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage(), alertMessage);
    }


    public ErrorCodeType getErrorCode() {
        return this.errorCode;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }

    public String getAlertMessage() {
        return this.alertMessage;
    }
}
