package com.planet.destiny.auth.service.module.member.item;

import com.planet.destiny.core.api.module.token.item.TokenDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public class AdminMemberDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class LoginReq implements Serializable {
        private String memberId;
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
        private String memberIdx;
        private TokenDto.TokenRes token;

        public LoginRes(String memberIdx, TokenDto.TokenRes token) {
            this.memberIdx = memberIdx;
            this.token = token;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SignInReq extends com.planet.destiny.core.api.module.member.item.AdminMemberDto implements Serializable {

        @Builder
        public SignInReq(String memberId, String name, String password, String passwordConfirm, String phone) {
            super.memberId = memberId;
            super.name = name;
            super.password = password;
            super.phone = phone;
        }
    }
}
