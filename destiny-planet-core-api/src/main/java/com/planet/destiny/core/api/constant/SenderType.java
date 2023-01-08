package com.planet.destiny.core.api.constant;

import com.planet.destiny.core.api.module.sender.item.SenderDto;

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

    /**
     * 메세지 전송 상세 클래스가 구현할 인터페이스
     */
    public interface Sender<T extends SenderDto> {
        void send(T senderDto);
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

