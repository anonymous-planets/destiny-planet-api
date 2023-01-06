package com.planet.destiny.core.api.items.wrapper.response;


import com.planet.destiny.core.api.constant.ErrorCode;
import com.planet.destiny.core.api.constant.ErrorCodeType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorSet <T extends Serializable, E extends ErrorCodeType> implements Serializable {

    private String name;                // 에러 이름
    private String serviceName;         // Micro Service Name
    private String code;                // Custom Code
    private String title;               // Error 기본 메시지
    private String message;             // Error 상세 메시지
    private String alertMessage;        // 경고창에 보여줄 메시지
    private String path;                // 요청 Path

//    private T data;                     // 요청 온 데이터

//    @Builder
//    public ErrorSet(String name, String code, String title, String message, String alertMessage, String path) {
//        this.name = name;
//        this.code = code;
//        this.title = title;
//        this.message = message;
//        this.alertMessage = alertMessage;
//        this.path = path;
//    }

    @Builder
    public ErrorSet(E errorCode, String name, String serviceName, String title, String message,String alertMessage, String path) {
        this.name = name;
        this.code = errorCode.getCode();
        this.serviceName = serviceName;
        this.title = title == null ? errorCode.getTitle() : title;
        this.message = message == null ? errorCode.getMessage() : message;
        this.alertMessage = alertMessage == null ? errorCode.getAlertMessage() : alertMessage;
        this.path = path;
    }
}
