import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;

public class SimpleServer {
    public static void main(String[] args) throws Exception {
        // Start server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/search", new SearchHandler());
        server.setExecutor(null);
        System.out.println("Java Backend running at http://localhost:8080/search");
        server.start();
    }
}
class SearchHandler implements HttpHandler {
    private final Map<String, String> entries = new HashMap<>();

    public SearchHandler() {
        // Sample data (like database, but stored in memory)
        entries.put("1", "Red running shoe with white stripes");
        entries.put("2", "Blue formal shirt for office wear");
        entries.put("3", "Wireless noise-cancelling headphones");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            // Read the request body (query text)
            InputStream is = exchange.getRequestBody();
            String query = new String(is.readAllBytes()).toLowerCase();

            // Simple search: return all items that contain the query word
            List<String> results = new ArrayList<>();
            for (String value : entries.values()) {
                if (value.toLowerCase().contains(query)) {
                    results.add(value);
                }
            }

            // Build response
            String response = String.join("\n", results);
            if (results.isEmpty()) {
                response = "No results found for: " + query;
            }

            // Send response
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
