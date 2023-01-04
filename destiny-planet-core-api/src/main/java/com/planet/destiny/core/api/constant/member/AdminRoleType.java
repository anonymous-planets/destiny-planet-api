package com.planet.destiny.core.api.constant.member;

import com.planet.destiny.core.api.constant.LegacyType;

public enum AdminRoleType implements LegacyType {

    GUEST("G", "비인증 회원")
    , ADMIN("A", "관리자")
    , MANAGER("M", "매니저")
    , SUPER_ADMIN("S", "슈퍼 관리자")
    ;

    private final String code;
    private final String desc;

    AdminRoleType(String code, String desc) {
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
}
