package com.planet.destiny.core.api.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageUtils {

  private final MessageSource initMessageSource;

  private static MessageSource messageSource;

  @PostConstruct
  private void init() {
    this.messageSource = this.initMessageSource;
  }

  public static String getMessage(String key) {
    return getMessage(key, null);
  }

  public static String getMessage(String key, Object[] args) {
    String message = null;

    if(key == null) {
      return null;
    }

    try {
      if(messageSource == null) {
        throw new NullPointerException();
      }
      message = messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }catch(NoSuchMessageException e) {
      log.error(e.getMessage());
      message = key;
    }
    return message;
  }
}
