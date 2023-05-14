package com.planet.destiny.core.api.config;

import com.planet.destiny.core.api.config.resolver.CustomCookieLocaleResolver;
import jakarta.servlet.ServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;

import java.time.Duration;
import java.util.Locale;


@Slf4j
@Configuration
public class MessageConfiguration {

  @Bean
  public MessageSource messageSource() {

    // 지정한 시간마다 다시 리로드 하도록 한다.
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

    // 언어 리소스들이 있는 경로를 지정한다.
    messageSource.setBasenames("classpath:/messages/");

    // Encoding 형식
    messageSource.setDefaultEncoding("UTF-8");

    // 기본 Locale
    messageSource.setDefaultLocale(Locale.getDefault());

    // Reload시간
    messageSource.setCacheSeconds(60 * 60 * 5);

    // locale에 해당하는 file이 없을 경우 System locale을 사용
    messageSource.setFallbackToSystemLocale(false);

    return messageSource;
  }

  @Bean
  public MessageSourceAccessor messageSourceAccessor() {
    return new MessageSourceAccessor(messageSource());
  }

  @Bean
  public LocaleResolver localeResolver() {
    CustomCookieLocaleResolver resolver = new CustomCookieLocaleResolver();
    resolver.setDefaultLocaleFunction(ServletRequest::getLocale);
    resolver.setCookieDomain("userLocale");
    resolver.setCookieMaxAge(Duration.ofDays(1L));
    resolver.setCookieHttpOnly(true);
    return resolver;
  }
}
