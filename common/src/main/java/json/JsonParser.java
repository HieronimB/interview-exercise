package json;

import com.fasterxml.jackson.core.JsonParser.Feature; // Nieużywane
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonParser {
    public JsonNode parse(String json) {
        //object maper tworzony przy kazdorazowym parsowaniu jsona, powinien byc statyczny finalny
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(json);
        } catch (IOException e) {
            throw new JsonParseException("Cannot parse given string as JSON!", e); // Nieprawda, to jest błąd pobierania danych
            //a nawet gdyby to za malo informacji w exceptionie, brak info o parsowanym jsonie
        }
    }
}
