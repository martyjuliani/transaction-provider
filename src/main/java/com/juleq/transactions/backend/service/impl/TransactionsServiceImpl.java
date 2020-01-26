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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private static final Logger logger = LogManager.getLogger(TransactionsServiceImpl.class);
    private final TransactionsRepository transactionsRepository;

    public TransactionsServiceImpl(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
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
            logger.error("Not possible to read input file from location '" + location + "'.");
            throw new IllegalArgumentException("Invalid input file location.");
        }
    }

    @Override
    public List<Transaction> getTransactions() {
        return null;
    }

    private void saveTransaction(String line) {
        Transaction transaction = parseTransaction(line);
        transactionsRepository.save(transaction);
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