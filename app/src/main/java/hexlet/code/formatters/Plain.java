package hexlet.code.formatters;

import java.util.Map;

public class Plain {
    public static String plainFormat(Map<String, Object> data1, Map<String, Object> data2, Map<String, String> diff) {
        StringBuilder output = new StringBuilder();
        diff.forEach((k, v) -> {
            var value1 = data1.get(k);
            var value2 = data2.get(k);
            switch (v) {
                case "change" -> changeValue(k, value1, value2, output);
                case "add" -> addValue(k, value1, value2, output);
                case "remove" -> output.append("Property '").append(k).append("' was removed").append("\n");
                default -> output.append("");
                //case "no difference" -> output.append("    ").append(k).append(": ").append(value2).append("\n");
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
    private static void addValue(String k, Object v1, Object v2, StringBuilder output) {
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
