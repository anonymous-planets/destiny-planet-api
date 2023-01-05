package com.planet.destiny.core.api.validate.annotation;

import com.planet.destiny.core.api.validate.validator.PhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * phone 유효성 검사 지정 Annotation
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
public @interface Phone {

    // 유효하지 않을 경우 반환할 메시지
    String message() default "Invalid Phone";

    // 유효성 검증이 진행될 그룹
    Class<?>[] groups() default {};

    // 유효성 검증 시에 전달할 메타 정보
    Class<? extends Payload>[] payload() default {};
}
