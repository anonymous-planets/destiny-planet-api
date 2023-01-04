package com.planet.destiny.core.api.exception;

import com.planet.destiny.core.api.constant.ErrorCode;
import com.planet.destiny.core.api.constant.ErrorCodeType;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorCodeType errorCode, String title, String message, String alertMessage) {
        super(errorCode, title, message, alertMessage);
    }

    public NotFoundException(ErrorCodeType errorCode, String title, String message) {
        this(errorCode, title, message, errorCode.getAlertMessage());
    }

    public NotFoundException(ErrorCodeType errorCode, String title) {
        this(errorCode, title, errorCode.getMessage());
    }

    public NotFoundException(ErrorCodeType errorCode) {
        this(errorCode, errorCode.getTitle());
    }

    public NotFoundException(String alertMessage) {
        this(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND.getTitle(), ErrorCode.NOT_FOUND.getMessage(), alertMessage);
    }

    public NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }
}
