package com.planet.destiny.core.api.module.member.item;

import com.planet.destiny.core.api.constant.member.StatusType;
import com.planet.destiny.core.api.module.token.item.TokenDto;
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
public class AdminMemberDto implements Serializable {

    protected Long memberIdx;
    protected String memberId;
    protected String password;
    protected String name;
    protected String phone;
    protected StatusType status;

    @Builder
    public AdminMemberDto(Long memberIdx, String memberId, String password, String name, String phone, StatusType status) {
        this.memberIdx = memberIdx;
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.status = status;
    }
}
