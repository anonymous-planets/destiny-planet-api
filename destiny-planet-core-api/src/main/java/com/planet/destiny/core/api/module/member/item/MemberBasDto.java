package com.planet.destiny.core.api.module.member.item;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberBasDto implements Serializable {

    private Long memberIdx;     // 고유 KEY
    private String memberId;    // ID (이메일 형식)
    private String password;    // 비밀번홓(암호회된 값)
    private String name;        // 회원 명
    private String phone;       // 휴대폰 번호
    private String status;      // 상태

    @Builder
    public MemberBasDto(Long memberIdx, String memberId, String password, String name, String phone, String status) {
        this.memberIdx = memberIdx;
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.status = status;
    }
}
