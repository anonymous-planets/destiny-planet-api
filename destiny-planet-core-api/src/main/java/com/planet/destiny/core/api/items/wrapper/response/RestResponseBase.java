package com.planet.destiny.core.api.items.wrapper.response;


import com.planet.destiny.core.api.constant.ResultCode;
import lombok.Getter;

@Getter
public abstract class RestResponseBase<T> {

    protected ResultCode resultCode;        // 0, -1, -2
    protected String message;               // 성공, 실패, 에러
    protected Long timestamp = System.currentTimeMillis();
    protected ErrorSet error;

    public abstract T message(String message);
}
