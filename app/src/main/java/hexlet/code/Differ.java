package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Differ {
    //public static String generate(String filepath1, String filepath2) throws Exception {

//}

   // public static Map getData(String content) throws Exception {
    //    return parse(content);
   // }
    public static Map<String, Object> getDiff(Map<String, Object> fileData1, Map<String, Object> fileData2) throws IOException {
        getData(fileData1.toString());
    }
    public static Path getFilePath(String filepath) {
        return Path.of(filepath);
    }
    public static List<String> readFile(String filePath) throws IOException {
        Path path = getFilePath(filePath).toAbsolutePath().normalize();
        return Files.readAllLines(path);
    }
    public static Map<String, Object> getData(String filepath) throws IOException {
        String[] fileData = readFile(filepath).toArray(new String[0]);
        return parse(fileData);
    }
    public static Map<String, Object> parse(String[] fileData) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(fileData);
    }
}
