package com.planet.destiny.core.api.validate.validator;

import com.planet.destiny.core.api.utils.StringUtils;
import com.planet.destiny.core.api.validate.annotation.Phone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO : 수정 필요
public class PhoneValidator implements ConstraintValidator<Phone, String> {


    // Validtor를 초기화 하기 위한 메소드
//    @Override
//    public void initialize(Phone constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
//    }

    // 유효성을 검증하는 메소드
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isEmpty(value)) {
            return false;
        }
        Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\{4}");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
