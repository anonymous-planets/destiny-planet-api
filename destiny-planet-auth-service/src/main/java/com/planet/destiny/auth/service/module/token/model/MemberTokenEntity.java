package com.planet.destiny.auth.service.module.token.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(schema = "destiny_planet_user", name = "tb_member_auth_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTokenEntity implements Serializable {

    @Id
    private Integer idx;

    @Column(name = "member_idx", columnDefinition = "INT NOT NULL COMMENT '회원 index'")
    private Integer memberIdx;

    @Column(name = "refresh_token", columnDefinition = "VARCHAR(200) NOT NULL COMMENT 'REFRESH TOKEN'")
    private String refreshToken;

    @Column(name = "refresh_token_expire_time", columnDefinition = "INT NOT NULL COMMENT 'REFRESH TOKEN 유지 시간(분)'")
    private Integer refreshTokenExpireTime;

    @Column(name = "refresh_token_expire_date_time", columnDefinition = "DATETIME NOT NULL COMMENT 'REFRESH TOKEN 만료 일자'")
    private Date refreshTokenExpireDateTime;

    @Column(name = "creator_idx", columnDefinition = "INT NOT NULL COMMENT '생성자 INDEX'")
    private Integer creator;

    @Column(name = "created_date_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자'")
    private Date createdDateTime;

    @Column(name = "modifier_idx", columnDefinition = "INT NOT NULL COMMENT '수정자'")
    private Integer modifier;

    @Column(name = "modified_date_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자'")
    private Date modifiedDateTime;

    @Builder
    public MemberTokenEntity(Integer idx, Integer memberIdx, String refreshToken
            , Integer refreshTokenExpireTime, Date refreshTokenExpireDateTime, Integer creator
            , Date createdDateTime, Integer modifier, Date modifiedDateTime) {
        this.idx = idx;
        this.memberIdx = memberIdx;
        this.refreshToken = refreshToken;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
        this.refreshTokenExpireDateTime = refreshTokenExpireDateTime;
        this.creator = creator;
        this.createdDateTime = createdDateTime;
        this.modifier = modifier;
        this.modifiedDateTime = modifiedDateTime;
    }
}
