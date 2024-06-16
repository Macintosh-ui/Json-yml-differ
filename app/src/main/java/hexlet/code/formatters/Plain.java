package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {

    public static String plainFormat(List<Map<String, Object>> diff) {
        StringBuilder output = new StringBuilder();
        diff.forEach(m -> {
            switch (m.get("type").toString()) {
                case "CHANGE" -> changeValue(m.get("key").toString(), m.get("value1"), m.get("value2"), output);
                case "ADD" -> addValue(m.get("key").toString(), m.get("value"), output);
                case "REMOVE" -> output.append("Property '").append(m.get("key")).append("' was removed").append("\n");
                default -> output.append("");
            }
        });
        return output.toString();
    }
    private static void changeValue(String k, Object v1, Object v2, StringBuilder output) {
        output.append("Property '").append(k).append("' was updated. From ");
        bracketCheck(v1, output);
        output.append(" to ");
        bracketCheck(v2, output);
        output.append("\n");
    }
    private static void addValue(String k, Object v2, StringBuilder output) {
        output.append("Property '").append(k).append("' was added with value: ");
        bracketCheck(v2, output);
        output.append("\n");
    }
    private static void bracketCheck(Object value, StringBuilder output) {
        if (value instanceof String) {
            output.append("'").append(value).append("'");
        } else if (String.valueOf(value).startsWith("[") || String.valueOf(value).startsWith("{")) {
            output.append("[complex value]");
        } else {
            output.append(value);
        }
    }
}
