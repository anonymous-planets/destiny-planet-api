package com.planet.destiny.core.api.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.planet.destiny.core.api.exception.BusinessException;
import com.planet.destiny.core.api.exception.NotFoundEnumValueException;
import com.planet.destiny.core.api.exception.NotFoundException;
import com.planet.destiny.core.api.utils.StringUtils;
import jakarta.persistence.AttributeConverter;

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

    @JsonCreator
    public static YesNoType create(String value) throws NotFoundException {
        if(StringUtils.isEmpty(value)) {
            throw new NotFoundEnumValueException(YesNoType.class.getSimpleName());
        }
        for(YesNoType yesNoType : YesNoType.values()) {
            if(yesNoType.getName().equals(value) || yesNoType.getCode().equals(value)) {
                return yesNoType;
            }
        }
        throw new NotFoundEnumValueException(value, YesNoType.class.getSimpleName());
    }

    public static class YesNoTypeAttributeConverter implements AttributeConverter<YesNoType, String> {
        @Override
        public String convertToDatabaseColumn(YesNoType yesNoType) {
            return yesNoType.getCode();
        }

        @Override
        public YesNoType convertToEntityAttribute(String dbData) {
            return YesNoType.create(dbData);
        }
    }
}
