package httpclient;

public class HttpClientException extends RuntimeException {
    public HttpClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
