package com.planet.destiny.core.api.constant;

import org.springframework.http.HttpStatus;

public interface ErrorCodeType {

    String getName();

    HttpStatus getStatus();

    String getCode();

    String getTitle();

    String getMessage();

    String getAlertMessage();
}
