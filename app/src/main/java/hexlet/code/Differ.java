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
        List<Map<String, Object>> diff = getDiff(data1, data2);
        String output = Formatter.format(diff, format).trim();//Formatter.format(diff) а форматтер уже начинает работать и вытягивать значения
        //из каждой мапы листа.
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
    public static TreeMap<String, String> getDiff1(Map<String, Object> data1, Map<String, Object> data2) {
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
    public static List<Map<String, Object>> getDiff(Map<String, Object> data1, Map<String, Object> data2) {

       Set<String> keys = new TreeSet<>(data1.keySet());
       List<Map<String, Object>> diff = new ArrayList<>();
        enum status {
            CHANGE, ADD, REMOVE, noDIFF
        }
        keys.addAll(data2.keySet());
        keys.forEach(key -> {
            LinkedHashMap<String, Object> differ = new LinkedHashMap<>();
            if(data1.containsKey(key) && !data2.containsKey(key)) {
                differ.put("key", key);
                differ.put("type", status.REMOVE);
                differ.put("value", data1.get(key));
            } else if(data2.containsKey(key) && !data1.containsKey(key)) {
                differ.put("key", key);
                differ.put("type", status.ADD);
                differ.put("value", data2.get(key));
            } else if(Objects.equals(data1.get(key), data2.get(key))) {
                differ.put("key", key);
                differ.put("type", status.noDIFF);
                differ.put("value", data2.get(key));
            } else {
                differ.put("key", key);
                differ.put("type", status.CHANGE);
                differ.put("value1", data1.get(key));
                differ.put("value2", data2.get(key));
            }
            diff.add(differ);
        });
        /*data1.forEach((k, v) -> {
            if (data2.containsKey(k) && !Objects.equals(v, data2.get(k))) {
                Map<String, Object> difference = new HashMap<>();
                difference.put("key", k);
                difference.put("type", status.CHANGE);
                difference.put("value1", data1.get(k));
                difference.put("value2", data2.get(k));
                diff.add(difference);
            } else if (!data2.containsKey(k)) {
                Map<String, Object> differenceRM = new HashMap<>();
                differenceRM.put("key", k);
                differenceRM.put("type", status.REMOVE);
                differenceRM.put("value", data1.get(k));
                diff.add(differenceRM);
            } else {
                Map<String, Object> differenceNoDiff = new HashMap<>();
                differenceNoDiff.put("key", k);
                differenceNoDiff.put("type", status.noDIFF);
                differenceNoDiff.put("value", data2.get(k));
                diff.add(differenceNoDiff);
            }
        });
        data2.forEach((k, v) -> {
            if (!data1.containsKey(k)) {
                Map<String, Object> differenceADD = new HashMap<>();
                differenceADD.put("key", k);
                differenceADD.put("type", status.ADD);
                differenceADD.put("value", data2.get(k));
                diff.add(differenceADD);
            }
        });*/
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
