package com.planet.destiny.core.api.items.wrapper.response;

import com.planet.destiny.core.api.constant.ResultCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;



@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestSingleResponse<T extends Serializable> extends RestResponseBase implements Serializable {

    private T data;

    private RestSingleResponse(ResultCode resultCode, String message) {
        super.resultCode = resultCode;
        super.message = message == null ? resultCode.getDesc() : message;
    }

    public static <T extends Serializable> RestSingleResponse<T> success(String message, T clazz) {
        return new RestSingleResponse<T>(ResultCode.SUCCESS, message).add(clazz);
    }

    public static <T extends Serializable> RestSingleResponse<T> success(T clazz) {
        return new RestSingleResponse<T>(ResultCode.SUCCESS, ResultCode.SUCCESS.getDesc()).add(clazz);
    }

    private RestSingleResponse<T> add(T data) {
        if(data == null) {
            return this;
        }

        this.data = data;
        return this;
    }

    @Override
    public Object message(String message) {
        super.message = message;
        return this;
    }
}
