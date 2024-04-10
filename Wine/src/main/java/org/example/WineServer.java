package org.example;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sun.net.httpserver.HttpServer;
import org.example.Wine;
import org.example.WineHandler;

public class WineServer {
    // Lista dei vini
    private List<Wine> wines = new ArrayList<>();

    // Inizializzazione del server
    public static void main(String[] args) throws Exception {
        // Creare un'istanza di WineServer
        WineServer wineServer = new WineServer();
        // Costruire la lista dei vini
        wineServer.buildWineList();

        // Creare il server HTTP sulla porta 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        // Passare un'istanza di WineServer al costruttore di WineHandler
        server.createContext("/wines", new WineHandler(wineServer));
        server.setExecutor(null); // Utilizziamo un executor predefinito
        server.start();
        System.out.println("Server started on port 8000");
    }

    // Costruisce la lista dei vini di esempio
    private void buildWineList() {
        wines.add(new Wine("Merlot", "Red", 25.99));
        wines.add(new Wine("Chardonnay", "White", 19.99));
        wines.add(new Wine("Pinot Noir", "Red", 30.99));
        wines.add(new Wine("Sauvignon Blanc", "White", 22.99));
    }

    // Ottiene la lista dei vini rossi
    String getRedWines() {
        StringBuilder response = new StringBuilder();
        for (Wine wine : wines) {
            if (wine.type.equals("Red")) {
                response.append(wine.name).append("\n");
            }
        }
        return response.toString();
    }

    // Ottiene la lista dei vini bianchi
    String getWhiteWines() {
        StringBuilder response = new StringBuilder();
        for (Wine wine : wines) {
            if (wine.type.equals("White")) {
                response.append(wine.name).append("\n");
            }
        }
        return response.toString();
    }

    // Ottiene la lista dei vini ordinata per nome
    String getWinesSortedByName() {
        List<String> sortedNames = new ArrayList<>();
        for (Wine wine : wines) {
            sortedNames.add(wine.name);
        }
        Collections.sort(sortedNames);
        return sortedNames.toString();
    }

    // Ottiene la lista dei vini ordinata per prezzo
    String getWinesSortedByPrice() {
        List<Double> sortedPrices = new ArrayList<>();
        for (Wine wine : wines) {
            sortedPrices.add(wine.price);
        }
        Collections.sort(sortedPrices);
        return sortedPrices.toString();
    }
}