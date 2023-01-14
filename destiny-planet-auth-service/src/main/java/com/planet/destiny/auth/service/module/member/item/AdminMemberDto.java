package com.planet.destiny.auth.service.module.member.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.planet.destiny.core.api.constant.member.AdminRoleType;
import com.planet.destiny.core.api.module.member.item.AdminMemberBasDto;
import com.planet.destiny.core.api.module.token.item.TokenDto;
import com.planet.destiny.core.api.utils.PropertyUtils;
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
    public static class InviteReq implements Serializable {

        private Long senderIdx;

        private AdminRoleType role;

        @NotBlank(message = "수신자 이름은 필수 값입니다.")
        private String receiverName;

        @NotBlank(message = "수신자 이메일은 필수 값입니다.")
        private String receiverAddress;

        @JsonIgnore
        private String siteUrl = PropertyUtils.getProperty("site-url.admin");

        @Builder
        public InviteReq(Long senderIdx, AdminRoleType role, String receiverName, String receiverAddress) {
            this.senderIdx = senderIdx;
            this.role = role;
            this.receiverName = receiverName;
            this.receiverAddress = receiverAddress;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SignUpCheckReq implements Serializable {
        private Long inviteIdx;
        private String identityCode;

        @Builder
        public SignUpCheckReq(Long inviteIdx, String identityCode) {
            this.inviteIdx = inviteIdx;
            this.identityCode = identityCode;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SignUpCheckRes implements Serializable {
        private String email;

        @Builder
        public SignUpCheckRes(String email) {
            this.email = email;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SignUpReq extends AdminMemberBasDto implements Serializable {
    }
}
