package com.planet.destiny.auth.service.module.member.model;

import com.planet.destiny.core.api.constant.YesNoType;
import com.planet.destiny.core.api.module.member.model.AdminMemberEntity;
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
@Table(schema = "destiny_planet_user", name ="tb_admin_login_record")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminLoginRecordEntity implements Serializable {

  @Id
  @Column(name = "idx")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idx;

  @JoinColumn(name = "member_idx")
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private AdminMemberEntity member;

  @Column(name = "ip")
  private String ip;

  @Column(name = "success_yn")
  @Convert(converter = YesNoType.YesNoTypeAttributeConverter.class)
  private YesNoType successYn;

  @Column(name = "fail_reason")
  private String failReason;

  @JoinColumn(name = "creator_idx")
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private AdminMemberEntity creator;

  @Column(name = "created_date_time", columnDefinition = "DATETIME NOT NULL COMMENT '최초 생성 일자'")
  private Date createdDateTime;

  @JoinColumn(name = "modifier_idx")
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private AdminMemberEntity modifier;

  @Column(name = "modified_date_time", columnDefinition = "DATETIME NOT NULL COMMENT '최종 수정 일자'")
  private Date modifiedDateTime;


  @Builder
  public AdminLoginRecordEntity(Long idx, AdminMemberEntity member
      , String ip, YesNoType successYn, String failReason
      , AdminMemberEntity creator, Date createdDateTime
      , AdminMemberEntity modifier, Date modifiedDateTime) {
    this.idx = idx;
    this.member = member;
    this.ip = ip;
    this.successYn = successYn;
    this.failReason = failReason;
    this.creator = creator;
    this.createdDateTime = createdDateTime;
    this.modifier = modifier;
    this.modifiedDateTime = modifiedDateTime;
  }
}
