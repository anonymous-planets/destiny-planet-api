package com.planet.destiny.core.api.module.member.item;

import com.planet.destiny.core.api.constant.member.StatusType;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 관리자 회원 기본 정보 DTO
 */
@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMemberBasDto implements Serializable {

    protected Long memberIdx;

    @NotBlank(message = "회원 아이디는 필수 값입니다.")
    protected String memberId;

    @NotBlank(message = "비밀번호는 필수 값입니다.")
    protected String password;

    @NotBlank(message = "이름은 필수 값입니다.")
    protected String name;

    @NotBlank(message = "이메일은 필수 값입니다.")
    protected String email;

    @NotBlank(message = "휴대폰 번호는 필수 값입니다.")
    protected String phone;

    protected StatusType status;

    @Builder
    public AdminMemberBasDto(Long memberIdx, String memberId, String password, String name, String email, String phone, StatusType status) {
        this.memberIdx = memberIdx;
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }
}
