package core.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationManager {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(
                    new FileInputStream("src/test/resources/config/config.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Config not loaded");
        }
    }

    public static String getBrowser() {
        return System.getProperty("browser",
                properties.getProperty("browser"));
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }
}