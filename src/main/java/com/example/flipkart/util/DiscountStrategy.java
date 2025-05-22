package com.example.flipkart.util;

/**
 * Strategy interface for applying a discount to a raw total.
 */
public interface DiscountStrategy {
    /** Returns the final total after discount. */
    double apply(double rawTotal);
}
