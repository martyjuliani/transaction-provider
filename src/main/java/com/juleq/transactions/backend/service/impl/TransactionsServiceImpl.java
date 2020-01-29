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
     * Reads transactions from the file line by line, and saves them to the H2 database.
     *
     * @param location the string of transaction separated in each line
     */
    @Override
    public void saveTransactions(String location) {
        try (Stream<String> lines = Files.lines(Paths.get(location))) {
            lines.forEach(this::saveTransaction);
        } catch (IOException e) {
            throw new IllegalArgumentException("Not possible to read input file from location '" + location + "'.");
        }
    }

    /**
     * Reads transactions from the text, line by line, and saves them to the H2 database.
     *
     * @param transactions the string of transaction separated in each line
     */
    @Override
    public void saveTransaction(String transactions) {
        String[] lines = transactions.split("\\r?\\n");
        for (String line: lines) {
            Transaction entity = parseTransaction(line);
            repository.save(entity);
        }
    }

    /**
     * Gets all transactions from the H2 database.
     *
     * @return the transactions in the specific format
     */
    @Override
    public Iterable<Transaction> getTransactions() {
        return repository.findAll();
    }

    /**
     * Gets order id of the transaction uniquely defined by partner name and date time of transaction.
     *
     * @param partner the name of partner
     * @param dateTime the date time when transaction was performed
     * @return the order id of the transaction
     */
    @Override
    public String getOrderId(String partner, LocalDateTime dateTime) {
        List<Transaction> transactions = repository.getSortedPartnerTransactions(partner);
        int paddingSize = String.valueOf(transactions.size()).length();

        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getDateTime().equals(dateTime)) {
                return String.format("%0" + paddingSize + "d", i + 1);
            }
        }
        throw new IllegalStateException("Not existing transaction for partner.");
    }

    /**
     * Deletes all transaction from H2 database. It is due to simultaneous run from web and command line, we need to
     * work just one set of transactions.
     */
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    private Transaction parseTransaction(String transaction) {
        CodePointCharStream input = CharStreams.fromString(transaction);
        TransactionsLexer lexer = new TransactionsLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        TransactionsParser parser = new TransactionsParser(tokenStream);
        TransactionsParser.StartContext tree = parser.start();
        TransactionVisitorImpl visitor = new TransactionVisitorImpl();
        return visitor.visit(tree);
    }
}