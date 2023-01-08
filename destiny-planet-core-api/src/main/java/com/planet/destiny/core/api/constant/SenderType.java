package com.planet.destiny.core.api.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.planet.destiny.core.api.exception.NotFoundEnumValueException;
import com.planet.destiny.core.api.module.sender.item.SenderDto;
import com.planet.destiny.core.api.module.sender.service.SenderCallback;
import com.planet.destiny.core.api.utils.StringUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.EnumSet;

public enum SenderType implements LegacyType {

    FCM("FCM", "PUSH")
    , REDIS("REDIS", "REDIS")
    , SMS("SMS", "SMS")
    , EMAIL("EMAIL", "EMAIL");

    private final String code;
    private final String desc;

    SenderType(final String code, final String desc) {
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

    public static final EnumSet<SenderType> PUSH_ALL = EnumSet.of(FCM, REDIS);


    @JsonCreator
    public static SenderType create(String value) {
        if(StringUtils.isEmpty(value)) {
            throw new NotFoundEnumValueException(SenderType.class.getSimpleName());
        }

        for(SenderType senderType : SenderType.values()) {
            if(senderType.getCode().equals(value)) {
                return senderType;
            }
        }
        throw new NotFoundEnumValueException(value, SenderType.class.getSimpleName());
    }

    @Converter
    public static class SenderTypeAttributeConverter implements AttributeConverter<SenderType, String> {
        @Override
        public String convertToDatabaseColumn(SenderType attribute) {
            return attribute.getCode();
        }
        @Override
        public SenderType convertToEntityAttribute(String dbData) {
            return SenderType.create(dbData);
        }
    }

    /**
     * 메세지 전송 상세 클래스가 구현할 인터페이스
     */
    public interface Sender<T extends SenderDto> {
        void send(T senderDto, SenderCallback callback);
    }

    public interface SenderFactory {
        Sender getSender(SenderType senderType);
    }

    public interface TypeConstants {
        String FCM_CODE = "FCM";
        String REDIS_CODE = "REDIS";
        String SMS_CODE = "SMS";
        String EMAIL_CODE = "EMAIL";
    }

}

