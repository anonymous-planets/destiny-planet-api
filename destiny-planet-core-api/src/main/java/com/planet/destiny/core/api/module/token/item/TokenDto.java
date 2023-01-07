package com.planet.destiny.core.api.module.token.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.planet.destiny.core.api.constant.member.AdminRoleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TokenDto {


    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TokenIssueReq implements Serializable {
        private Long memberIdx;
        private Set<AdminRoleType> roles = new HashSet<>();

        private Long currentDateTime = new Date().getTime();

        @Builder
        public TokenIssueReq(Long memberIdx, Set<AdminRoleType> roles) {
            this.memberIdx = memberIdx;
            this.roles = roles;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TokenReIssueReq implements Serializable {
        private String refreshToken;

        @Builder
        public TokenReIssueReq(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TokenRes implements Serializable {

        private String grantType;
        private String accessToken;
        private Long accessTokenExpiresIn;
        private Long accessTokenExpireTime;
        private String refreshToken;
        private Long refreshTokenExpiresIn;
        private Long refreshTokenExpireTime;

        @Builder
        public TokenRes(String grantType, String accessToken, Long accessTokenExpiresIn, Long accessTokenExpireTime, String refreshToken, Long refreshTokenExpiresIn, Long refreshTokenExpireTime) {
            this.grantType = grantType;
            this.accessToken = accessToken;
            this.accessTokenExpiresIn = accessTokenExpiresIn;
            this.accessTokenExpireTime = accessTokenExpireTime;
            this.refreshToken = refreshToken;
            this.refreshTokenExpiresIn = refreshTokenExpiresIn;
            this.refreshTokenExpireTime = refreshTokenExpireTime;
        }

        public void setGrantType(String grantType) {
            this.grantType = grantType;
        }

        public TokenRes setAccessTokenInfo(String accessToken, Long accessTokenExpiresIn, Long accessTokenExpireTime) {
            this.accessToken = accessToken;
            this.accessTokenExpiresIn = accessTokenExpiresIn;
            this.accessTokenExpireTime = accessTokenExpireTime;
            return this;
        }

        public TokenRes setRefreshTokenInfo(String refreshToken, Long refreshTokenExpiresIn, Long refreshTokenExpireTime) {
            this.refreshToken = refreshToken;
            this.refreshTokenExpiresIn = refreshTokenExpiresIn;
            this.refreshTokenExpireTime = refreshTokenExpireTime; // 일
            return this;
        }
    }
}
