package com.example.flipkart.service;

import com.example.flipkart.model.*;
import com.example.flipkart.repository.OrderLogRepository;
import java.util.UUID;
import java.util.List;


/**
 * Orchestrates order placement, shipment, and notifies observers.
 */
public class OrderService {
    private final OrderLogRepository logRepo;

    public OrderService(OrderLogRepository logRepo) {
        this.logRepo = logRepo;
    }

    public Order placeOrder(User user, List<CartItem> cart) {
        String oid = UUID.randomUUID().toString();
        Order.Builder ob = new Order.Builder(oid, user);
        cart.forEach(ob::addItem);
        Order order = ob.build();

        // Subscribe all global listeners
        logRepo.getListeners().forEach(order::addListener);

        // Confirm order
        order.updateStatus(OrderStatus.CONFIRMED);
        return order;
    }

    public Shipment shipOrder(Order order, String address) {
        if (order.getStatus() != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Order not confirmed");
        }
        order.updateStatus(OrderStatus.SHIPPED);
        return new Shipment(UUID.randomUUID().toString(), order.getOrderId(), address);
    }

    public void deliverOrder(Order order) {
        if (order.getStatus() != OrderStatus.SHIPPED) {
            throw new IllegalStateException("Order not shipped");
        }
        order.updateStatus(OrderStatus.DELIVERED);
    }
}
