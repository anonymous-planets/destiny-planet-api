package com.planet.destiny.core.api.constant;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements ErrorCodeType {
    // Client Error
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "C000", "잘못된 요청입니다.", "", "")

    , UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "C101", "인증이 필한 요청입니다", "인증이 필요한 요청입니다.", "로그인이 필요 합니다. 로그인 해주세요.")

    , FORBIDDEN(HttpStatus.FORBIDDEN, "C201", "권한이 없는 요청입니다.", "해당 페이지 접근 권한이 없습니다.", "해당 페이지 접근 권한이 없습니다.")

    , NOT_FOUND(HttpStatus.NOT_FOUND, "C401", "정보를 찾을 수 없습니다.", "{0} 정보를 찾을 수 없습니다.", "")
    , NOT_FOUND_RESOURCE(HttpStatus.NOT_FOUND, "C402", "자원 정보를 찾을 수 없습니다.", "%s 정보를 찾을 수 없습니다.", "")
    , NOT_FOUND_USER(HttpStatus.NOT_FOUND, "C402", "회원 정보를 찾을 수 없습니다.", "[%s] 회원 정보를 찾을 수 없습니다.", "")
    , NOT_FOUND_ENUM_VALUE(HttpStatus.NOT_FOUND, "C403", "타입 정보를 찾을 수 없습니다.", "%{0}는 %{1} 에서 지원 하지 않는 값입니다.", "문제가 발생 했습니다. 잠시 후 다시 시도해주세요.<br/>계속 문제가 발생시 로그아웃 후 다시 로그인 해주세요.")

    , METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C501", "허용 하지 않는 요청입니다.", "허용하지 않은 Method입니다. API정보를 다시 확인해주세요.", "문제가 발생 했습니다. 잠시 후 다시 시도해주세요.")

    // Server Error
    , INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S001", "서버 에러가 발생했습니다.", "서버에 에러가 발생 했습니다.", "문제가 발생 했습니다. 잠시 후 다시 시도해주세요.<br/>계속 문제가 발생시 고객 센터로 문의 해주세요.")

    , BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "S201", "잘못된 응답을 수신했습니다.", "", "")

    , SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "S301", "서버 작업을 진행 하고 있습니다.", "서버 작업을 진행 중입니다. $1 ~ $2까지 작업 진행 예정입니다.", "좀더 낫은 서비스를 위해 시스템 작업 중입니다. $1 이후 부터 접속 가능합니다.")

    , GATEWAY_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT, "S401", "", "", "")

    ;


    private final HttpStatus status;
    private final String code;
    private final String title;
    private final String message;
    private final String alertMessage;

    ErrorCode(final HttpStatus status, String code, String title, String message, String alertMessage) {
        this.status = status;
        this.code = code;
        this.title = title;
        this.message = message;
        this.alertMessage = alertMessage;
    }

    @Override
    public String getName() {
        return this.name();
    }
    @Override
    public HttpStatus getStatus() {
        return this.status;
    }
    @Override
    public String getCode() {
        return this.code;
    }
    @Override
    public String getTitle() {
        return this.title;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
    @Override
    public String getAlertMessage() {
        return this.alertMessage;
    }
}
