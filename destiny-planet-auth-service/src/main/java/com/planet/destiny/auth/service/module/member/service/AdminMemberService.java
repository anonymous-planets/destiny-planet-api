package com.planet.destiny.auth.service.module.member.service;


import com.planet.destiny.auth.service.constant.ErrorCodeAuth;
import com.planet.destiny.auth.service.exception.member.MemberNotFoundException;
import com.planet.destiny.auth.service.module.member.item.AdminMemberDto;
import com.planet.destiny.auth.service.module.member.model.AdminInviteEntity;
import com.planet.destiny.auth.service.module.member.model.AdminMemberEntity;
import com.planet.destiny.auth.service.module.member.repository.AdminInviteRepository;
import com.planet.destiny.auth.service.module.member.repository.AdminMemberRepository;
import com.planet.destiny.auth.service.module.token.service.TokenService;
import com.planet.destiny.core.api.constant.SenderType;
import com.planet.destiny.core.api.items.wrapper.response.RestEmptyResponse;
import com.planet.destiny.core.api.items.wrapper.response.RestSingleResponse;
import com.planet.destiny.core.api.module.sender.item.EmailDto;
import com.planet.destiny.core.api.module.sender.item.SenderDto;
import com.planet.destiny.core.api.module.sender.service.SenderCallback;
import com.planet.destiny.core.api.module.sender.service.SenderService;
import com.planet.destiny.core.api.module.token.item.TokenDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "adminMemberService")
@RequiredArgsConstructor
public class AdminMemberService {

    private final AdminMemberRepository adminMemberRepository;

    private final AdminInviteRepository adminInviteRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    private final SenderService senderService;

    @Value("${invite.admin.email.expire-time}")
    private String inviteExpireTime;

    @Transactional
    public RestSingleResponse<AdminMemberDto.LoginRes> login(AdminMemberDto.LoginReq reqDto) {
        AdminMemberEntity admin = adminMemberRepository.findByMemberId(reqDto.getMemberId()).orElseThrow(() -> new MemberNotFoundException(ErrorCodeAuth.LOGIN_ERROR, "????????? ??????", "???????????? ???????????? ????????????."));

        if(!passwordEncoder.matches(reqDto.getPassword(), admin.getPassword())) {
            throw new MemberNotFoundException(ErrorCodeAuth.LOGIN_ERROR, "????????? ??????", "??????????????? ???????????? ????????????.");
        }

        TokenDto.TokenRes token = tokenService.adminTokenIssue(TokenDto.TokenIssueReq.builder().memberIdx(admin.getIdx()).roles(admin.getRoles().stream().map(item -> item.getRole().getRole()).collect(Collectors.toSet())).build());

        return RestSingleResponse.success(AdminMemberDto.LoginRes.builder().token(token).build());
    }

    @Transactional
    public RestEmptyResponse invite(AdminMemberDto.InviteReq reqDto) {
        // DB??? ?????? ??????
        AdminMemberEntity admin = adminMemberRepository.findByIdx(reqDto.getCreatorIdx()).orElseThrow(() -> new MemberNotFoundException(ErrorCodeAuth.MEMBER_NOT_FOUND, "NOT FOUND ADMIN INFO", "????????? ????????? ????????? ?????? ??? ????????????."));

        AdminInviteEntity invite = adminInviteRepository.save(
                AdminInviteEntity.builder()
                        .senderType(SenderType.EMAIL)
                        .sender(reqDto.getFromInfo().getName())
                        .receiverName(reqDto.getToInfo().getName())
                        .receiver(reqDto.getToInfo().getAddress())
                        .expireTime(Integer.parseInt(inviteExpireTime))
                        .expireDateTime(new Date(new Date().getTime() + Long.parseLong(inviteExpireTime)))
                        .creator(admin)
                        .modifier(admin)
                        .build()
        );

        // ?????? ?????? ??????
        senderService.send(reqDto, sendYn -> {
            // ?????? ??????
            adminInviteRepository.save(invite.updateSendYn(sendYn));
        });

        return RestEmptyResponse.success("?????? ????????? ??????????????? ?????????????????????.");
    }

    @Transactional
    public RestEmptyResponse signIn(AdminMemberDto.SignInReq reqDto) {
        return RestEmptyResponse.success();
    }
}
