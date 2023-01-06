package com.planet.destiny.core.api.constant.member;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.planet.destiny.core.api.constant.LegacyType;
import com.planet.destiny.core.api.exception.NotFoundEnumValueException;
import com.planet.destiny.core.api.utils.StringUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
    public static StatusType create(String value) throws NotFoundEnumValueException {
        if(StringUtils.isEmpty(value)) {
            throw new NotFoundEnumValueException(StatusType.class.getSimpleName());
        }

        for(StatusType statusType : StatusType.values()) {
            if(statusType.getName().equals(value) || statusType.getCode().equals(value)) {
                return statusType;
            }
        }

        throw new NotFoundEnumValueException(value, StatusType.class.getSimpleName());
    }

    @Converter
    public static class StatusTypeAttributeConverter implements AttributeConverter<StatusType, String> {
        @Override
        public String convertToDatabaseColumn(StatusType attribute) {
            return attribute.getCode();
        }

        @Override
        public StatusType convertToEntityAttribute(String dbData) {
            return StatusType.create(dbData);
        }
    }
}
