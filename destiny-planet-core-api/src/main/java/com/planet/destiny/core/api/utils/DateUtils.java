package com.planet.destiny.core.api.utils;


import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class DateUtils {

    public Long getCurrentDateTime() {
        DateTime dateTime = new DateTime();
        return 1L;
    }
}
