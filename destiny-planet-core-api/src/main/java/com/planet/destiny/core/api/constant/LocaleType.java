package com.planet.destiny.core.api.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.planet.destiny.core.api.exception.NotFoundEnumValueException;
import com.planet.destiny.core.api.utils.StringUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Locale;

public enum LocaleType implements LegacyType {
  KOREA("ko_KR", "한국", Locale.KOREA)
  , JAPAN("ja_JP", "일본", Locale.JAPAN)
  , CHINA("zh_CN", "중국", Locale.CHINA)
  , ETC("en_US", "영어권", Locale.ENGLISH)
  ;

  private final String code;
  private final String desc;
  private final Locale localeCode;

  LocaleType(final String code, final String desc, final Locale localeCode) {
    this.code = code;
    this.desc = desc;
    this.localeCode = localeCode;
  }

  @Override
  public String getName() {
    return this.name();
  }


  @JsonCreator
  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public String getDesc() {
    return this.desc;
  }

  public Locale getLocaleCode() {
    return this.localeCode;
  }


  @JsonCreator
  public static LocaleType create(String value) throws NotFoundEnumValueException {
    if(StringUtils.isEmpty(value)) {
      return LocaleType.KOREA;
    }

    for(LocaleType localeType : LocaleType.values()) {
      if(localeType.getName().equalsIgnoreCase(value) || localeType.getCode().equalsIgnoreCase(value)) {
        return localeType;
      }
    }
    return LocaleType.KOREA;
  }

  @Converter
  public static class LocaleTypeAttributeConverter implements AttributeConverter<LocaleType, String> {
    @Override
    public String convertToDatabaseColumn(LocaleType attribute) {
      return attribute.getCode();
    }

    @Override
    public LocaleType convertToEntityAttribute(String dbData) {
      return LocaleType.create(dbData);
    }
  }
}
