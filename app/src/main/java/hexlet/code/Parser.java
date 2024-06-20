package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;


public class Parser {

    public static Map<String, Object> parse(String content, String extension) throws IOException {
        return switch (extension) {
            case ".json" -> jsonParse(content);
            case ".yml", ".yaml" -> ymlParse(content);
            default -> throw new IllegalArgumentException("Unsupported extension: " + extension);
        };
    }
    public static Map<String, Object> jsonParse(String content) throws IOException {
        return new ObjectMapper().readValue(content, new TypeReference<>() { });
    }
//
    public static Map<String, Object> ymlParse(String content) throws IOException {
        return new ObjectMapper(new YAMLFactory()).readValue(content, new TypeReference<>() { });
    }
}
