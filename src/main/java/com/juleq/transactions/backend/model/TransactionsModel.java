package com.juleq.transactions.backend.model;

import java.util.List;

public class TransactionsModel {

    public TransactionsModel(List<TransactionModel> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionModel> transactions;
}