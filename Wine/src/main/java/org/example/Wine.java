package org.example;

// Classe che rappresenta un vino
public class Wine {
    public String name;
    public String type;
    public double price;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public Wine(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }
}