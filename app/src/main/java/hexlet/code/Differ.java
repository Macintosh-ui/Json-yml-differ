package hexlet.code;

import java.nio.file.Path;
import java.util.*;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        TreeMap<String, Object> data1;
        TreeMap<String, Object> data2;
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
        List<Map<String, Object>> diff = jsonDiff(data1, data2);
        String output1 = Formatter.newFormat(diff, format);
        String output = Formatter.format(data1, data2, diff, format).trim();//Formatter.format(diff) а форматтер уже начинает работать и вытягивать значения
        //из каждой мапы листа.
        System.out.println(output);
        return output;
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        String format = "stylish";
        return generate(filepath1, filepath2, format);
    }

    private static String getFileExtension(String filepath) {
        String extension = "";
        if (filepath.endsWith(".json") || filepath.endsWith(".JSON")) {
            extension = ".json";
        } else {
            extension = ".yml";
        }
        return extension;
    }
    public static TreeMap<String, String> getDiff(Map<String, Object> data1, Map<String, Object> data2) {
        List<Map<String, Object>> differ = new ArrayList<>();
        Map<String, Object> diff2 = new HashMap<>();
        TreeMap<String, String> diff = new TreeMap<>();
            enum status {
                CHANGE, ADD, REMOVE, noDIFF
            }
            /*String change = "change";
            String add = "add";
            String remove = "remove";
            String noDiff = "no difference";
            */

        data1.forEach((k, v) -> {
            if (data2.containsKey(k) && !Objects.equals(v, data2.get(k))) {
                diff2.put(k, String.valueOf(status.CHANGE));
            } else if (!data2.containsKey(k)) {
                diff2.put(k, String.valueOf(status.REMOVE));
            } else {
                diff2.put(k, String.valueOf(status.noDIFF));
            }
        });
        data2.forEach((k, v) -> {
            if (!data1.containsKey(k)) {
                diff2.put(k, String.valueOf(status.ADD));
            }
        });
        differ.add(diff2);
        return diff;
    }
    public static List<Map<String, Object>> jsonDiff(Map<String, Object> data1, Map<String, Object> data2) {
        TreeMap<String, Object> differ = new TreeMap<>();
        List<Map<String, Object>> diff = new ArrayList<>();
        enum status {
            CHANGE, ADD, REMOVE, noDIFF
        }
        data1.forEach((k, v) -> {
            if (data2.containsKey(k) && !Objects.equals(v, data2.get(k))) {
                differ.put("key", k);
                differ.put("type", status.CHANGE);
                differ.put("value1", data1.get(k));
                differ.put("value2", data2.get(k));
                diff.add(differ);
                differ.clear();
            } else if (!data2.containsKey(k)) {
                differ.put("key", k);
                differ.put("type", status.REMOVE);
                differ.put("value", data1.get(k));
                diff.add(differ);
                differ.clear();
            } else {
                differ.put("key", k);
                differ.put("type", status.noDIFF);
                diff.add(differ);
                differ.clear();
            }
        });
        data2.forEach((k, v) -> {
            if (!data1.containsKey(k)) {
                differ.put("key", k);
                differ.put("type", status.ADD);
                differ.put("value", data2.get(k));
                diff.add(differ);
                differ.clear();
            }
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
