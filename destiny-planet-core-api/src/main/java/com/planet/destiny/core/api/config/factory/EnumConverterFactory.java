package com.planet.destiny.core.api.config.factory;

import com.planet.destiny.core.api.constant.LegacyType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Arrays;


/**
 * https://kapentaz.github.io/java/spring/Enum-and-@RequestParam-in-Spring/#
 *
 * PathVariable로 넘어온 Enum타입 매칭
 */
public class EnumConverterFactory implements ConverterFactory<String, Enum<? extends LegacyType>> {
    @Override
    public <T extends Enum<? extends LegacyType>> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnumsConverter<>(targetType);
    }

    private static final class StringToEnumsConverter<T extends Enum<? extends LegacyType>> implements Converter<String, T> {

        private final Class<T> enumType;
        private final boolean constantEnum;

        public StringToEnumsConverter(Class<T> enumType) {
            this.enumType = enumType;
            this.constantEnum = Arrays.stream(enumType.getInterfaces()).anyMatch(i -> i == LegacyType.class);
        }

        @Override
        public T convert(String source) {
            if(source.isEmpty()) {
                return null;
            }

            T[] constants = enumType.getEnumConstants();

            for(T c : constants) {
                if(constantEnum) {
                    if (((LegacyType) c).getCode().equals(source.trim())) {

                        return c;
                    }
                }
            }
            return null;
        }
    }
}
