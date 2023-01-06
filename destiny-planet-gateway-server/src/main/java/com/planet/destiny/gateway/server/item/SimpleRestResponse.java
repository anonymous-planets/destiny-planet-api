package com.planet.destiny.gateway.server.item;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleRestResponse implements Serializable {

    private Integer resultCode; // 결과 코드(0 : 성공, -1 : 실패)
    private String message;     // 결과 메시지
    private Long timestamp;     // 시간
    private ErrorSet error;     // Error 내용

    private SimpleRestResponse(Integer resultCode, String message, ErrorSet error) {
        this.resultCode = resultCode;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.error = error;
    }

    public static SimpleRestResponse error(String message, ErrorSet error) {
        return new SimpleRestResponse(-1, message, error);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ErrorSet implements Serializable {

        private String name;

        private String serviceName = "destiny-planet-gateway-server";
        private String code;
        private String title;
        private String message;
        private String alertMessage;
        private String path;


        @Builder
        public ErrorSet(String name, String code, String title, String message, String alertMessage, String path) {
            this.name = name;
            this.code = code;
            this.title = title;
            this.message = message;
            this.alertMessage = alertMessage;
            this.path = path;
        }

    }
}
