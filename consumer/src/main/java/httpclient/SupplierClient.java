package httpclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class SupplierClient {
    private String host;
    private int port;
    private HttpClient httpClient;

    public SupplierClient(@Value("${supplier.host}") String host,@Value("${supplier.port}") int port) { // Brakuje spacji po przecinku
        this.host = host;
        this.port = port;
        this.httpClient = HttpClient.newBuilder().build();
    }

    public HttpResponse<String> get(String path) {
        String url = String.format("http://%s:%d/%s", host, port, path);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new HttpClientException(String.format("Could not send http request. Url: %s", url), e);
        }
    }
}
