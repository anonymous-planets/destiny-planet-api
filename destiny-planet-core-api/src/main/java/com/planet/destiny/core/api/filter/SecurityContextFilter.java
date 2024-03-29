package com.planet.destiny.core.api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import reactor.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Gateway에서 설정한 token정보 꺼내 SecurityContext에 설정하는 역할
 */
public class SecurityContextFilter extends OncePerRequestFilter {

    private final String AUTHORIZATION_ID_HEADER = "X-Authorization-Id";
    private final String AUTHORIZATION_ROLE_HEADER = "X-Authorization-Role";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String memberIdx = resolveHeader(AUTHORIZATION_ID_HEADER, request);
        String roles = resolveHeader(AUTHORIZATION_ROLE_HEADER, request);

        if(StringUtils.hasText(String.valueOf(memberIdx)) && StringUtils.hasText(roles)) {
            // SecurityContextHolder에 인증정보 저장
            Collection<? extends GrantedAuthority> authorities = Arrays.stream(roles.split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            UserDetails principal = new User(memberIdx, "", authorities);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, "", authorities));
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Header에서 특정 값 꺼내오기
     * @param header
     * @param request
     * @return
     */
    private String resolveHeader(String header, HttpServletRequest request) {
        return request.getHeader(header);
    }
}
