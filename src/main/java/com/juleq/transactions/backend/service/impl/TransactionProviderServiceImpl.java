package com.juleq.transactions.backend.service.impl;

import com.juleq.transactions.backend.service.TransactionProviderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionProviderServiceImpl implements TransactionProviderService {

    /**
     * Reads the file from location which contains the list of M transactions location. The order of lines will stay
     * the same.
     *
     * @param location the location of input file with transaction
     * @return the string representing the list of transactions and order for given partner
     */
    @Override
    public List<String> getTransactions(String location) {
        return null;
    }
}