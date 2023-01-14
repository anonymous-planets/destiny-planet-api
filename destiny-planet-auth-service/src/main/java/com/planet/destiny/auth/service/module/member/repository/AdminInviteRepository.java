package com.planet.destiny.auth.service.module.member.repository;

import com.planet.destiny.core.api.constant.YesNoType;
import com.planet.destiny.core.api.module.member.model.AdminInviteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository(value = "adminInviteRepository")
public interface AdminInviteRepository extends JpaRepository<AdminInviteEntity, Long> {

    Optional<AdminInviteEntity> findByReceiverAddressAndSendYnAndExpireDateTimeAfter(String receiverAddress, YesNoType sendYn, Date now);
    Optional<AdminInviteEntity> findByIdxAndIdentityCode(Long idx, String identityCode);

}
