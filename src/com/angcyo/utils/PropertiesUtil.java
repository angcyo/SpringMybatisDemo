package com.angcyo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by angcyo on 2016-03-01 10:57.
 */
public class PropertiesUtil {
    private static Properties init() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/log4j.properties");
        properties.load(fileInputStream);
        return properties;
    }

    public static void get(String key, OnGet onGet) {
        String value;
        Properties properties;
        try {
            properties = init();
            value = properties.getProperty(key, "");
            if (onGet != null) {
                onGet.onGet(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface OnGet{
        void onGet(String value);
    }
}
