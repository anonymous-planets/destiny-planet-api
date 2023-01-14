package com.planet.destiny.core.api.exception;

import com.planet.destiny.core.api.constant.ErrorCode;
import com.planet.destiny.core.api.constant.ErrorCodeType;

public class BadRequestException extends RuntimeException {
    
    protected ErrorCodeType errorCode;
    protected String title;
    protected String message;
    protected String alertMessage;

    /**
     * @param errorCode
     * @param title
     * @param message
     * @param alertMessage
     */
    public BadRequestException(ErrorCodeType errorCode, String title, String message, String alertMessage) {
        this.errorCode = errorCode;
        this.title = title;
        this.message = message;
        this.alertMessage = alertMessage;
    }

    /**
     *
     * @param errorCode
     * @param title
     * @param message
     */
    public BadRequestException(ErrorCodeType errorCode, String title, String message) {
        this(errorCode, title, message, errorCode.getAlertMessage());
    }

    /**
     *
     * @param errorCode
     * @param title
     */
    public BadRequestException(ErrorCodeType errorCode, String title) {
        this(errorCode, title, errorCode.getMessage());
    }

    /**
     *
     * @param errorCode
     */
    public BadRequestException(ErrorCodeType errorCode) {
        this(errorCode, errorCode.getTitle(), errorCode.getMessage());
    }

    /**
     * @param title
     * @param message
     */
    public BadRequestException(String title, String message) {
        this(ErrorCode.BAD_REQUEST, title, message, ErrorCode.BAD_GATEWAY.getAlertMessage());
    }

    /**
     *
     * @param message
     */
    public BadRequestException(String message) {
        this(ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getTitle(), message, ErrorCode.BAD_REQUEST.getAlertMessage());
    }

    /**
     *
     */
    public BadRequestException() {
        this(ErrorCode.BAD_REQUEST);
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
