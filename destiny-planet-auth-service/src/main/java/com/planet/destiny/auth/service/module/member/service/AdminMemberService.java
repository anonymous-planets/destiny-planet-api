package com.planet.destiny.auth.service.module.member.service;


import com.planet.destiny.auth.service.module.member.item.AdminMemberDto;
import com.planet.destiny.core.api.items.wrapper.response.RestEmptyResponse;
import com.planet.destiny.core.api.items.wrapper.response.RestSingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "adminMemberService")
@RequiredArgsConstructor
public class AdminMemberService {

    public RestSingleResponse<AdminMemberDto.LoginRes> login(AdminMemberDto.LoginReq reqDto) {
        return null;
    }

    public RestEmptyResponse signIn(AdminMemberDto.SignInReq reqDto) {
        return RestEmptyResponse.success();
    }


}
