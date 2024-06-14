package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Stylish {
    public static String stylishFormat(Map<String, Object> data1, Map<String, Object> data2, Map<String, String> diff) {
        var output = new StringBuilder("{\n");
        diff.forEach((k, v) -> {
            String value1 = String.valueOf(data1.get(k));
            String value2 = String.valueOf(data2.get(k));
            switch (v) {
                case "CHANGE" ->
                        output.append("  - ").append(k).append(": ").append(value1).append("\n").append("  + ")
                                .append(k).append(": ").append(value2).append("\n");
                case "ADD" -> output.append("  + ").append(k).append(": ").append(value2).append("\n");
                case "REMOVE" -> output.append("  - ").append(k).append(": ").append(value1).append("\n");
                case "noDIFF" -> output.append("    ").append(k).append(": ").append(value2).append("\n");
                default -> throw new IllegalStateException("Unexpected value: " + v);
            }
        });
        output.append("}");
        return output.toString();
    }

    public static String newStylishFormat(Set<Map<String, Object>> diff) {
        var output = new StringBuilder("{\n");
        diff.forEach(m -> {
            m.forEach((k, v) -> {
               String status = String.valueOf(m.get("type"));
               switch (status) {
                   case "CHANGE" ->
                           output.append("  - ").append(m.get("key")).append(": ").append(m.get("value1")).append("\n")
                                 .append("  + ").append(m.get("key")).append(": ").append(m.get("value2")).append("\n");
                   case "ADD" ->
                           output.append("  + ").append(m.get("key")).append(": ").append(m.get("value")).append("\n");
                   case "REMOVE" ->
                           output.append("  - ").append(m.get("key")).append(": ").append(m.get("value")).append("\n");
                   case "noDIFF" ->
                           output.append("    ").append(m.get("key")).append(": ").append(m.get("value")).append("\n");
                   default -> throw new IllegalStateException("Unexpected status: " + m.get("status"));
               }
            });
        });
        output.append("}");
        return output.toString();
    }
}
