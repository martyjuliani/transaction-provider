package com.juleq.transactions.backend.service;

import com.juleq.transactions.backend.entity.Transaction;

import java.util.List;

public interface TransactionsService {

    void saveTransactions(String location);

    List<Transaction> getTransactions();
}