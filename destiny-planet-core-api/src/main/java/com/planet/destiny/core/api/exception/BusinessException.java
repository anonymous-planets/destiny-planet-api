package com.planet.destiny.core.api.exception;

import com.planet.destiny.core.api.constant.ErrorCode;
import com.planet.destiny.core.api.constant.ErrorCodeType;

public class BusinessException extends RuntimeException {

    protected ErrorCode errorCode;

    protected String title;

    protected String message;

    protected String alertMessage;

    public BusinessException(ErrorCode errorCode, String title, String message, String alertMessage) {
        super(title);
        this.errorCode = errorCode;
        this.title = title;
        this.message = message;
        this.alertMessage = alertMessage;
    }

    public BusinessException(ErrorCode errorCode, String title, String message) {
        this(errorCode, title, message, errorCode.getAlertMessage());
    }

    public BusinessException(ErrorCode errorCode) {
        this(errorCode, errorCode.getTitle(), errorCode.getMessage(), errorCode.getAlertMessage());
    }

    public BusinessException(String title, String message) {
        this(ErrorCode.INTERNAL_SERVER_ERROR, title, message, ErrorCode.INTERNAL_SERVER_ERROR.getAlertMessage());
    }

    public BusinessException() {
        this(ErrorCode.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getTitle(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR.getAlertMessage());
    }

    public ErrorCode getErrorCode() {
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
