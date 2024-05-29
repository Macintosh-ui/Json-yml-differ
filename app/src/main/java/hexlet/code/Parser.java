package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Parser {

    public static Map<String, Object> jsonParse(String filePath) throws IOException {
        Path path = Differ.getFilePath(filePath);
        File file = path.toFile();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, new TypeReference<>() { });
    }

    public static Map<String, Object> ymlParse(String filePath) throws IOException {
        Path path = Differ.getFilePath(filePath);
        File file = path.toFile();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(file, new TypeReference<>() { });
    }
}
