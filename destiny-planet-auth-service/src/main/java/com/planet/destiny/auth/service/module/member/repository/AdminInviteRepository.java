package com.planet.destiny.auth.service.module.member.repository;

import com.planet.destiny.core.api.module.member.model.AdminInviteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "adminInviteRepository")
public interface AdminInviteRepository extends JpaRepository<AdminInviteEntity, Long> {

}
