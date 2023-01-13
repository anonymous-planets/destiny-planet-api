package com.planet.destiny.auth.service.module.token.repository;


import com.planet.destiny.core.api.module.member.model.AdminMemberEntity;
import com.planet.destiny.auth.service.module.token.model.AdminTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "adminTokenRepository")
public interface AdminTokenRepository extends JpaRepository<AdminTokenEntity, Long> {
    Optional<AdminTokenEntity> findByAdmin(AdminMemberEntity adminMemberEntity);
}
