package com.planet.destiny.core.api.constant;

public enum YesNoType implements LegacyType {

    YES("Y", "YES")
    , NO("N", "NO")
    ;

    private final String code;
    private final String desc;

    YesNoType(final String code, final String desc) {
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
