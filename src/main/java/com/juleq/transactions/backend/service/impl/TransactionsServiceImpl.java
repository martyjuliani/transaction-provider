package com.juleq.transactions.backend.service.impl;

import com.juleq.transactions.TransactionsLexer;
import com.juleq.transactions.TransactionsParser;
import com.juleq.transactions.backend.entity.Transaction;
import com.juleq.transactions.backend.parser.TransactionVisitorImpl;
import com.juleq.transactions.backend.repository.TransactionsRepository;
import com.juleq.transactions.backend.service.TransactionsService;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private final TransactionsRepository repository;

    public TransactionsServiceImpl(TransactionsRepository repository) {
        this.repository = repository;
    }

    /**
     * Reads the file from location which contains the list of M transactions location and saves them to the database.
     * The order of lines will stay the same.
     *
     * @param location the location of input file with transaction
     */
    @Override
    public void saveTransactions(String location) {
        try (Stream<String> lines = Files.lines(Paths.get(location))) {
            lines.forEach(this::saveTransaction);
        } catch (IOException e) {
            throw new IllegalArgumentException("Not possible to read input file from location '" + location + "'.");
        }
    }

    @Override
    public Iterable<Transaction> getTransactions() {
        return repository.findAll();
    }

    @Override
    public String getOrderId(String partner, LocalDateTime dateTime) {
        List<Transaction> transactions = repository.getOrderedPartnerTransactions(partner);
        int paddingSize = String.valueOf(transactions.size()).length();

        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getDateTime().equals(dateTime)) {
                return String.format("%0" + paddingSize + "d", i + 1);
            }
        }
        throw new IllegalStateException("Not existing transaction for partner.");
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    public void saveTransaction(String line) {
        Transaction transaction = parseTransaction(line);
        repository.save(transaction);
    }

    private Transaction parseTransaction(String line) {
        CodePointCharStream input = CharStreams.fromString(line);
        TransactionsLexer lexer = new TransactionsLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        TransactionsParser parser = new TransactionsParser(tokenStream);
        TransactionsParser.StartContext tree = parser.start();
        TransactionVisitorImpl visitor = new TransactionVisitorImpl();
        return visitor.visit(tree);
    }
}