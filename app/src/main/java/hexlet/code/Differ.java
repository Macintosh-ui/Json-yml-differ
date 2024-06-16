package hexlet.code;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Objects;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> data1;
        Map<String, Object> data2;
        data1 = switch (getFileExtension(filepath1)) {
            case ".json" -> Parser.jsonParse(filepath1);
            case ".yml" -> Parser.ymlParse(filepath1);
            default -> throw new IllegalArgumentException("Unsupported file format: " + getFileExtension(filepath1));
        };
        data2 = switch (getFileExtension(filepath2)) {
            case ".json" -> Parser.jsonParse(filepath2);
            case ".yml" -> Parser.ymlParse(filepath2);
            default -> throw new IllegalArgumentException("Unsupported file format: " + getFileExtension(filepath2));
        };
        List<Map<String, Object>> diff = getDiff(data1, data2);
        String output = Formatter.format(diff, format).trim();
        System.out.println(output);
        return output;
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        String format = "stylish";
        return generate(filepath1, filepath2, format);
    }

    private static String getFileExtension(String filepath) {
        String extension;
        if (filepath.endsWith(".json") || filepath.endsWith(".JSON")) {
            extension = ".json";
        } else {
            extension = ".yml";
        }
        return extension;
    }

    public static List<Map<String, Object>> getDiff(Map<String, Object> data1, Map<String, Object> data2) {

        Set<String> keys = new TreeSet<>(data1.keySet());
        List<Map<String, Object>> diff = new ArrayList<>();
        enum STATUS {
            CHANGE, ADD, REMOVE, noDIFF
        }
        keys.addAll(data2.keySet());
        keys.forEach(key -> {
            LinkedHashMap<String, Object> differ = new LinkedHashMap<>();
            if (data1.containsKey(key) && !data2.containsKey(key)) {
                differ.put("key", key);
                differ.put("type", STATUS.REMOVE);
                differ.put("value", data1.get(key));
            } else if (data2.containsKey(key) && !data1.containsKey(key)) {
                differ.put("key", key);
                differ.put("type", STATUS.ADD);
                differ.put("value", data2.get(key));
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                differ.put("key", key);
                differ.put("type", STATUS.noDIFF);
                differ.put("value", data2.get(key));
            } else {
                differ.put("key", key);
                differ.put("type", STATUS.CHANGE);
                differ.put("value1", data1.get(key));
                differ.put("value2", data2.get(key));
            }
            diff.add(differ);
        });
        return diff;
    }

    public static Path getFilePath(String filepath) {
        Path output = null;
        try {
            output = Path.of(filepath).toAbsolutePath().normalize();
        } catch (Exception e) {
            System.out.println("File not found: " + filepath);
        }
        return output;
    }
}
