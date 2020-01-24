package com.juleq.transactions.backend.service;

import java.util.List;

public interface TransactionProviderService {

    List<String> getTransactions(String location);
}