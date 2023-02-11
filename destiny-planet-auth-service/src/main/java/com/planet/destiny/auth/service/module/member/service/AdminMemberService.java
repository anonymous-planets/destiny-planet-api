package com.planet.destiny.auth.service.module.member.service;


import com.planet.destiny.auth.service.constant.ErrorCodeAuth;
import com.planet.destiny.auth.service.exception.member.MemberNotFoundException;
import com.planet.destiny.auth.service.module.member.item.AdminMemberDto;
import com.planet.destiny.core.api.constant.EmailTemplateType;
import com.planet.destiny.core.api.constant.YesNoType;
import com.planet.destiny.core.api.exception.BadRequestException;
import com.planet.destiny.core.api.exception.BusinessException;
import com.planet.destiny.core.api.exception.NotFoundException;
import com.planet.destiny.core.api.module.member.model.AdminInviteEntity;
import com.planet.destiny.core.api.module.member.model.AdminMemberEntity;
import com.planet.destiny.auth.service.module.member.repository.AdminInviteRepository;
import com.planet.destiny.auth.service.module.member.repository.AdminMemberRepository;
import com.planet.destiny.auth.service.module.token.service.TokenService;
import com.planet.destiny.core.api.constant.SenderType;
import com.planet.destiny.core.api.items.wrapper.response.RestEmptyResponse;
import com.planet.destiny.core.api.items.wrapper.response.RestSingleResponse;
import com.planet.destiny.core.api.module.sender.item.EmailDto;
import com.planet.destiny.core.api.module.sender.model.EmailTemplateEntity;
import com.planet.destiny.core.api.module.sender.repository.EmailTemplateRepository;
import com.planet.destiny.core.api.module.sender.service.SenderService;
import com.planet.destiny.core.api.module.token.item.TokenDto;
import com.planet.destiny.core.api.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
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
    private String defaultSenderAddress;

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
        // 관리자 조회
        AdminMemberEntity admin = adminMemberRepository.findByIdx(reqDto.getSenderIdx()).orElseThrow(() -> new MemberNotFoundException(ErrorCodeAuth.MEMBER_NOT_FOUND, "NOT FOUND ADMIN INFO", "보내는 관리자 정보를 찾을 수 없습니다."));

        // Email Template 조회
        EmailTemplateEntity template = emailTemplateRepository.findByTemplateTypeAndUseYnAndAndDeleteYn(EmailTemplateType.ADMIN_INVITE, YesNoType.YES, YesNoType.NO).orElseThrow(() -> new NotFoundException("이메일 템플릿을"));

        Optional<AdminInviteEntity> inviteLog = adminInviteRepository.findByReceiverAddressAndSendYnAndExpireDateTimeAfter(reqDto.getReceiverAddress(), YesNoType.YES, new Date());

        if(inviteLog.isPresent()) {
            if(YesNoType.YES.equals(inviteLog.get().getAuthYn())) {
                throw new BadRequestException( "이미 가입 된 메일 주소", "해당 메일 주소는 가입 이력이 있습니다.") ;
            }
            throw new BadRequestException("가입 요청된 이메일 주소.", "해당 메일에 이미 가입 요청 메일을 전송했습니다.");
        }

        // 초대 이력 내용 저장
        AdminInviteEntity invite = adminInviteRepository.save(
                AdminInviteEntity.builder()
                        .identityCode(StringUtils.generateUUID())
                        .template(template)
                        .senderAddress(defaultSenderAddress)
                        .senderName(defaultName)
                        .receiverAddress(reqDto.getReceiverAddress())
                        .receiverName(reqDto.getReceiverName())
                        .expireTime(Integer.parseInt(inviteExpireTime))
                        .expireDateTime(new Date(new Date().getTime() + (long) ( 1000 * 60 * 60 * 24 ) ))
                        .authYn(YesNoType.NO)
                        .sendYn(YesNoType.NO)
                        .creator(admin)
                        .modifier(admin)
                        .build()
        );

        // 초대 메일 전송
        senderService.send(EmailDto
                .builder()
                .senderType(SenderType.EMAIL)
                .fromInfo(EmailDto.PersonInfo.builder().name(defaultName).address(defaultSenderAddress).build())
                .toInfo(EmailDto.PersonInfo.builder().name(reqDto.getReceiverName()).address(reqDto.getReceiverAddress()).build())
                .subject(template.getSubject())
                .isUseTemplate(true)
                .templateFileName(template.getFilePath())
                .params(StringUtils.templateParams(template.getTemplateParams(), reqDto))
                .build(), sendYn -> {
            // 내용 콜백
            adminInviteRepository.save(invite.updateSendYn(sendYn));
        });

        return RestEmptyResponse.success("회원 초대가 정상적으로 이루어졌습니다.");
    }


    @Transactional
    public RestSingleResponse signUpCheck(AdminMemberDto.SignUpCheckReq reqDto) {
        AdminInviteEntity invite = adminInviteRepository.findByIdxAndIdentityCode(reqDto.getInviteIdx(), reqDto.getIdentityCode()).orElseThrow(() -> new NotFoundException("초대 정보를"));

        Date now = new Date();
        if(invite.getExpireDateTime().before(now)) {
            throw new BadRequestException("가입 가능한 시간을 초과했습니다. 다시 초대해 주세요.");
        }

        if(YesNoType.YES.equals(invite.getAuthYn())) {
            throw new BadRequestException("이미 가입된 이메일입니다. 다른 이메일로 다시 초대해 주세요.");
        }


        return RestSingleResponse.success("가입 가능한 URL 입니다.", AdminMemberDto.SignUpCheckRes.builder().email(invite.getReceiverAddress()).build());
    }

    @Transactional
    public RestEmptyResponse signUp(AdminMemberDto.SignUpReq reqDto) {
        // validation

        // tb_admin_invite테이블에 auth_yn값 Y로 Update
        //        adminInviteRepository.save(invite.updateAuthYn());


        // tb_admin_member테이블에 Insert


        return RestEmptyResponse.success();
    }
}
