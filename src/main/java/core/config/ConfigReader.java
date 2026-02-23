package core.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream input =
                    ConfigReader.class.getClassLoader()
                            .getResourceAsStream("config/config.properties");

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public static String get(String key) {
        return System.getProperty(key,
                properties.getProperty(key));
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}