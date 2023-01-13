package com.planet.destiny.core.api.module.sender.repository;

import com.planet.destiny.core.api.constant.EmailTemplateType;
import com.planet.destiny.core.api.constant.YesNoType;
import com.planet.destiny.core.api.module.sender.model.EmailTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository(value = "emailTemplateRepository")
public interface EmailTemplateRepository extends JpaRepository<EmailTemplateEntity, Long> {

    Optional<EmailTemplateEntity> findByTemplateTypeAndUseYnAndAndDeleteYn(EmailTemplateType templateType, YesNoType useYn, YesNoType deleteYn);

}
