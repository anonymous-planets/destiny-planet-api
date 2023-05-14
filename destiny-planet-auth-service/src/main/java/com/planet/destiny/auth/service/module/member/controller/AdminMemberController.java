package com.planet.destiny.auth.service.module.member.controller;


import com.planet.destiny.auth.service.module.member.item.AdminMemberDto;
import com.planet.destiny.auth.service.module.member.service.AdminMemberService;
import com.planet.destiny.core.api.utils.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 로그인
     * @param reqDto
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseEntity login(@Validated @RequestBody AdminMemberDto.LoginReq reqDto, HttpServletRequest request) {
        return ResponseEntity.ok(adminMemberService.login(reqDto, request));
    }

    /**
     * 회원 가입 이메일 전송
     * @param reqDto
     * @return
     */
    @PostMapping(value = "/invite")
    public ResponseEntity invite(@Validated @RequestBody AdminMemberDto.InviteReq reqDto) {
        return ResponseEntity.ok(adminMemberService.invite(reqDto));
    }

    /**
     * 회원 가입 페이지 진입전 체크
     * @param reqDto
     * @return
     */
    @GetMapping(value = "/invite/{inviteIdx}")
    public ResponseEntity signUpCheck(@PathVariable Long inviteIdx, AdminMemberDto.SignUpCheckReq reqDto) {
        return ResponseEntity.ok(adminMemberService.signUpCheck(reqDto));
    }

    /**
     * 회원 가입
     * @param reqDto
     * @return
     */
    @PostMapping(value = "/sign-up")
    public ResponseEntity signUp(@Validated @RequestBody AdminMemberDto.SignUpReq reqDto) {
        return ResponseEntity.ok(adminMemberService.signUp(reqDto));
    }
}
