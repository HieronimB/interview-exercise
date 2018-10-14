package rest;

import com.fasterxml.jackson.databind.JsonNode;
import csv.CsvConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ConsumerService;

import java.util.List;

@RestController
public class ConsumerController {
    private CsvConverter csvConverter;
    private ConsumerService consumerService;

    @Autowired
    public ConsumerController(CsvConverter csvConverter, ConsumerService consumerService) {
        this.csvConverter = csvConverter;
        this.consumerService = consumerService;
    }

    @GetMapping(value = "/csv", produces = "application/octet-stream", params = "format")
    public String getCsvWithFormatting(@RequestParam("numberOfLines") int numberOfLines,
                                       @RequestParam(value = "format")List<String> format) {
        List<JsonNode> jsonData = consumerService.getJsonData(numberOfLines);
        return csvConverter.convert(jsonData, format);
    }

    @GetMapping(value = "/csv", produces = "application/octet-stream")
    public String getCsv(@RequestParam("numberOfLines") int numberOfLines) {
        List<JsonNode> jsonData = consumerService.getJsonData(numberOfLines);
        return csvConverter.convert(jsonData);
    }
}
