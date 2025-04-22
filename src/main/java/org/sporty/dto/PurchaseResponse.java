package org.sporty.dto;

public class PurchaseResponse {
    private double totalPrice;
    private String message;

    public PurchaseResponse() {}

    public PurchaseResponse(double totalPrice, String message) {
        this.totalPrice = totalPrice;
        this.message = message;
    }

    // Getters and setters
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}