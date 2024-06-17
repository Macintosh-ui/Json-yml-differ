package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(List<Map<String, Object>> diff, String format) throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.stylishFormat(diff);
            case "plain" -> Plain.plainFormat(diff);
            case "json" -> Json.jsonFormat(diff);
            default -> throw new IllegalStateException("Unexpected format: " + format);
        };
    }
}
