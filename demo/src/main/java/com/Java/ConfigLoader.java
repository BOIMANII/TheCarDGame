package com.Java;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    public static String getToken() throws Exception {
        Properties properties = new Properties();
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new Exception("config.properties file not found!");
            }
            properties.load(input);
        }
        return properties.getProperty("botToken");
    }
}
