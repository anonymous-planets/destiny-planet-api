package com.planet.destiny.core.api.module.member.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.io.Serializable;
import java.util.Date;

@Slf4j
@Getter
@Entity
@Table(schema = "destiny_planet_user", name = "tb_admin_role_relation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminRoleEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_idx")
    private AdminMemberEntity admin;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_idx")
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_idx")
    private AdminMemberEntity creator;

    @Generated(GenerationTime.INSERT)
    @Column(name = "created_date_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자'")
    private Date createdDateTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "modifier_idx")
    private AdminMemberEntity modifier;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "modified_date_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정 일자'")
    private Date modifiedDateTime;


    @Builder
    public AdminRoleEntity(Long idx, AdminMemberEntity admin, RoleEntity role, AdminMemberEntity creator, AdminMemberEntity modifier) {
        this.idx = idx;
        this.admin = admin;
        this.role = role;
        this.creator = creator;
        this.modifier = modifier;
    }


}
