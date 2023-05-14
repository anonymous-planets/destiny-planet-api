package com.planet.destiny.core.api.utils;

import java.time.LocalDateTime;
import java.time.Period;

public class DateUtils {

    public static void main(String args[]) {
        System.out.println("===== DateUtils =====");
        System.out.println(getBeforeHourLocalDateTime(1L));
        System.out.println("=====================");
    }

    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDateTime getBeforeHourLocalDateTime(Long hour) {
        return LocalDateTime.now().minusHours(hour);
    }

    public static LocalDateTime getBeforeMinutesLocalDateTime(Long minutes) {
        return getCurrentLocalDateTime().minusMinutes(minutes);
    }
}
