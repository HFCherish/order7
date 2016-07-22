package com.thoughtworks.ketsu.domain.user;

public class Payment {
    private long orderId;
    private double amount;
    private PayType type;

    public long getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public PayType getType() {
        return type;
    }
}
