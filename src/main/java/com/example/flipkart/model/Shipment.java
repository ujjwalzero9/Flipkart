package com.example.flipkart.model;

/**
 * Shipment details associated with an order.
 */
public class Shipment {
    private final String shipmentId;
    private final String orderId;
    private final String address;

    public Shipment(String shipmentId, String orderId, String address) {
        this.shipmentId = shipmentId;
        this.orderId    = orderId;
        this.address    = address;
    }

    @Override
    public String toString() {
        return String.format("Shipment[%s] for Order[%s] to %s",
            shipmentId, orderId, address);
    }
}
