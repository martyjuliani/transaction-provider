package com.juleq.transactions.backend.repository;

import com.juleq.transactions.backend.entity.Transaction;

import java.util.List;

public interface TransactionsRepositoryCustom {

    List<Transaction> getSortedPartnerTransactions(String partner);
}