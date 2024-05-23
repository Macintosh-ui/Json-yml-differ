package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> data1 = parse(filepath1);
        Map<String, Object> data2 = parse(filepath2);
        List<String> keys = new ArrayList<>(data1.keySet());
        var output = new StringBuilder();
        keys.addAll(data2.keySet());
        for (String key : keys) {
            String value1 = data1.get(key).toString();
            String value2 = data2.get(key).toString();
            if (value1.contains(key) && value2.contains(key)) {
                if(value1.equals(value2)) {
                    output.append(" ").append(key).append(":").append(value1).append("\n");
                } else {
                    output.append("-").append(key).append(":").append(value2).append("\n");
                    output.append("+").append(key).append(":").append(value1).append("\n");
                }
            }
        }
        System.out.println(output);
        return output.toString();
    }
    public static Path getFilePath(String filepath) {
        return Path.of(filepath).toAbsolutePath().normalize();
    }
    public static List<String> readFile(String filePath) throws IOException {
        Path path = getFilePath(filePath).toAbsolutePath().normalize();
        return Files.readAllLines(path);
    }
    public static Map<String, Object> getData(String fileLines) throws IOException {
        return parse(fileLines);
    }
    public static Map<String, Object> parse(String fileData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(fileData, new TypeReference<>() { });
    }
    /* public static void example() throws IOException {
        String filepath = "/build/install/bin/example.JSON";
        var fileLines = readFile(filepath); //List
        Map<String, Object> data = getData(String.valueOf(fileLines));
        String filePath2 = "/build/install/bin/example2.json";
        var fileLines2 = readFile(filePath2);
        Map<String, Object> data2 = getData(String.valueOf(fileLines2));
        System.out.println();
    }*/
}
