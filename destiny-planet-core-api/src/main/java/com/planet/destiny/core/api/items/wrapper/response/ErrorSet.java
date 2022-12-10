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
    private String code;                // Custom Code
    private String title;               // Error 기본 메시지
    private String message;             // Error 상세 메시지
    private String alertMessage;        // 경고창에 보여줄 메시지
    private String path;                // 요청 Path
    private T data;                     // 요청 온 데이터


    @Builder
    public ErrorSet(E errorCode, String path, String alertMessage) {
        this.name = errorCode.getName();
        this.code = errorCode.getCode();
        this.title = errorCode.getTitle();
        this.message = errorCode.getMessage();
        this.alertMessage = alertMessage == null ? errorCode.getAlertMessage() : alertMessage;
        this.path = path;
    }
}
