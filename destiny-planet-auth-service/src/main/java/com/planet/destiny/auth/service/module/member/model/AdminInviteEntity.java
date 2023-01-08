package com.planet.destiny.auth.service.module.member.model;

import com.planet.destiny.core.api.constant.SenderType;
import com.planet.destiny.core.api.constant.YesNoType;
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

    @Column(name ="sender_type")
    @Convert(converter = SenderType.SenderTypeAttributeConverter.class)
    private SenderType senderType;

    @Column(name ="sender")
    private String sender;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "expire_time")
    private Integer expireTime;

    @Column(name = "expire_date_time")
    private Date expireDateTime;

    @Column(name = "auth_yn")
    @Convert(converter = YesNoType.YesNoTypeAttributeConverter.class)
    private YesNoType authYn;


    @Column(name = "send_yn")
    @Convert(converter = YesNoType.YesNoTypeAttributeConverter.class)
    private YesNoType sendYn;

    @Column(name = "send_date")
    private Date sendDate;

    @ManyToOne
    @JoinColumn(name = "created_id")
    private AdminMemberEntity creator;

    @Column(name = "created_date_time")
    @Generated(GenerationTime.INSERT)
    private Date createdDateTime;

    @ManyToOne
    @JoinColumn(name = "modified_id")
    private AdminMemberEntity modifier;

    @Column(name ="modified_date_time")
    @Generated(GenerationTime.ALWAYS)
    private Date modifiedDateTime;

    @Builder
    public AdminInviteEntity(Long idx, SenderType senderType, String sender
            , String receiverName, String receiver, Integer expireTime
            , Date expireDateTime, YesNoType authYn, YesNoType sendYn, Date sendDate
            , AdminMemberEntity creator, AdminMemberEntity modifier) {
        this.idx = idx;
        this.senderType = senderType;
        this.sender = sender;
        this.receiverName = receiverName;
        this.receiver = receiver;
        this.expireTime = expireTime;
        this.expireDateTime = expireDateTime;
        this.authYn = authYn;
        this.sendYn = sendYn;
        this.creator = creator;
        this.modifier = modifier;
    }

    public AdminInviteEntity updateSendYn(YesNoType sendYn) {
        this.sendYn = sendYn;
        this.sendDate = new Date();
        return this;
    }
}
