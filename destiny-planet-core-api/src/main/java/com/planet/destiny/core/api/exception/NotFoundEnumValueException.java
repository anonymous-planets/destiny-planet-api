package com.planet.destiny.core.api.exception;

import com.planet.destiny.core.api.constant.ErrorCode;

import java.text.MessageFormat;

public class NotFoundEnumValueException extends NotFoundException{


    public NotFoundEnumValueException(String className) {
        super(ErrorCode.NOT_FOUND_ENUM_VALUE, MessageFormat.format("{0}에서 찾을 값이 빈칸 또는 NULL값 입니다.", className));
    }

    public NotFoundEnumValueException(String value, String className) {
        super(ErrorCode.NOT_FOUND_ENUM_VALUE, MessageFormat.format(ErrorCode.NOT_FOUND_ENUM_VALUE.getMessage(), value, className));
    }
}
