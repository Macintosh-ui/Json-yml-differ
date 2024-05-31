package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String format(Map<String, Object> data1, Map<String, Object> data2,
                                Map<String, String> diff, String format) throws Exception {
        format.toLowerCase();
        String output;
        output = switch (format) {
            case "stylish" -> Stylish.stylishFormat(data1, data2, diff);
            case "plain" -> Plain.plainFormat(data1, data2, diff);
            case "json" -> Json.jsonFormat(diff);
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
        return output;
    }
}
