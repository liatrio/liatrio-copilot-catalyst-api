package com.liatrio.dojo.devopsknowledgeshareapi.functional;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertiesExtractor {
    private static Properties properties;
    private static String propertiesFile = System.getenv("SPRING_PROFILES_ACTIVE") != null ? System.getenv("SPRING_PROFILES_ACTIVE") : "application";
    static {
        properties = new Properties();
        URL url = PropertiesExtractor.class.getClassLoader().getResource(propertiesFile+".properties");
        try{
            assert url != null;
            properties.load(new FileInputStream(url.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
