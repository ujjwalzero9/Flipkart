package com.example.flipkart.model;

/**
 * A single product listing with category, name, price and stock.
 */
public class Product {
    private final String id;
    private final String name;
    private final Category category;
    private final String brand;
    private final double price;
    private int availableQty;

    /** 
     * Private constructor used by the builder.
     */
    private Product(Builder b) {
        this.id           = b.id;
        this.name         = b.name;
        this.category     = b.category;
        this.brand        = b.brand;
        this.price        = b.price;
        this.availableQty = b.availableQty;
    }

    // Getters
    public String getId()               { return id; }
    public String getName()             { return name; }
    public Category getCategory()       { return category; }
    public String getBrand()            { return brand; }
    public double getPrice()            { return price; }
    public int getAvailableQty()        { return availableQty; }

    /** Reserve (subtract) stock when an order is placed. */
    public void reserveStock(int qty) {
        if (qty < 0 || qty > availableQty) {
            throw new IllegalArgumentException("Invalid quantity to reserve");
        }
        availableQty -= qty;
    }

    /** Builder Pattern for Product creation. */
    public static class Builder {
        private final String id;
        private final String name;
        private final Category category;
        private final String brand;
        private final double price;
        private int availableQty = 0;

        public Builder(String id, String name, Category category, String brand, double price) {
            this.id       = id;
            this.name     = name;
            this.category = category;
            this.brand    = brand;
            this.price    = price;
        }

        public Builder stock(int qty) {
            this.availableQty = qty;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    @Override
    public String toString() {
        return String.format("%s[%s:%s] $%.2f (qty=%d)",
            brand, id, name, price, availableQty);
    }
}
