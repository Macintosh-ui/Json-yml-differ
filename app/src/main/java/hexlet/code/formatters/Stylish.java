package hexlet.code.formatters;

import java.util.Map;

public class Stylish {
    public static String stylishFormat(Map<String, Object> data1, Map<String, Object> data2, Map<String, String> diff) {
        var output = new StringBuilder("{\n");
        diff.forEach((k, v) -> {
            String value1 = String.valueOf(data1.get(k));
            String value2 = String.valueOf(data2.get(k));
            switch (v) {
                case "change" ->
                        output.append("  - ").append(k).append(": ").append(value1).append("\n").append("  + ")
                                .append(k).append(": ").append(value2).append("\n");
                case "add" -> output.append("  + ").append(k).append(": ").append(value2).append("\n");
                case "remove" -> output.append("  - ").append(k).append(": ").append(value1).append("\n");
                case "no difference" -> output.append("    ").append(k).append(": ").append(value2).append("\n");
                default -> throw new IllegalStateException("Unexpected value: " + v);
            }
        });
        output.append("}\n");
        return output.toString();
    }
}
