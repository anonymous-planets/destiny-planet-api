package com.planet.destiny.auth.service.module.member.repository;

import com.planet.destiny.auth.service.module.member.model.AdminMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "adminMemberRepository")
public interface AdminMemberRepository extends JpaRepository<AdminMemberEntity, Long> {

    Optional<AdminMemberEntity> findByIdx(Long idx);
    Optional<AdminMemberEntity> findByMemberId(String memberId);
}
