package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Diff {
    public static List<Map<String, Object>> getDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>(data1.keySet());
        List<Map<String, Object>> diff = new ArrayList<>();
        keys.addAll(data2.keySet());
        keys.forEach(key -> {
            LinkedHashMap<String, Object> differ = new LinkedHashMap<>();
            differ.put("key", key);
            if (!data2.containsKey(key)) {
                differ.put("type", STATUS.REMOVE);
                differ.put("value", data1.get(key));
            } else if (data2.containsKey(key) && !data1.containsKey(key)) {
                differ.put("type", STATUS.ADD);
                differ.put("value", data2.get(key));
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                differ.put("type", STATUS.noDIFF);
                differ.put("value", data2.get(key));
            } else {
                differ.put("type", STATUS.CHANGE);
                differ.put("value1", data1.get(key));
                differ.put("value2", data2.get(key));
            }
            diff.add(differ);
        });
        return diff;
    }
}
