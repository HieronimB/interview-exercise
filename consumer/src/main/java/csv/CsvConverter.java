package csv;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class CsvConverter {
    //obie metody convert wygladaja prawie tak samo, wystarczylo przekazac pierwsza fcje map jako argument do ogolnej metody
    public String convert(List<JsonNode> jsonData, List<String> format) {
        return jsonData.stream()
                .map(node -> extractFieldsUsingFormatter(node, format))
                .map(s -> s.collect(Collectors.joining(",")))
                .collect(Collectors.joining("\n"));
    }

    public String convert(List<JsonNode> jsonData) {
        return jsonData.stream()
                .map(this::flattenJson)
                .map(s -> s.collect(Collectors.joining(","))) // Kod tworzący CSV duplikuje się
                .collect(Collectors.joining("\n"));
    }

    //Probably not the best idea to use recursion in java because of lack of tail recursion optimization, this will blow up if json will be too large
    private Stream<String> flattenJson(JsonNode jsonNode) {
        if (jsonNode.isValueNode()) {
            return Stream.of(jsonNode.asText());
        }

        Stream<JsonNode> jsonNodeStream = convertToStream(jsonNode);
        return jsonNodeStream.flatMap(this::flattenJson);
    }

    private Stream<String> extractFieldsUsingFormatter(JsonNode jsonNode, List<String> format) {
        return format.stream()
                .flatMap(f -> extractField(jsonNode, f));
    }

    private Stream<String> extractField(JsonNode jsonNode, String fieldName) {
        JsonNode node = jsonNode.findValue(fieldName);
        if (node == null) {
            return Stream.empty();
        }

        if (node.isValueNode()) {
            return Stream.of(node.asText());
        }

        return flattenJson(node);
    }

    private Stream<JsonNode> convertToStream(JsonNode jsonNode) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(jsonNode.elements(), Spliterator.ORDERED), false);
    }

}
