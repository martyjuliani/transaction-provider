package com.juleq.transactions.backend.parser;

import com.juleq.transactions.TransactionsBaseVisitor;
import com.juleq.transactions.TransactionsParser;
import com.juleq.transactions.backend.entity.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionVisitorImpl extends TransactionsBaseVisitor<Transaction> {

    Logger logger = LogManager.getLogger(TransactionVisitorImpl.class);

    @Override
    public Transaction visitStart(TransactionsParser.StartContext ctx) {
        Transaction transaction = new Transaction();
        transaction.setName(ctx.name.getText());
        transaction.setPartner(ctx.partner.getText());
        transaction.setPhone(ctx.phone.getText());
        transaction.setDateTime(parseDateTime(ctx.date.getText(), ctx.time.getText()));
        logger.debug("read transaction: " + transaction.toString());
        return transaction;
    }

    private static LocalDateTime parseDateTime(String date, String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date + " " + time, formatter);
    }
}