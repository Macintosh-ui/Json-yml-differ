package hexlet.code;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> data1;
        Map<String, Object> data2;
        String extension1 = getFileExtension(filepath1);
        String extension2 = getFileExtension(filepath2);
        data1 = Parser.parse(getFilePath(filepath1), extension1);
        data2 = Parser.parse(getFilePath(filepath2), extension2);
        List<Map<String, Object>> diff = Diff.getDiff(data1, data2);
        System.out.println(Formatter.format(diff, format).trim());
        return Formatter.format(diff, format).trim();
    }
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
    private static String getFileExtension(String filepath) {
        if (filepath.endsWith(".json") || filepath.endsWith(".JSON")) {
            return ".json";
        } else {
            return ".yml";
        }
    }

    private static Path getFilePath(String filepath) {
        return Path.of(filepath).toAbsolutePath().normalize();
    }
}
