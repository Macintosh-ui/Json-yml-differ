package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;


public class Parser {

    public static Map<String, Object> parse(Path filePath, String extension) throws IOException {
        return switch (extension) {
            case ".json" -> jsonParse(filePath);
            case ".yml" -> ymlParse(filePath);
            default -> throw new IllegalArgumentException("Unsupported extension: " + extension);
        };
    }
    public static Map<String, Object> jsonParse(Path filePath) throws IOException {
        File file = filePath.toFile();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, new TypeReference<>() { });
    }

    public static Map<String, Object> ymlParse(Path filePath) throws IOException {
        File file = filePath.toFile();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(file, new TypeReference<>() { });
    }
}
