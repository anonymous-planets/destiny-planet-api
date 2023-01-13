package com.planet.destiny.core.api.constant.member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.planet.destiny.core.api.constant.LegacyType;
import com.planet.destiny.core.api.exception.NotFoundEnumValueException;
import com.planet.destiny.core.api.utils.StringUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum AdminRoleType implements LegacyType {

    GUEST("G", "비인증 회원")
    , MANAGER("M", "매니저")
    , ADMIN("A", "관리자")
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

    @JsonCreator
    public static AdminRoleType create(String value) throws NotFoundEnumValueException {
        if(StringUtils.isEmpty(value)) {
            throw new NotFoundEnumValueException(AdminRoleType.class.getSimpleName());
        }

        for(AdminRoleType adminRoleType : AdminRoleType.values()) {
            if(adminRoleType.getName().equals(value) || adminRoleType.getCode().equals(value)) {
                return adminRoleType;
            }
        }
        throw new NotFoundEnumValueException(value, AdminRoleType.class.getSimpleName());
    }

    @Converter
    public static class AdminRoleTypeAttributeConverter implements AttributeConverter<AdminRoleType, String> {
        @Override
        public String convertToDatabaseColumn(AdminRoleType attribute) {
            return attribute.getCode();
        }

        @Override
        public AdminRoleType convertToEntityAttribute(String dbData) {
            return AdminRoleType.create(dbData);
        }
    }
}
