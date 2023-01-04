package com.planet.destiny.core.api.module.token.item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public class TokenDto {


    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TokenIssueReq implements Serializable {
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

        private String refreshToken;

        private Long refreshTokenExpiresIn;


        @Builder
        public TokenRes(String grantType, String accessToken, Long accessTokenExpiresIn, String refreshToken, Long refreshTokenExpiresIn) {
            this.grantType = grantType;
            this.accessToken = accessToken;
            this.accessTokenExpiresIn = accessTokenExpiresIn;
            this.refreshToken = refreshToken;
            this.refreshTokenExpiresIn = refreshTokenExpiresIn;
        }

        public TokenRes setAccessTokenInfo(String accessToken, Long accessTokenExpiresIn) {
            this.accessToken = accessToken;
            this.accessTokenExpiresIn = accessTokenExpiresIn;
            return this;
        }

        public TokenRes setRefreshTokenInfo(String refreshToken, Long refreshTokenExpiresIn) {
            this.refreshToken = refreshToken;
            this.refreshTokenExpiresIn = refreshTokenExpiresIn;
            return this;
        }
    }
}
