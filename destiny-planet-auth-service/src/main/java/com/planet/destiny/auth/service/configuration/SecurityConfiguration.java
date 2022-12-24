package com.planet.destiny.auth.service.configuration;

import com.planet.destiny.core.api.config.security.AccessDeniedHandler;
import com.planet.destiny.core.api.config.security.AuthenticationEntryPoint;
import com.planet.destiny.core.api.filter.SecurityContextFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfiguration(final AccessDeniedHandler accessDeniedHandler, final AuthenticationEntryPoint authenticationEntryPoint) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                // X-Frame-Options이 Http hedaer 추가되어 iframe 외부 프레임 접근이 가능하도록 외부프레임 거부를 해제
                .headers().frameOptions().disable()
                .and()
                .headers().frameOptions().sameOrigin().disable()
                // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless로 설정
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/v1/api/signin", "/v1/api/signup", "/v1/api/token/**/re-issue").permitAll()   // FIXME : re-issue는 필인증 없도록 설정 필요
                .anyRequest().authenticated() // 나머지 API는 전부 인증 필요
                // Exception Handling
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .addFilterBefore(new SecurityContextFilter(), UsernamePasswordAuthenticationFilter.class)
                .build()
                ;
    }
}
