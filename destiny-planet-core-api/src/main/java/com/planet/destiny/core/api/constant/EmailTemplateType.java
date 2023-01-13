package com.planet.destiny.core.api.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.planet.destiny.core.api.exception.NotFoundEnumValueException;
import com.planet.destiny.core.api.utils.StringUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

public enum EmailTemplateType implements LegacyType {

    ADMIN_INVITE("AI", "관리자 초대 메일")
    ;

    private final String code;
    private final String desc;

    EmailTemplateType(final String code, final String desc) {
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
    public static EmailTemplateType create(String value) throws NotFoundEnumValueException {
        if(StringUtils.isEmpty(value)) {
            throw new NotFoundEnumValueException(EmailTemplateType.class.getSimpleName());
        }

        for(EmailTemplateType type : EmailTemplateType.values()) {
            if(type.getCode().equals(value)) {
                return type;
            }
        }
        throw new NotFoundEnumValueException(value, EmailTemplateType.class.getSimpleName());
    }


    @Converter
    public static class EmailTemplateTypeAttributeConverter implements AttributeConverter<EmailTemplateType, String> {

        @Override
        public String convertToDatabaseColumn(EmailTemplateType attribute) {
            return attribute.getCode();
        }

        @Override
        public EmailTemplateType convertToEntityAttribute(String dbData) {
            return EmailTemplateType.create(dbData);
        }
    }
}
