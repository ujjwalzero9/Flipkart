package com.example.flipkart.model;

import java.util.*;

/**
 * Represents a placed order, built via the Builder pattern.
 * Observers can subscribe to status changes.
 */
public class Order {
    private final String orderId;
    private final User customer;
    private final List<CartItem> items;
    private OrderStatus status;
    private final List<EventListener> listeners = new ArrayList<>();

    private Order(Builder b) {
        this.orderId   = b.orderId;
        this.customer  = b.customer;
        this.items     = Collections.unmodifiableList(b.items);
        this.status    = OrderStatus.CREATED;
    }

    public String getOrderId()       { return orderId; }
    public User getCustomer()        { return customer; }
    public List<CartItem> getItems() { return items; }
    public OrderStatus getStatus()   { return status; }

    /** Change status and notify observers. */
    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
        listeners.forEach(l -> l.onEvent(this, newStatus));
    }

    public void addListener(EventListener l) { listeners.add(l); }

    public static class Builder {
        private final String orderId;
        private final User customer;
        private final List<CartItem> items = new ArrayList<>();

        public Builder(String orderId, User customer) {
            this.orderId  = orderId;
            this.customer = customer;
        }

        public Builder addItem(CartItem ci) {
            items.add(ci);
            return this;
        }

        public Order build() {
            if (items.isEmpty()) {
                throw new IllegalStateException("Cannot create order with no items");
            }
            return new Order(this);
        }
    }
}
