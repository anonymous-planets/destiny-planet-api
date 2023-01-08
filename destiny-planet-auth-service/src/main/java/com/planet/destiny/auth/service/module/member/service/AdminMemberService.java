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

    @Transactional
    public RestSingleResponse<AdminMemberDto.LoginRes> login(AdminMemberDto.LoginReq reqDto) {
        AdminMemberEntity admin = adminMemberRepository.findByMemberId(reqDto.getMemberId()).orElseThrow(() -> new MemberNotFoundException(ErrorCodeAuth.LOGIN_ERROR, "로그인 실패", "아이디가 올바르지 않습니다."));

        if(!passwordEncoder.matches(reqDto.getPassword(), admin.getPassword())) {
            throw new MemberNotFoundException(ErrorCodeAuth.LOGIN_ERROR, "로그인 실패", "비밀빈호가 올바르지 않습니다.");
        }

        TokenDto.TokenRes token = tokenService.adminTokenIssue(TokenDto.TokenIssueReq.builder().memberIdx(admin.getIdx()).roles(admin.getRoles().stream().map(item -> item.getRole().getRole()).collect(Collectors.toSet())).build());

        return RestSingleResponse.success(AdminMemberDto.LoginRes.builder().token(token).build());
    }

    @Transactional
    public RestEmptyResponse invite(AdminMemberDto.InviteReq reqDto) {
        // DB에 내용 저장
        Date now = new Date();
        AdminMemberEntity admin = adminMemberRepository.findByIdx(1L).orElseGet(null);
        AdminInviteEntity invite = adminInviteRepository.save(AdminInviteEntity.builder().senderType(reqDto.getSender()).creator(admin).modifier(admin).build());

        // 초대 메일 전송
        senderService.send(null, sendYn -> {
            // 내용 콜백
            adminInviteRepository.save(invite.updateSendYn(sendYn));
        });

        return RestEmptyResponse.success("회원 초대가 정상적으로 이루어졌습니다.");
    }

    @Transactional
    public RestEmptyResponse signIn(AdminMemberDto.SignInReq reqDto) {
        return RestEmptyResponse.success();
    }
}
