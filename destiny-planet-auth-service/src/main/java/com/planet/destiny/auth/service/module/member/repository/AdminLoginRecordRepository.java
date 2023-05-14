package com.planet.destiny.auth.service.module.member.repository;


import com.planet.destiny.auth.service.module.member.model.AdminLoginRecordEntity;
import com.planet.destiny.core.api.constant.YesNoType;
import com.planet.destiny.core.api.module.member.model.AdminMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AdminLoginRecordRepository extends JpaRepository<AdminLoginRecordEntity, Long> {
  Long countByMemberAndSuccessYnAndCreatedDateTimeBetween(AdminMemberEntity member, YesNoType successYn, LocalDateTime start, LocalDateTime end);
}
