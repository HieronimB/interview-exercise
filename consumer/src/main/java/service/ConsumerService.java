package service;

import com.fasterxml.jackson.databind.JsonNode;
import httpclient.SupplierClient;
import json.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumerService {
    private SupplierClient supplierClient;
    private JsonParser jsonParser;

    @Autowired // Zbędne, Spring tak czy inaczej wybierze ten konstruktor
    public ConsumerService(SupplierClient supplierClient, JsonParser jsonParser) {
        this.supplierClient = supplierClient;
        this.jsonParser = jsonParser;
    }

    public List<JsonNode> getJsonData(int size) {
        HttpResponse<String> response = supplierClient.get("/generate/json/" + size); // Zbędny '/' na początku ścieżki, get() go dokłada
        JsonNode json = jsonParser.parse(response.body());

        List<JsonNode> jsonNodes = new ArrayList<>();
        if (json.isArray()) {
            json.elements().forEachRemaining(jsonNodes::add);
        } else {
            jsonNodes.add(json);
        }

        return jsonNodes;
    }
}
