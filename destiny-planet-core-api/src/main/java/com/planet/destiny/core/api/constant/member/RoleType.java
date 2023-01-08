package com.planet.destiny.core.api.constant.member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.planet.destiny.core.api.constant.LegacyType;
import com.planet.destiny.core.api.exception.NotFoundEnumValueException;
import com.planet.destiny.core.api.utils.StringUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum RoleType implements LegacyType {

    // 등급이 높을수록 등급이 높은 회원
    GUEST("G", "비인증회원")
    , MERCURY("M-1", "수성(1등급)")
    , VENUS("V-2", "금성(2등급)")
    , EARTH("E-3", "지구(3등급)")
    , MARS("M-4", "화성(4등급)")
    , JUPITER("J-5", "목성(5등급)")
    , SATURN("S-6", "토성(6등급)")
    , URANUS("U-7", "천왕성(7등급)")
    , NEPTUNE("N-8", "해왕성(8등급)")
    ;


    private final String code;
    private final String desc;

    RoleType(final String code, final String desc) {
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
    public static RoleType create(String value) throws NotFoundEnumValueException {
        if(StringUtils.isEmpty(value)) {
            throw new NotFoundEnumValueException(RoleType.class.getSimpleName());
        }

        for(RoleType roleType : RoleType.values()) {
            if(roleType.getCode().equals(value) || roleType.getName().equals(value)) {
                return roleType;
            }
        }
        throw new NotFoundEnumValueException(value, RoleType.class.getSimpleName());
    }

    @Converter
    public static class RoleTypeAttributeConverter implements AttributeConverter<RoleType, String> {
        @Override
        public String convertToDatabaseColumn(RoleType attribute) {
            return attribute.getCode();
        }
        @Override
        public RoleType convertToEntityAttribute(String dbData) {
            return RoleType.create(dbData);
        }
    }
}
