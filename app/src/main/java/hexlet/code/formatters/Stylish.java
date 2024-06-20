package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String stylishFormat(List<Map<String, Object>> diff) {
        var output = new StringBuilder("{\n");
        diff.forEach(map -> {
            switch (String.valueOf(map.get("type"))) {
                case "CHANGE" ->
                        output.append("  - ").append(map.get("key")).append(": ").append(map.get("value1")).append("\n")
                             .append("  + ").append(map.get("key")).append(": ").append(map.get("value2")).append("\n");
                case "ADD" ->
                        output.append("  + ").append(map.get("key")).append(": ").append(map.get("value")).append("\n");
                case "REMOVE" ->
                        output.append("  - ").append(map.get("key")).append(": ").append(map.get("value")).append("\n");
                case "NO_DIFF" ->
                        output.append("    ").append(map.get("key")).append(": ").append(map.get("value")).append("\n");
                default -> throw new IllegalStateException("Unexpected status: " + map.get("status"));
            }
        });
        output.append("}");
        return output.toString();
    }
}
