package com.planet.destiny.core.api.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResultCode {

    SUCCESS(0, "SUCCESS")
    , FAIL(-1, "FAIL")
    , ERROR(-2, "ERROR")
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
