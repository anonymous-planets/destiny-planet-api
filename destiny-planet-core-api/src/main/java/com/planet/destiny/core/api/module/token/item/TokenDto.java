package com.planet.destiny.core.api.module.token.item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public class TokenDto {


    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TokenReissueReq implements Serializable {
        private String refreshToken;

        @Builder
        public TokenReissueReq(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TokenRes implements Serializable {

        private String grantType;

        private String accessToken;             // accessToken

        private Long accessTokenExpiresIn;      // accessToken 만료 일자

        private String refreshToken;            // refreshToken

        private Long refreshTokenExpiresIn;     // refreshToken 만료 일자

        @Builder
        public TokenRes(String grantType, String accessToken, Long accessTokenExpiresIn, String refreshToken, Long refreshTokenExpiresIn) {
            this.grantType = grantType;
            this.accessToken = accessToken;
            this.accessTokenExpiresIn = accessTokenExpiresIn;
            this.refreshToken = refreshToken;
            this.refreshTokenExpiresIn = refreshTokenExpiresIn;
        }

        public TokenRes setAccessToken(String accessToken, Long accessTokenExpiresIn) {
            this.accessToken = accessToken;
            this.accessTokenExpiresIn = accessTokenExpiresIn;
            return this;
        }

        public TokenRes setRefreshToken(String refreshToken, Long refreshTokenExpiresIn) {
            this.refreshToken = refreshToken;
            this.refreshTokenExpiresIn = refreshTokenExpiresIn;
            return this;
        }
    }
}
