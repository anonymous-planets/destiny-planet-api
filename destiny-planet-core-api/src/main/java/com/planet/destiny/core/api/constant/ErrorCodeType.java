package com.planet.destiny.core.api.constant;

import org.springframework.http.HttpStatus;

public interface ErrorCodeType {

    public String getName();

    public HttpStatus getStatus();

    public String getCode();

    public String getTitle();

    public String getMessage();

    public String getAlertMessage();
}
