package com.planet.destiny.core.api.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.planet.destiny.core.api.utils.HTMLCharacterEscapes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class WebConfiguration {

    private final ObjectMapper objectMapper;

    /**
     * 전역 공통 Cors 설정
     * @return
     */
    @Bean
    public CorsConfiguration corsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 허용 Origin(Origin = URI Schema + hostname + port)
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:80"));

        // 허용 Method
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "PATCH", "DELETE"));

        // 허용 Header
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return configuration;
    }

    @Bean
    public MappingJackson2HttpMessageConverter jsonEscapeConverter() {
        ObjectMapper copy = objectMapper.copy();

        // ObjectMapper에 특수 문자 처리 기능 적용
        copy.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());

        // MessageConverter에 ObjectMapper 설정
        return new MappingJackson2HttpMessageConverter(copy);

    }
}
