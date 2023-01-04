package com.planet.destiny.core.api.constant.member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.planet.destiny.core.api.constant.LegacyType;

public enum StatusType implements LegacyType {
    WAIT("W", "가입 대기")
    , ACTIVE("A", "활성")
    , DORMANCY("D", "휴면")
    , LOCK("L", "잠금")
    ;

    private final String code;
    private final String desc;

    StatusType(final String code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }


    @JsonCreator
    public static StatusType create(String value) {
        return null;
    }

}
