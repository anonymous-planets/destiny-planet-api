package com.planet.destiny.auth.service.module.member.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController(value = "memberController")
@RequestMapping(value = "/v1/api/member")
@RequiredArgsConstructor
public class MemberController {
}
