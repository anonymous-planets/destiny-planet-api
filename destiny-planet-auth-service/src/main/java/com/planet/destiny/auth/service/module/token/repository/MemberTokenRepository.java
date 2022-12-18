package com.planet.destiny.auth.service.module.token.repository;

import com.planet.destiny.auth.service.module.token.model.MemberTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository(value = "memberTokenRepository")
public interface MemberTokenRepository extends JpaRepository<MemberTokenEntity, Integer> {
}
