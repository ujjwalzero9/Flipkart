package com.example.flipkart.service;

import com.example.flipkart.model.*;
import com.example.flipkart.util.DiscountStrategy;
import java.util.*;

/**
 * Factory for creating carts and calculating totals with a discount strategy.
 */
public class CartService {
    private final DiscountStrategy discountStrategy;

    public CartService(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    /** Build a new empty cart (just a list of CartItem). */
    public List<CartItem> createCart() {
        return new ArrayList<>();
    }

    /** Compute total after applying discount. */
    public double calculateTotal(List<CartItem> cart) {
        double sum = cart.stream()
                         .mapToDouble(CartItem::lineTotal)
                         .sum();
        return discountStrategy.apply(sum);
    }
}
