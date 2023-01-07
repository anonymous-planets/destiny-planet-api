package com.planet.destiny.auth.service.module.member.item;

import com.planet.destiny.core.api.module.token.item.TokenDto;
import com.planet.destiny.core.api.validate.annotation.Phone;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public class AdminMemberDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class LoginReq implements Serializable {

        @NotBlank(message = "로그인 아이디 값이 없습니다.")
        private String memberId;

        @NotBlank(message = "로그인 비밀번호 값이 없습니다.")
        private String password;

        private String serviceType;

        @Builder
        public LoginReq(String memberId, String password, String serviceType) {
            this.memberId = memberId;
            this.password = password;
            this.serviceType = serviceType;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class LoginRes implements Serializable {
        private TokenDto.TokenRes token;

        @Builder
        public LoginRes(TokenDto.TokenRes token) {
            this.token = token;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SignInReq extends com.planet.destiny.core.api.module.member.item.AdminMemberDto implements Serializable {
        public SignInReq(String memberId, String name, String password, String passwordConfirm, String phone) {
            super.memberId = memberId;
            super.name = name;
            super.password = password;
            super.phone = phone;
        }
    }
}
