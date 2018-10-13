package json;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JsonParserTest {
    private JsonParser jsonParser = new JsonParser();

    @Test
    void should_parseValidJsonString() {
        JsonNode json = jsonParser.parse("{ _type: \"Position\", _id: 65483214, key: null, name: \"Oksywska\", " +
                "fullName: \"Oksywska,Poland\", iata_airport_code: null, type: \"location\", country: \"Poland\", " +
                "geo_position:{ latitude: 51.0855422, longitude: 16.9987442 }, location_id: 756423, inEurope: true,\n" +
                "countryCode: \"PL\", coreCountry: true, distance: null }");

        assertThat(json).isNotNull();
    }

    @Test
    void should_throwExceptionOnInvalidInput() {
        assertThatThrownBy(() -> jsonParser.parse("whatTheHell")).isInstanceOf(JsonParseException.class);
    }
}