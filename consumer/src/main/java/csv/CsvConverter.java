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
    public String convert(List<JsonNode> jsonData, List<String> format) {
        Stream<Stream<String>> csvStream;
        if (format != null && format.size() > 0) {
            csvStream = jsonData.stream()
                    .map(node -> convert(node, format));
        } else {
            csvStream = jsonData.stream()
                    .map(this::flattenJson);
        }

        return csvStream
                .map(s -> s.collect(Collectors.joining(",")))
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

    private Stream<String> convert(JsonNode jsonNode, List<String> format) {
        return format.stream()
                .flatMap(f -> convert(jsonNode, f));
    }

    private Stream<String> convert(JsonNode jsonNode, String fieldName) {
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
