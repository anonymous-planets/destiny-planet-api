package com.planet.destiny.core.api.module.sender.model;

import com.planet.destiny.core.api.constant.EmailTemplateType;
import com.planet.destiny.core.api.constant.YesNoType;
import com.planet.destiny.core.api.module.member.model.AdminMemberEntity;
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
@Table(schema = "destiny_planet_user", name = "tb_email_template")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailTemplateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "template_type", columnDefinition = "CHAR(2) NOT NULL COMMENT '이메일 템플릿 타입'")
    @Convert(converter = EmailTemplateType.EmailTemplateTypeAttributeConverter.class)
    private EmailTemplateType templateType;

    @Column(name = "subject", columnDefinition = "VARCHAR(100) NOT NULL COMMENT '해당 템플릿 이메일 제목'")
    private String subject;

    @Column(name = "template_params", columnDefinition = "VARCHAR(400) COMMENT '해당 템플릿 파라미터 JSON형태 문자열'")
    private String templateParams;

    @Column(name = "file_path", columnDefinition = "VARCHAR(100) NOT NULL COMMENT '템플릿 파일 경로 + 파일 명 + 파일 확장자'")
    private String filePath;

    @Column(name = "use_yn", columnDefinition = "CHAR(1) NOT NULL DEFAULT 'N' COMMENT '템플릿 사용 여부")
    @Convert(converter = YesNoType.YesNoTypeAttributeConverter.class)
    private YesNoType useYn;

    @Column(name = "delete_yn", columnDefinition = "CHAR(1) NOT NULL DEFAULT 'N' COMMENT '템플릿 삭제 여부")
    @Convert(converter = YesNoType.YesNoTypeAttributeConverter.class)
    private YesNoType deleteYn;

    @ManyToOne
    @JoinColumn(name = "creator_idx", columnDefinition = "INT NOT NULL COMMENT '생성자'")
    private AdminMemberEntity creator;

    @Column(name = "created_date_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자'")
    @Generated(GenerationTime.INSERT)
    private Date createdDateTime;

    @ManyToOne
    @JoinColumn(name = "modifier_idx", columnDefinition = "INT NOT NULL COMMENT '수정자'")
    private AdminMemberEntity modifier;

    @Column(name ="modified_date_time", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자'")
    @Generated(GenerationTime.ALWAYS)
    private Date modifiedDateTime;


    @Builder
    public EmailTemplateEntity(Long idx, EmailTemplateType templateType, String subject
            , String templateParams, String filePath, YesNoType useYn
            , YesNoType deleteYn, AdminMemberEntity creator, AdminMemberEntity modifier) {
        this.idx = idx;
        this.templateType = templateType;
        this.subject = subject;
        this.templateParams = templateParams;
        this.filePath = filePath;
        this.useYn = useYn;
        this.deleteYn = deleteYn;
        this.creator = creator;
        this.modifier = modifier;
    }
}
