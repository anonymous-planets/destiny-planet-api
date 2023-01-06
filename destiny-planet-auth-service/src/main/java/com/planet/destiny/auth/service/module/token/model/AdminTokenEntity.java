package com.planet.destiny.auth.service.module.token.model;


import com.planet.destiny.auth.service.module.member.model.AdminMemberEntity;
import com.planet.destiny.core.api.module.token.item.TokenDto;
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
@Table(schema = "destiny_planet_user", name = "tb_admin_auth_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminTokenEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

//    @Column(name = "member_idx", columnDefinition = "INT NOT NULL COMMENT '회원 index'")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_idx", columnDefinition = "INT NOT NULL COMMENT '회원 index'")
    private AdminMemberEntity admin;

    @Column(name = "refresh_token", columnDefinition = "VARCHAR(200) NOT NULL COMMENT 'REFRESH TOKEN'")
    private String refreshToken;

    @Column(name = "refresh_token_expire_time", columnDefinition = "INT NOT NULL COMMENT 'REFRESH TOKEN 유지 시간(분)'")
    private Long refreshTokenExpireTime;

    @Column(name = "refresh_token_expire_date_time", columnDefinition = "DATETIME NOT NULL COMMENT 'REFRESH TOKEN 만료 일자'")
    private Date refreshTokenExpireDateTime;

//    @Column(name = "creator_idx", columnDefinition = "INT NOT NULL COMMENT '생성자 INDEX'")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_idx", columnDefinition = "INT NOT NULL COMMENT '생성자 INDEX'")
    private AdminMemberEntity creator;

    @Column(name = "created_date_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자'")
    private Date createdDateTime;

//    @Column(name = "modifier_idx", columnDefinition = "INT NOT NULL COMMENT '수정자'")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "modifier_idx", columnDefinition = "INT NOT NULL COMMENT '수정자'")
    private AdminMemberEntity modifier;

    @Column(name = "modified_date_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자'")
    private Date modifiedDateTime;

    @Builder
    public AdminTokenEntity(Long idx, AdminMemberEntity admin, String refreshToken
            , Long refreshTokenExpireTime, Date refreshTokenExpireDateTime, AdminMemberEntity creator
            , Date createdDateTime, AdminMemberEntity modifier, Date modifiedDateTime) {
        this.idx = idx;
        this.admin = admin;
        this.refreshToken = refreshToken;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
        this.refreshTokenExpireDateTime = refreshTokenExpireDateTime;
        this.creator = creator;
        this.createdDateTime = createdDateTime;
        this.modifier = modifier;
        this.modifiedDateTime = modifiedDateTime;
    }

    public static AdminTokenEntity createToken(AdminMemberEntity admin, AdminMemberEntity creator, AdminMemberEntity modifier, TokenDto.TokenRes tokenRes) {
        return AdminTokenEntity.builder()
                .admin(admin)
                .refreshToken(tokenRes.getRefreshToken())
                .refreshTokenExpireTime(tokenRes.getRefreshTokenExpireTime())
                .refreshTokenExpireDateTime(new Date(tokenRes.getRefreshTokenExpireTime()))
                .creator(creator)
                .createdDateTime(new Date())
                .modifier(modifier)
                .modifiedDateTime(new Date())
                .build();
    }
}
