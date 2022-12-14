package com.planet.destiny.core.api.utils;

import com.planet.destiny.core.api.exception.BusinessException;
import com.planet.destiny.core.api.items.wrapper.response.ErrorSet;
import com.planet.destiny.core.api.items.wrapper.response.RestEmptyResponse;
import jakarta.servlet.http.HttpServletRequest;


public class ErrorSetUtils {
    public static ErrorSet makeErrorSet(BusinessException e, HttpServletRequest request) {
        return ErrorSet.builder()
                .errorCode(e.getErrorCode())
                .path(request.getRequestURI())
                .build();
    }
}
