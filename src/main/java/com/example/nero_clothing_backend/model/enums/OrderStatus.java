package com.example.nero_clothing_backend.model.enums;

public enum OrderStatus {
    NEW,        // Order created, awaiting payment
    CANCELLED,  // Order cancelled
    PAID,       // Payment received, awaiting shipment
    SHIPPED,    // Order dispatched to the customer
    DELIVERED   // Order successfully delivered
}
