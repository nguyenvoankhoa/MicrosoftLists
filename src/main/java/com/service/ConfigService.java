package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigService {
    public static String loadProperties(String path) {
        Properties properties = new Properties();
        try (InputStream input = ConfigService.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
            return properties.getProperty(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
