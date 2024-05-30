package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Json {
    public static String jsonFormat(Map<String, String> diff) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String output = mapper.writeValueAsString(diff);
        return output;
    }
}
