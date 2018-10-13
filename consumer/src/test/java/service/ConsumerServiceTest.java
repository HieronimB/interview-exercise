package service;

import com.fasterxml.jackson.databind.JsonNode;
import extensions.MockitoExtension;
import httpclient.SupplierClient;
import json.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.net.http.HttpResponse;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsumerServiceTest {
    @Mock
    private SupplierClient supplierClient;
    @Mock
    private HttpResponse<String> httpResponse;

    private JsonParser jsonParser = new JsonParser();

    private ConsumerService consumerService;

    @BeforeEach
    void beforeEach() {
        consumerService = new ConsumerService(supplierClient, jsonParser);
    }

    @Test
    void should_returnListOfJsonNodesWhenClientReturnJsonArray() {
        when(httpResponse.body()).thenReturn("[{ \"foo\": \"bar\", \"fuz\":\"baz\"}," +
                                             "{ \"foo\": \"bar\", \"fuz\":\"baz\"}," +
                                             "{ \"foo\": \"bar\", \"fuz\":\"baz\"}]");
        when(supplierClient.get(anyString())).thenReturn(httpResponse);

        List<JsonNode> jsonData = consumerService.getJsonData(10);

        assertThat(jsonData).hasSize(3);
    }

    @Test
    void should_returnListWithOneElementWhenClientDoesntReturnJsonArray() {
        when(httpResponse.body()).thenReturn("{ \"foo\": \"bar\", \"fuz\":\"baz\"}");
        when(supplierClient.get(anyString())).thenReturn(httpResponse);

        List<JsonNode> jsonData = consumerService.getJsonData(10);

        assertThat(jsonData).hasSize(1);
    }

}