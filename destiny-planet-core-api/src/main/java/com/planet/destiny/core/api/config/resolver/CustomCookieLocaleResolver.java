package com.planet.destiny.core.api.config.resolver;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;
import java.util.function.Function;

public class CustomCookieLocaleResolver extends CookieLocaleResolver {

  @Override
  public void setDefaultLocaleFunction(Function<HttpServletRequest, Locale> defaultLocaleFunction) {
    defaultLocaleFunction = request -> {
      Locale defaultLocale = Locale.KOREA;
      if(this.getDefaultLocale() == null) {
        setDefaultLocale(defaultLocale);
        return defaultLocale;
      }
      return defaultLocale;
    };
    super.setDefaultLocaleFunction(defaultLocaleFunction);
  }
}
