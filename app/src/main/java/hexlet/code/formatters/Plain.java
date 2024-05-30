package hexlet.code.formatters;

import java.util.Map;

public class Plain {
    public static String plainFormat(Map<String, Object> data1, Map<String, Object> data2, Map<String, String> diff) {
        StringBuilder output = new StringBuilder();
        diff.forEach((k, v) -> {
            String value1 = String.valueOf(data1.get(k));
            String value2 = String.valueOf(data2.get(k));
            switch (v) {
                case "change" ->
                        output.append("Property '").append(k).append("' was updated. From ").append(value1).append(" to ").append(value2).append(".\n");
                case "add" -> output.append("Property '").append(k).append("' was added with value '").append(value2).append("'\n");
                case "remove" -> output.append("Property '").append(k).append("' was removed").append("\n");
                //case "no difference" -> output.append("    ").append(k).append(": ").append(value2).append("\n");
            }
        });
        return output.toString();
    }
}
