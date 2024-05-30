package hexlet.code;

import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> data1 = new HashMap<>();
        Map<String, Object> data2 = new HashMap<>();
        if (filepath1.endsWith(".JSON") && filepath2.endsWith(".JSON")) {
            data1 = Parser.jsonParse(filepath1);
            data2 = Parser.jsonParse(filepath2);
        } else if (filepath1.endsWith(".yml") && filepath2.endsWith(".yml")) {
            data1 = Parser.ymlParse(filepath1);
            data2 = Parser.ymlParse(filepath2);
        }
        Map<String, String> diff = getDiff(data1, data2);
        String output = Stylish.format(data1, data2, diff);
        System.out.println(output);
        return output;
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        String format = "stylish";
        return generate(filepath1, filepath2, format);
    }

    public static Map<String, String> getDiff (Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, String> diff = new HashMap<>();
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
