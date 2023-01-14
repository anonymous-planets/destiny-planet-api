package com.planet.destiny.core.api.utils;

import com.planet.destiny.core.api.config.component.ApplicationContextServe;
import org.springframework.context.ApplicationContext;

public class PropertyUtils {

    public static String getProperty(String propertyName) {
        return getProperty(propertyName, null);
    }

    public static String getProperty(String propertyName, String defaultValue) {
        String value = defaultValue;

        ApplicationContext applicationContext = ApplicationContextServe.getApplicationContext();

        if(applicationContext.getEnvironment().getProperty(propertyName) == null) {
        } else {
            value = String.valueOf(applicationContext.getEnvironment().getProperty(propertyName));
        }

        return value;
    }
}
