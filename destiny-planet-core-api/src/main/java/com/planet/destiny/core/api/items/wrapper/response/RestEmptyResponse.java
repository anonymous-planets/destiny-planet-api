package com.planet.destiny.core.api.items.wrapper.response;


import com.planet.destiny.core.api.constant.ResultCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestEmptyResponse<T> extends RestResponseBase<RestEmptyResponse<T>> implements Serializable {

    private RestEmptyResponse(ResultCode resultCode, String message, ErrorSet error) {
        super.resultCode = resultCode;
        super.message = message;
        super.error = error;
    }

    public static RestEmptyResponse success(String message) {
        return new RestEmptyResponse(ResultCode.SUCCESS, message, null);
    }

    public static RestEmptyResponse success() {
        return new RestEmptyResponse(ResultCode.SUCCESS, ResultCode.SUCCESS.getDesc(), null);
    }

    /**
     * 에러는 아니고 실패
     * @param message 실패 사유(필수)
     * @return
     */
    public static RestEmptyResponse fail(String message) {
        return new RestEmptyResponse(ResultCode.FAIL, ResultCode.FAIL.getDesc() + " : " + message, null);
    }

    /**
     * 에러 발생
     * @param message
     * @param error
     * @return
     * @param <T>
     */
    public static <T extends Serializable> RestEmptyResponse<T> error(String message, ErrorSet<T> error) {
        return new RestEmptyResponse<T>(ResultCode.ERROR, message, error);
    }

    @Override
    public RestEmptyResponse<T> message(String message) {
        super.message = message;
        return this;
    }
}
