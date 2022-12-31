package com.planet.destiny.auth.service.module.token.controller;


import com.planet.destiny.auth.service.exception.token.TokenNotExistsException;
import com.planet.destiny.auth.service.module.token.service.TokenService;
import com.planet.destiny.core.api.module.token.item.TokenDto;
import com.planet.destiny.core.api.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController(value = "tokenController")
@RequestMapping(value = "/v1/api/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;


    @PostMapping("/admin/re-issue")
    public ResponseEntity adminTokenReissue(@CookieValue(value="refreshToken")TokenDto.TokenReissueReq reqDto) {
        if(StringUtils.isEmpty(reqDto.getRefreshToken())) {
            throw new TokenNotExistsException("refreshToken이 존재 하지 않습니다.", "문제가 발생했습니다. 다시 로그인해주세요.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tokenService.adminTokenReissue(reqDto));
    }
}
