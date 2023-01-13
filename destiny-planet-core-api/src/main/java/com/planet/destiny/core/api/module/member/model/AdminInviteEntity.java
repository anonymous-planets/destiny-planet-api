package com.planet.destiny.core.api.module.member.model;

import com.planet.destiny.core.api.constant.SenderType;
import com.planet.destiny.core.api.constant.YesNoType;
import com.planet.destiny.core.api.module.sender.model.EmailTemplateEntity;
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
@Table(schema = "destiny_planet_user", name = "tb_admin_invite")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminInviteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "template_idx", columnDefinition = "INT COMMENT '템플릿 파일 IDX'")
    private EmailTemplateEntity template;

    @Column(name ="sender_address", columnDefinition = "VARCHAR(100) NOT NULL COMMENT '발신자 EMAIL'")
    private String senderAddress;

    @Column(name = "sender_name", columnDefinition = "VARCHAR(40) NOT NULL COMMENT '발신자 이름'")
    private String senderName;

    @Column(name = "receiver_address", columnDefinition = "VARCHAR(100) NOT NULL COMMENT '수신자 EMAIL'")
    private String receiverAddress;

    @Column(name = "receiver_name", columnDefinition = "VARCHAR(40) NOT NULL COMMENT '수신자 이름'")
    private String receiverName;

    @Column(name = "expire_time", columnDefinition = "INT(2) NOT NULL COMMENT '만료 시간(분)'")
    private Integer expireTime;

    @Column(name = "expire_date_time", columnDefinition = "DATETIME NOT NULL COMMENT '만료 일자 yyyy-MM-dd HH:mm:ss'")
    private Date expireDateTime;

    @Column(name = "auth_yn", columnDefinition = "CHAR(1) NOT NULL DEFAULT 'N' COMMENT '인증 여부'")
    @Convert(converter = YesNoType.YesNoTypeAttributeConverter.class)
    private YesNoType authYn;

    @Column(name = "send_yn", columnDefinition = "CHAR(1) NOT NULL DEFAULT 'N' COMMENT '전송 여부'")
    @Convert(converter = YesNoType.YesNoTypeAttributeConverter.class)
    private YesNoType sendYn;

    @Column(name = "send_date", columnDefinition = "DATETIME COMMENT '전송 일자'")
    private Date sendDate;

    @ManyToOne
    @JoinColumn(name = "creator_idx", columnDefinition = "INT NOT NULL COMMENT '생성자 IDX'")
    private AdminMemberEntity creator;

    @Column(name = "created_date_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일자'")
    @Generated(GenerationTime.INSERT)
    private Date createdDateTime;

    @ManyToOne
    @JoinColumn(name = "modifier_idx", columnDefinition = "INT NOT NULL COMMENT '수정자 IDX'")
    private AdminMemberEntity modifier;

    @Column(name ="modified_date_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정 일자'")
    @Generated(GenerationTime.ALWAYS)
    private Date modifiedDateTime;


    @Builder
    public AdminInviteEntity(Long idx, EmailTemplateEntity template
            , String senderAddress, String senderName
            , String receiverAddress, String receiverName
            , Integer expireTime, Date expireDateTime
            , YesNoType authYn, YesNoType sendYn
            , Date sendDate, AdminMemberEntity creator, AdminMemberEntity modifier
    ) {

        this.idx = idx;
        this.template = template;
        this.senderAddress = senderAddress;
        this.senderName = senderName;
        this.receiverAddress = receiverAddress;
        this.receiverName = receiverName;
        this.expireTime = expireTime;
        this.expireDateTime = expireDateTime;
        this.authYn = authYn;
        this.sendYn = sendYn;
        this.sendDate = sendDate;
        this.creator = creator;
        this.modifier = modifier;
    }

    public AdminInviteEntity updateSendYn(YesNoType sendYn) {
        this.sendYn = sendYn;
        this.sendDate = new Date();
        return this;
    }
}
