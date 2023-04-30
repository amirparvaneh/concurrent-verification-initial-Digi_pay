import sample.eclipsesource.json.*;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.stream.Collectors;

public class SampleHttpResponseHandler implements HttpHandler {
    private final int port;
    private final String expectedTransactionId;
    private final long sleep;

    public SampleHttpResponseHandler(String expectedTransactionId, int port, long sleep) {
        this.expectedTransactionId = expectedTransactionId;
        this.port = port;
        this.sleep = sleep;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ignored) {
        }
        String responseBody = "{\"success\":" + (verifyRequest(exchange) ? "true" : "false") + ",\"port\":" + port + "}";
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, responseBody.length());
        exchange.getResponseBody().write(responseBody.getBytes());
        exchange.close();
    }

    private boolean verifyRequest(HttpExchange exchange) {
        if (!exchange.getRequestMethod().equals("POST")) {
            return false;
        }
        String requestBody = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))
                .lines().collect(Collectors.joining("\n"));
        JsonObject object = Json.parse(requestBody).asObject();
        String transactionId = object.get("transaction_id").asString();
        return transactionId.equals(expectedTransactionId);
    }
}
