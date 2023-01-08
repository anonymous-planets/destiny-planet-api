package com.planet.destiny.auth.service.module.member.model;

import com.planet.destiny.core.api.constant.SenderType;
import com.planet.destiny.core.api.constant.YesNoType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;


//@Slf4j
//@Getter
//@Entity
//@Table(schema = "destiny_planet_user", name = "tb_admin_invite")
public class AdminInviteEntity implements Serializable {

    private Long idx;
    private SenderType senderType;
    private String sender;
    private String receiverName;
    private String receiver;
    private String expireTime;
    private String expireDateTime;
    private YesNoType authYn;
    private AdminMemberEntity creator;
    private Date createdDateTime;
    private AdminMemberEntity modifier;
    private Date modifiedDateTime;




}
