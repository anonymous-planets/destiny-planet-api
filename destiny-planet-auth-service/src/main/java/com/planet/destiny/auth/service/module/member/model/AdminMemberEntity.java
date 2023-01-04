package com.planet.destiny.auth.service.module.member.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Slf4j
@Getter
@Entity
@Table(schema = "destiny_planet_user", name = "tb_admin_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMemberEntity implements Serializable {

    @Id
    @Column(name = "idx")
    private Long memberIdx;

    @Column(name = "member_id", columnDefinition = "VARCHAR(60) NOT NULL COMMENT '회원 ID(Email 형식)'")
    private String memberId;

    @Column(name = "password", columnDefinition = "VARCHAR(200) NOT NULL COMMENT '비밀번호'")
    private String password;

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL COMMENT '회원 이름'")
    private String name;

    @Column(name = "status", columnDefinition = "CHAR(5)")
    private String status;

    private String birthDay;
    private String phone;
    private Date lastPwdInitDate;
    private Date lastLoginDate;
    private Long creator;
    private Date createdDateTime;
    private Long modifier;
    private Date modifiedDateTime;

}
