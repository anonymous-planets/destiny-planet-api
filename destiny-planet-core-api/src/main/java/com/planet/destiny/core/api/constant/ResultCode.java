package com.planet.destiny.core.api.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResultCode {

    SUCCESS(0, "성공")
    , FAIL(-1, "실패")
    , ERROR(-2, "에러")
    ;

    private final Integer code;
    private final String desc;

    ResultCode(final Integer code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getName() {
        return this.name();
    }

    @JsonValue
    public Integer getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
