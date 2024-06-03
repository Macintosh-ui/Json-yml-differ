package hexlet.code;

import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import java.util.Objects;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        TreeMap<String, Object> data1 = new TreeMap<>();
        TreeMap<String, Object> data2 = new TreeMap<>();
        if (filepath1.endsWith(".json") && filepath2.endsWith(".json")) {
            data1 = Parser.jsonParse(filepath1);
            data2 = Parser.jsonParse(filepath2);
        } else if (filepath1.endsWith(".yml") && filepath2.endsWith(".yml")) {
            data1 = Parser.ymlParse(filepath1);
            data2 = Parser.ymlParse(filepath2);
        }
        TreeMap<String, String> diff = getDiff(data1, data2);
        String output = Formatter.format(data1, data2, diff, format);
        System.out.println(output);
        return output;
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        String format = "stylish";
        return generate(filepath1, filepath2, format);
    }

    public static TreeMap<String, String> getDiff(Map<String, Object> data1, Map<String, Object> data2) {
        TreeMap<String, String> diff = new TreeMap<>();
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
        Path output = null;
        try {
            output = Path.of(filepath).toAbsolutePath().normalize();
        } catch (Exception e) {
            System.out.println("File not found: " + filepath);
        }
        return output;
    }
}
