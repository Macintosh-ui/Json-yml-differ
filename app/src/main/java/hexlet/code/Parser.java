package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class Parser {

    public static Map<String, Object> parse(File file, String extension) throws IOException {
        return switch (extension) {
            case ".json" -> jsonParse(file);
            case ".yml", ".yaml" -> ymlParse(file);
            default -> throw new IllegalArgumentException("Unsupported extension: " + extension);
        };
    }
    public static Map<String, Object> jsonParse(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, new TypeReference<>() { });
    }

    public static Map<String, Object> ymlParse(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(file, new TypeReference<>() { });
    }
}
