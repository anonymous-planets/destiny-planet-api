package com.planet.destiny.auth.service.module.member.controller;


import com.planet.destiny.auth.service.module.member.item.AdminMemberDto;
import com.planet.destiny.auth.service.module.member.service.AdminMemberService;
import com.planet.destiny.core.api.module.sender.service.SenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 해당 Controller는 로그인, 회원가입과 관련된 API만 존재해야한다.
 * 이유 : Security에서 인증을 하지 않아도 되는 API로 설정해두었다
 */
@Slf4j
@RestController(value = "adminMemberController")
@RequestMapping(value = "/v1/api/admin-member")
@RequiredArgsConstructor
public class AdminMemberController {

    private final AdminMemberService adminMemberService;

    @PostMapping(value = "/login")
    public ResponseEntity login(@Validated @RequestBody AdminMemberDto.LoginReq reqDto) {
        return ResponseEntity.ok(adminMemberService.login(reqDto));
    }

    @PostMapping(value = "/invite")
    public ResponseEntity invite(@Validated @RequestBody AdminMemberDto.InviteReq reqDto) {
        return ResponseEntity.ok(adminMemberService.invite(reqDto));
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity signIn(@Validated @RequestBody AdminMemberDto.SignInReq reqDto) {
        return ResponseEntity.ok(adminMemberService.signIn(reqDto));
    }
}
