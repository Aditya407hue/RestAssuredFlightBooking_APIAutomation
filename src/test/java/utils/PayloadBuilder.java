package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.Map;

/**
 * The PayloadBuilder class is a utility for loading JSON files and converting
 * their contents into a Map<String, Object> representation. This is useful for
 * scenarios where JSON payloads are required, such as API testing or data manipulation.
 */
public class PayloadBuilder {
    // Static ObjectMapper instance for JSON parsing
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * Loads a JSON file from the classpath and converts its content into a Map<String, Object>.
     *
     * @param resourcePath The path to the JSON resource file within the classpath.
     * @return A Map<String, Object> representation of the JSON content.
     * @throws RuntimeException if the file cannot be read or parsed.
     */
    public static Map<String, Object> loadJsonAsMap(String resourcePath) {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath)) {
            // Parse the JSON content into a Map and return it
            return mapper.readValue(is, Map.class);
        } catch (Exception e) {
            // Throw a RuntimeException with a descriptive error message if an exception occurs
            throw new RuntimeException("Failed to read " + resourcePath, e);
        }
    }
}