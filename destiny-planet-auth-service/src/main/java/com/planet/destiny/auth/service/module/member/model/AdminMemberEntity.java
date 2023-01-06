package com.planet.destiny.auth.service.module.member.model;

import com.planet.destiny.core.api.constant.member.StatusType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Slf4j
@Getter
@Entity
@Table(schema = "destiny_planet_user", name = "tb_admin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMemberEntity implements Serializable {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "member_id", columnDefinition = "VARCHAR(30) NOT NULL COMMENT '로그인 아이디'")
    private String memberId;

    @Column(name = "password", columnDefinition = "VARCHAR(150) NOT NULL COMMENT '로그인 비밀번호'")
    private String password;

    @Column(name = "name", columnDefinition = "VARCHAR(30) NOT NULL COMMENT '회원 이름'")
    private String name;

    @Column(name = "phone", columnDefinition = "VARCHAR(30) NOT NULL COMMENT '회원 이름'")
    private String phone;

    @Column(name = "nick_name", columnDefinition = "VARCHAR(30) NOT NULL COMMENT '회원 이름'")
    private String nickName;

    @Column(name = "birthday", columnDefinition = "VARCHAR(30) NOT NULL COMMENT '회원 이름'")
    private String birthday;

    @Column(name = "email_addr", columnDefinition = "VARCHAR(30) NOT NULL COMMENT '회원 이름'")
    private String emailAddr;

    @Column(name = "status", columnDefinition = "VARCHAR(30) NOT NULL COMMENT '회원 이름'")
    @Convert(converter = StatusType.StatusTypeAttributeConverter.class)
    private StatusType status;

    @Column(name = "last_pwd_init_date", columnDefinition = "VARCHAR(30) NOT NULL COMMENT '회원 이름'")
    private Date lastPwdInitDate;

    @Column(name = "last_login_date", columnDefinition = "VARCHAR(30) NOT NULL COMMENT '회원 이름'")
    private Date lastLoginDate;

    @Column(name = "creator_idx", columnDefinition = "VARCHAR(30) NOT NULL COMMENT '회원 이름'")
    private Long creator;

    @Column(name = "created_date_time", columnDefinition = "VARCHAR(30) NOT NULL COMMENT '회원 이름'")
    private Date createdDateTime;

    @Column(name = "modifier_idx", columnDefinition = "VARCHAR(30) NOT NULL COMMENT '회원 이름'")
    private Long modifier;

    @Column(name = "modified_date_time", columnDefinition = "VARCHAR(30) NOT NULL COMMENT '회원 이름'")
    private Date modifiedDateTime;


    @Builder
    public AdminMemberEntity(Long idx, String memberId, String password, String name
            , String phone, String nickName, String birthday, String emailAddr
            , StatusType status, Date lastPwdInitDate, Date lastLoginDate, Long creator
            , Date createdDateTime, Long modifier, Date modifiedDateTime) {
        this.idx = idx;
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.nickName = nickName;
        this.birthday = birthday;
        this.emailAddr = emailAddr;
        this.status = status;
        this.lastPwdInitDate = lastPwdInitDate;
        this.lastLoginDate = lastLoginDate;
        this.creator = creator;
        this.createdDateTime = createdDateTime;
        this.modifier = modifier;
        this.modifiedDateTime = modifiedDateTime;
    }

}
