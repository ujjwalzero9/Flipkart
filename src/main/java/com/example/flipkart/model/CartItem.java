package com.example.flipkart.model;

/**
 * One line‐item in a shopping cart.
 */
public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product  = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity()    { return quantity; }
    public double lineTotal()   { return product.getPrice() * quantity; }

    /** Increase quantity in cart. */
    public void increase(int add) {
        if (add <= 0) throw new IllegalArgumentException("Must add > 0");
        this.quantity += add;
    }
}
