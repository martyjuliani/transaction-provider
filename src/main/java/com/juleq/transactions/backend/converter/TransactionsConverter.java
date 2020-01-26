package com.juleq.transactions.backend.converter;

import com.juleq.transactions.backend.entity.Transaction;
import com.juleq.transactions.backend.model.TransactionModel;
import com.juleq.transactions.backend.model.TransactionsModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionsConverter {

    public TransactionsModel toModel(List<Transaction> transactions) {
        List<TransactionModel> models = transactions.stream()
                .map(t -> new TransactionModel(t.getPartner(), t.getOrder(), t.getName()))
                .collect(Collectors.toCollection(ArrayList::new));
        return new TransactionsModel(models);
    }

    public String toString(List<Transaction> transactions) {
        return transactions.stream()
                .map(t -> String.format("%s|%s|%s", t.getPartner(), t.getOrder(), t.getName()))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
