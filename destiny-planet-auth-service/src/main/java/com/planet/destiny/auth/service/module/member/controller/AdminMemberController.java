package com.planet.destiny.auth.service.module.member.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController(value = "adminMemberController")
@RequestMapping(value = "/v1/api/admin-member")
@RequiredArgsConstructor
public class AdminMemberController {

    @PostMapping(value = "/sign-up")
    public ResponseEntity signUp() {
        return null;
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity signIn() {
        return null;
    }
}
