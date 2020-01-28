package com.juleq.transactions.backend.service;

import com.juleq.transactions.backend.entity.Transaction;

import java.time.LocalDateTime;

public interface TransactionsService {

    void saveTransactions(String transactions);

    void saveTransaction(String transaction);

    Iterable<Transaction> getTransactions();

    String getOrderId(String partner, LocalDateTime dateTime);

    void deleteAll();
}