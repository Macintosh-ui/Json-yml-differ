package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.dataformat.yaml.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> data1 = ymlParse(filepath1);
        Map<String, Object> data2 = ymlParse(filepath2);
        Map<String, String> diff = getDiff(data1, data2);
        var output = new StringBuilder();
        diff.forEach((k, v) -> {
            String value1 = String.valueOf(data1.get(k));
            String value2 = String.valueOf(data2.get(k));
            switch (v) {
                case "change" ->
                        output.append("  - ").append(k).append(": ").append(value1).append("\n").append("  + ").append(k).append(": ").append(value2).append("\n");
                case "add" -> output.append("  + ").append(k).append(": ").append(value2).append("\n");
                case "remove" -> output.append("  - ").append(k).append(": ").append(value1).append("\n");
                case "no difference" -> output.append("    ").append(k).append(": ").append(value2).append("\n");
            }
        });
        System.out.println(output);
        return output.toString();
    }

    public static Map<String, String> getDiff (Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, String> diff = new HashMap<>();
        List<String> keys = new ArrayList<>(data1.keySet());
        keys.addAll(data2.keySet());
        String change = "change";
        String add = "add";
        String remove = "remove";
        String noDiff = "no difference";
        data1.forEach((k, v) -> {
            if (data2.containsKey(k) && !Objects.equals(v, data2.get(k))) {
                diff.put(k, change);
            } else if (!data2.containsKey(k)) {
                diff.put(k, remove);
            } else {
                diff.put(k, noDiff);
            }
        });
        data2.forEach((k, v) -> {
            if (!data1.containsKey(k)) {
                diff.put(k, add);
            }
        });
        return diff;
    }

    public static Path getFilePath(String filepath) {
        return Path.of(filepath).toAbsolutePath().normalize();
    }

    public static List<String> readFile(String filePath) throws IOException {
        Path path = getFilePath(filePath).toAbsolutePath().normalize();
        return Files.readAllLines(path);
    }

    public static Map<String, Object> jsonParse(String filePath) throws IOException {
        Path path = getFilePath(filePath);
        File file = path.toFile();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, new TypeReference<>() { });
    }
    public static Map<String, Object> ymlParse(String filePath) throws IOException {
        Path path = getFilePath(filePath);
        File file = path.toFile();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(file, new TypeReference<>() { });
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
