package com.planet.destiny.core.api.utils;

import com.google.gson.Gson;
import com.planet.destiny.core.api.constant.LegacyType;
import com.planet.destiny.core.api.module.member.item.MemberBasDto;

import java.lang.reflect.Field;
import java.util.*;

public class StringUtils {

    public static void main(String args[]) throws Exception {
        System.out.println(templateParams("{\"param1\" : \"name\",\"param2\" : \"memberId\"} ", MemberBasDto.builder().memberId("test").name("이름").build()));
    }

    /**
     * Enum타입 찾는중 에러 발생시 해당 Enum값 목록 추출
     * @param e
     * @param <E>
     * @return
     */
    public static <E extends LegacyType> List<String> getEnumValues(Class<E> e) {
        List<String> list = new ArrayList<>();
        for(LegacyType value : e.getEnumConstants()) {
            list.add(value.getCode() + "(" + value.getName() + ")");
        }
        return list;
    }

    public static <E extends Enum> List<String> getNormalEnumValues(Class<E> e) {
        List<String> list = new ArrayList<>();
        for(Enum enu : e.getEnumConstants()) {
            list.add(enu.name());
        }
        return list;
    }

    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str) || str.length() == 0);
    }

    public static String generateUUID() {
        return String.valueOf(UUID.randomUUID()).replaceAll("-", "");
    }

    public static Map<String, Object> templateParams(String templateParamStr, Object dto) {
        Map<String, Object> templateParams = new HashMap<>();

        try{
            Map<String, String> params = new Gson().fromJson(templateParamStr, Map.class);
            Object obj = dto;
            for(String key : params.keySet()) {
                String value = params.get(key);
                for(Field field : dto.getClass().getDeclaredFields()) {
                    if(value.equals(field.getName())) {
                        field.setAccessible(true);
                        templateParams.put(value, field.get(dto));
                    }
                }
            }
            return templateParams;
        }catch(Exception e) {
            return null;
        }
    }


    /**
     * 랜덤 비밀번호 생성
     * @return
     */
    public static String randomPassword() {
        return "";
    }
}
