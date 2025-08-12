package utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * The ConfigLoader class is a utility for loading and accessing configuration
 * properties from a `config.properties` file. It ensures that the properties
 * are loaded once and made available throughout the application.
 */
public class ConfigLoader {
    // Static Properties object to hold the loaded configuration data
    private static Properties props = new Properties();

    // Static block to initialize the Properties object by loading the config file
    static {
        try (InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("config.properties")) {
            // Load the properties from the config file
            props.load(is);
        } catch (Exception e) {
            // Throw a RuntimeException if the file fails to load
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    /**
     * Retrieves the value associated with the specified key from the loaded properties.
     *
     * @param key The key whose associated value is to be returned.
     * @return The value corresponding to the given key, or null if the key is not found.
     */
    public static String get(String key) {
        return props.getProperty(key);
    }
}