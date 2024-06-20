package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {

    public static String plainFormat(List<Map<String, Object>> diff) {
        StringBuilder output = new StringBuilder();
        String changed = "Property '%s' was updated. From %s to %s\n";
        String added = "Property '%s' was added with value: %s\n";
        String removed = "Property '%s' was removed\n";
        diff.forEach(m -> {
            switch (m.get("type").toString()) {
                case "CHANGE" -> output.append(String.format(changed, m.get("key"),
                                    bracketCheck(m.get("value1")), bracketCheck(m.get("value2"))));
                case "ADD" -> output.append(String.format(added, m.get("key"), bracketCheck(m.get("value"))));
                case "REMOVE" -> output.append(String.format(removed, m.get("key")));
                case "NO_DIFF" -> output.append("");
                default -> throw new IllegalStateException("Unexpected value: " + m.get("type"));
            }
        });
        return output.toString();
    }

    private static String bracketCheck(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Collection<?> || value instanceof Map<?, ?>) {
            return ("[complex value]");
        } else {
            return String.valueOf(value);
        }
    }
}
