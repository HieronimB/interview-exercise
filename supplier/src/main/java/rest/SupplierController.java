package rest;

import com.fasterxml.jackson.databind.JsonNode;
import json.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import source.DataSource;
import source.JsonDataSource;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SupplierController {
    private DataSource<String> dataSource;
    private JsonParser jsonParser;

    @Autowired
    public SupplierController(JsonDataSource dataSource, JsonParser jsonParser) {
        this.dataSource = dataSource;
        this.jsonParser = jsonParser;
    }

    @GetMapping("/generate/json/{size}")
    public List<JsonNode> generateJson(@PathVariable int size) {
        Collection<String> jsonResult = dataSource.fetch(size);
        return jsonResult.stream().map(jsonParser::parse).collect(Collectors.toList());
    }
}
