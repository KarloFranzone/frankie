package org.example;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

// Gestore delle richieste HTTP relative ai vini
public class WineHandler implements HttpHandler {
    private WineServer server; // Riferimento a WineServer

    // Costruttore che accetta un riferimento a WineServer
    public WineHandler(WineServer server) {
        this.server = server;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();

        // Gestione delle richieste GET
        if (requestMethod.equalsIgnoreCase("GET")) {
            // Estrarre il comando dalla richiesta
            String command = extractCommand(exchange.getRequestURI().getPath());

            String response = "";

            // Switch per gestire diversi comandi
            switch (command) {
                case "red":
                    response = server.getRedWines(); // Utilizzo del metodo di WineServer
                    break;
                case "white":
                    response = server.getWhiteWines(); // Utilizzo del metodo di WineServer
                    break;
                case "sorted_by_name":
                    response = server.getWinesSortedByName(); // Utilizzo del metodo di WineServer
                    break;
                case "sorted_by_price":
                    response = server.getWinesSortedByPrice(); // Utilizzo del metodo di WineServer
                    break;
                default:
                    response = "Invalid command";
            }

            // Invia la risposta al client
            sendResponse(exchange, response);
        }
    }

    // Estrae il comando dalla richiesta
    private String extractCommand(String path) {
        return path.substring(7); // Rimuovere "/wines/"
    }

    // Invia la risposta al client
    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}