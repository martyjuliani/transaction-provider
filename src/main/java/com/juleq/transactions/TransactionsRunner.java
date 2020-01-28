package com.juleq.transactions;

import com.juleq.transactions.backend.entity.Transaction;
import com.juleq.transactions.backend.service.TransactionsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class TransactionsRunner implements CommandLineRunner {

    private final TransactionsService transactionsService;

    public TransactionsRunner(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException("Incorrect input entered.");
        }
        transactionsService.saveTransactions(args[0]);
        toString(transactionsService.getTransactions());
        transactionsService.deleteAll();
    }

    private void toString(Iterable<Transaction> transactions) {
        for (Transaction t : transactions) {
            String orderId = transactionsService.getOrderId(t.getPartner(), t.getDateTime());
            System.out.println(format("%s|%s|%s", t.getPartner(), orderId, t.getName()));
        }
    }
}