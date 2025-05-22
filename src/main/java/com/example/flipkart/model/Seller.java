package com.example.flipkart.model;

/**
 * A marketplace seller who supplies products.
 */
public class Seller {
    private final String sellerId;
    private final String name;

    public Seller(String sellerId, String name) {
        this.sellerId = sellerId;
        this.name     = name;
    }

    public String getSellerId() { return sellerId; }
    public String getName()     { return name; }

    @Override
    public String toString() {
        return String.format("Seller[%s - %s]", sellerId, name);
    }
}
