package com.planet.destiny.auth.service.module.member.item;

import com.google.gson.Gson;
import com.planet.destiny.core.api.constant.SenderType;
import com.planet.destiny.core.api.constant.member.AdminRoleType;
import com.planet.destiny.core.api.module.sender.item.EmailDto;
import com.planet.destiny.core.api.module.token.item.TokenDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

        @Builder
        public InviteReq(Long senderIdx, AdminRoleType role, String receiverName, String receiverAddress) {
            this.senderIdx = senderIdx;
            this.role = role;
            this.receiverName = receiverName;
            this.receiverAddress = receiverAddress;
        }

        public EmailDto toEmailDto(String senderName, String senderAddress, String templateFileName, String param) {
            Gson gson = new Gson();
            Map<String, Object> paramMap = gson.fromJson(param, Map.class);
            paramMap.put("receiverName", this.receiverName);
            paramMap.put("role", this.role.getDesc());

            return EmailDto
                    .builder()
                    .senderType(SenderType.EMAIL)
                    .fromInfo(EmailDto.PersonInfo.builder().name(senderName).address(senderAddress).build())
                    .toInfo(EmailDto.PersonInfo.builder().name(this.receiverName).address(this.receiverAddress).build())
                    .subject("[DestinyPlanet] 관리자 회원 가입 요청")
                    .isUseTemplate(true)
                    .templateFileName(templateFileName)
                    .params(paramMap)
                    .build();
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
