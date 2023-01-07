package com.planet.destiny.auth.service.module.member.model;


import com.planet.destiny.core.api.constant.YesNoType;
import com.planet.destiny.core.api.constant.member.AdminRoleType;
import com.planet.destiny.core.api.constant.member.RoleType;
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
@Table(schema = "destiny_planet_user", name = "tb_admin_role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", columnDefinition = "INT NOT NULL COMMENT 'idx'")
    private Long idx;

    @Column(name = "role", columnDefinition = "VARCHAR(30) NOT NULL COMMENT '권한(EX) SUPER_ADMIN, ADMIN, MANAGER, MEMBER, GUEST)")
    @Convert(converter = AdminRoleType.AdminRoleTypeAttributeConverter.class)
    private AdminRoleType role;

    @Column(name = "role_name", columnDefinition = "VARCHAR(20) NOT NULL COMMENT '권한명'")
    private String roleName;

    @Column(name = "role_desc", columnDefinition = "VARCHAR(100) NOT NULL COMMENT '권한 설명'")
    private String roleDesc;

    @Column(name = "limit_cnt", columnDefinition = "INT(3) NOT NULL DEFAULT 0 COMMENT '제한 인원(0일경우 제한 없음)'")
    private Integer limitCnt;

    @Column(name = "display_yn", columnDefinition = "CHAR(1) NOT NULL DEFAULT 'N' COMMENT '표시 여부'")
    @Convert(converter = YesNoType.YesNoTypeAttributeConverter.class)
    private YesNoType displayYn;

    @Column(name = "use_yn", columnDefinition = "CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용 여부'")
    @Convert(converter = YesNoType.YesNoTypeAttributeConverter.class)
    private YesNoType useYn;

    @Column(name = "delete_yn", columnDefinition = "CHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제 여부'")
    @Convert(converter = YesNoType.YesNoTypeAttributeConverter.class)
    private YesNoType deleteYn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_idx")
    private AdminMemberEntity creator;

    @Column(name = "created_date_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일자'")
    @Generated(GenerationTime.INSERT)
    private Date createdDateTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "modifier_idx")
    private AdminMemberEntity modifier;


    @Column(name = "modified_date_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정 일자'")
    @Generated(GenerationTime.ALWAYS)
    private Date modifiedDateTime;


    @Builder
    public RoleEntity(Long idx, AdminRoleType role, String roleName
            , String roleDesc, Integer limitCnt, YesNoType displayYn
            , YesNoType useYn, YesNoType deleteYn, AdminMemberEntity creator
            , AdminMemberEntity modifier) {
        this.idx = idx;
        this.role = role;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.limitCnt = limitCnt;
        this.displayYn = displayYn;
        this.useYn = useYn;
        this.deleteYn = deleteYn;
        this.creator = creator;
        this.modifier = modifier;
    }
}
