package com.juleq.transactions.backend.solution;

import com.juleq.transactions.backend.entity.Transaction;
import com.juleq.transactions.backend.service.TransactionsService;
import org.springframework.stereotype.Component;

@Component
public class Solution {

    private final TransactionsService transactionsService;

    public Solution(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    public String solution(String location) {
        transactionsService.saveTransactions(location);
        String result = toString(transactionsService.getTransactions());
        transactionsService.deleteAll();
        return result;
    }

    private String toString(Iterable<Transaction> transactions) {
        StringBuilder sb = new StringBuilder();
        for (Transaction t : transactions) {
            String orderId = transactionsService.getOrderId(t.getPartner(), t.getDateTime());
            sb.append(String.format("%s|%s|%s%s", t.getPartner(), orderId, t.getName(), System.lineSeparator()));
        }
        return sb.toString();
    }
}