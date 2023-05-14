package com.planet.destiny.core.api.constant.member;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.planet.destiny.core.api.constant.LegacyType;
import com.planet.destiny.core.api.exception.NotFoundEnumValueException;
import com.planet.destiny.core.api.utils.StringUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MemberType implements LegacyType {

  ADMIN("A", "관리자")
  , MEMBER("M", "일반회원")
  ;

  private final String code;
  private final String desc;

  MemberType(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  @Override
  public String getName() {
    return this.name();
  }

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public String getDesc() {
    return this.desc;
  }


  @JsonCreator
  public static MemberType create(String value) throws NotFoundEnumValueException {
    if(StringUtils.isEmpty(value)) {
      throw new NotFoundEnumValueException(MemberType.class.getSimpleName());
    }

    for(MemberType memberType : MemberType.values()) {
      if(memberType.getCode().equals(value) || memberType.getName().equals(value)) {
        return memberType;
      }
    }
    throw new NotFoundEnumValueException(value, MemberType.class.getSimpleName());

  }

  @Converter
  public static class MemberTypeAttributeConverter implements AttributeConverter<MemberType, String> {
    @Override
    public String convertToDatabaseColumn(MemberType memberType) {
      return memberType.getCode();
    }

    @Override
    public MemberType convertToEntityAttribute(String dbData) {
      return MemberType.create(dbData);
    }
  }
}
