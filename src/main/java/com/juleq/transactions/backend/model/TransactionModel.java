package com.juleq.transactions.backend.model;

public class TransactionModel {
    public String partner;
    public String order;
    public String name;

    public TransactionModel(String partner, String order, String name) {
        this.partner = partner;
        this.order = order;
        this.name = name;
    }
}
