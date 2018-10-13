package csv;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CsvConverterTest {
    private CsvConverter csvConverter = new CsvConverter();

    @Test
    void should_convertJsonNodeToCsvWithoutCustomFormat() throws IOException {
        List<JsonNode> jsonNodes = createTestJsonList("{ \"foo\": \"bar\", \"fuz\":\"baz\"}");

        String csv = csvConverter.convert(jsonNodes, null);

        assertThat(csv).contains("bar,baz");
    }

    @Test
    void should_convertJsonNodeToCsvWithCustomFormat() throws IOException {
        List<JsonNode> jsonNodes = createTestJsonList("{ \"foo\": \"bar\", \"fuz\":\"baz\", \"fus\":\"bas\"}");
        List<String> format = Arrays.asList("fus","foo");

        String csv = csvConverter.convert(jsonNodes, format);

        assertThat(csv).contains("bas,bar");
    }

    @Test
    void should_convertJsonNodeToCsvWithNestedObjects() throws IOException {
        List<JsonNode> jsonNodes = createTestJsonList("{ \"foo\": \"bar\", \"fuz\": {\"baz\": \"bazz\", \"fus\":\"bas\"}}");
        List<String> format = Arrays.asList("fuz","foo");

        String csv = csvConverter.convert(jsonNodes, format);

        assertThat(csv).contains("bazz,bas,bar");
    }

    private List<JsonNode> createTestJsonList(String s) throws IOException {
        JsonNode jsonNode = createJsonNode(s);
        return Arrays.asList(jsonNode, jsonNode, jsonNode);
    }

    private JsonNode createJsonNode(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(json);
    }
}