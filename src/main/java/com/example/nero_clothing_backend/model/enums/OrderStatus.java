package com.example.nero_clothing_backend.model.enums;

public enum OrderStatus {
    NEW, // złożone, czeka na opłacenie
    CANCELLED, // anulowane
    PAID, // opłacone, czeka na wysyłkę
    SHIPPED, // wysłane
    DELIVERED, // dostarczone
}
