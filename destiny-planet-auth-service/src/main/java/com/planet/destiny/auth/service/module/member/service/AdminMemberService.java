package com.planet.destiny.auth.service.module.member.service;


import com.planet.destiny.auth.service.constant.ErrorCodeAuth;
import com.planet.destiny.auth.service.exception.member.MemberNotFoundException;
import com.planet.destiny.auth.service.module.member.item.AdminMemberDto;
import com.planet.destiny.core.api.constant.EmailTemplateType;
import com.planet.destiny.core.api.constant.YesNoType;
import com.planet.destiny.core.api.exception.NotFoundException;
import com.planet.destiny.core.api.module.member.model.AdminInviteEntity;
import com.planet.destiny.core.api.module.member.model.AdminMemberEntity;
import com.planet.destiny.auth.service.module.member.repository.AdminInviteRepository;
import com.planet.destiny.auth.service.module.member.repository.AdminMemberRepository;
import com.planet.destiny.auth.service.module.token.service.TokenService;
import com.planet.destiny.core.api.constant.SenderType;
import com.planet.destiny.core.api.items.wrapper.response.RestEmptyResponse;
import com.planet.destiny.core.api.items.wrapper.response.RestSingleResponse;
import com.planet.destiny.core.api.module.sender.model.EmailTemplateEntity;
import com.planet.destiny.core.api.module.sender.repository.EmailTemplateRepository;
import com.planet.destiny.core.api.module.sender.service.EmailSender;
import com.planet.destiny.core.api.module.sender.service.SenderService;
import com.planet.destiny.core.api.module.token.item.TokenDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "adminMemberService")
@RequiredArgsConstructor
public class AdminMemberService {

    private final AdminMemberRepository adminMemberRepository;

    private final AdminInviteRepository adminInviteRepository;

    private final EmailTemplateRepository emailTemplateRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    private final SenderService senderService;

    @Value("${invite.admin.email.expire-time}")
    private String inviteExpireTime;

    @Value("${spring.mail.default-sender}")
    private String defaultSender;

    @Value("${spring.mail.default-username}")
    private String defaultName;


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
        AdminMemberEntity admin = adminMemberRepository.findByIdx(reqDto.getSenderIdx()).orElseThrow(() -> new MemberNotFoundException(ErrorCodeAuth.MEMBER_NOT_FOUND, "NOT FOUND ADMIN INFO", "보내는 관리자 정보를 찾을 수 없습니다."));

        EmailTemplateEntity template = emailTemplateRepository.findByTemplateTypeAndUseYnAndAndDeleteYn(EmailTemplateType.ADMIN_INVITE, YesNoType.YES, YesNoType.NO).orElseThrow(() -> new NotFoundException("이메일 템플릿을"));


        AdminInviteEntity invite = adminInviteRepository.save(
                AdminInviteEntity.builder()
                        .template(template)
                        .senderAddress(defaultSender)
                        .senderName(defaultName)
                        .receiverAddress(reqDto.getReceiverAddress())
                        .receiverName(reqDto.getReceiverName())
                        .expireTime(Integer.parseInt(inviteExpireTime))
                        .expireDateTime(new Date(new Date().getTime() + Long.parseLong(inviteExpireTime)))
                        .authYn(YesNoType.NO)
                        .sendYn(YesNoType.NO)
                        .creator(admin)
                        .modifier(admin)
                        .build()
        );

        // 초대 메일 전송
        senderService.send(reqDto.toEmailDto(invite.getSenderName(), invite.getSenderAddress(), template.getFilePath(), template.getTemplateParams()), sendYn -> {
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
