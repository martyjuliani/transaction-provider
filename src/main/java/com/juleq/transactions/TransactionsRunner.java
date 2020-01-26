package com.juleq.transactions;

import com.juleq.transactions.backend.converter.TransactionsConverter;
import com.juleq.transactions.backend.service.TransactionsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TransactionsRunner implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(TransactionsRunner.class);
    private final TransactionsService transactionsService;
    private final TransactionsConverter converter;

    public TransactionsRunner(TransactionsService transactionsService, TransactionsConverter converter) {
        this.transactionsService = transactionsService;
        this.converter = converter;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException("Incorrect input entered.");
        }
        transactionsService.saveTransactions(args[0]);
        String transactions = converter.toString(transactionsService.getTransactions());
        System.out.println(transactions);
    }
}