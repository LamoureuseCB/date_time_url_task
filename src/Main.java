import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final int PORT = 8080;
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/hello", new HelloHandler());
        httpServer.createContext("/date", new DateTimeHandler());
        httpServer.start();
    }
}

class HelloHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Началась обработка /hello запроса от клиента.");
        String response = "Вы успешно попали на сервер";
        exchange.sendResponseHeaders(200, 0);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}

class DateTimeHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Началась обработка /datetime запроса от клиента");
        String headUrl = "http://localhost:8080/";
        LocalDateTime localDateTime = LocalDateTime.now();

        String dateTimeRequestUrl = urlBuilder(headUrl, localDateTime);
        exchange.sendResponseHeaders(200, 0);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(dateTimeRequestUrl.getBytes());
        }
    }

    public static String urlBuilder(String beginUrl, LocalDateTime localDateTime) {
        StringBuilder stringBuilder = new StringBuilder(beginUrl);
        stringBuilder.append("?");
        String dateTime = localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        stringBuilder.append("date=")
                .append(dateTime);
        return stringBuilder.toString();
    }
}
