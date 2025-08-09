package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.Map;

public class PayloadBuilder {
    private static ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> loadJsonAsMap(String resourcePath) {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath)) {
            return mapper.readValue(is, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read " + resourcePath, e);
        }
    }
}
