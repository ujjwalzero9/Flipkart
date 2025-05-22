package com.example.flipkart.model;

import com.example.flipkart.model.Order;
import com.example.flipkart.model.OrderStatus;

/**
 * Observer interface for listening to order status updates.
 */
public interface EventListener {
    void onEvent(Order order, OrderStatus newStatus);
}
