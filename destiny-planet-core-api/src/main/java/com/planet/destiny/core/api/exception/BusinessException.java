package com.planet.destiny.core.api.exception;

import com.planet.destiny.core.api.constant.ErrorCode;
import com.planet.destiny.core.api.constant.ErrorCodeType;

public class BusinessException extends RuntimeException {

    protected ErrorCode errorCode;

    private String name;
    private String code;
    private String title;
    private String message;
    private String alertMessage;
    private String path;





}
