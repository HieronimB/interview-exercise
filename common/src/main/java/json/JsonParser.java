package json;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonParser {
    public JsonNode parse(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        try {
            return objectMapper.readTree(json);
        } catch (IOException e) {
            throw new JsonParseException("Cannot parse given string as JSON!", e);
        }
    }
}
