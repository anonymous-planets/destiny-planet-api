package com.planet.destiny.auth.service.module.token.repository;


import com.planet.destiny.auth.service.module.token.model.AdminTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "adminTokenRepository")
public interface AdminTokenRepository extends JpaRepository<AdminTokenEntity, Long> {
}
