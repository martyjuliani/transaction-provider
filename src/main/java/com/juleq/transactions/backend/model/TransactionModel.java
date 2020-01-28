package com.juleq.transactions.backend.model;

public class TransactionModel {
    public String partner;
    public String orderId;
    public String name;

    public TransactionModel(String partner, String orderId, String name) {
        this.partner = partner;
        this.orderId = orderId;
        this.name = name;
    }
}
