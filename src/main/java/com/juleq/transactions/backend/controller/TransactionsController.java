package com.juleq.transactions.backend.controller;

import com.juleq.transactions.backend.entity.Transaction;
import com.juleq.transactions.backend.model.TransactionModel;
import com.juleq.transactions.backend.model.TransactionsModel;
import com.juleq.transactions.backend.service.TransactionsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionsController {

    private final TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    /**
     * REST API for retrieve all transactions in specified format.
     * Example: GET http://localhost:8080/transactions with request body with transactions
     *
     * param transactions the string with transactions, the each one is placed in the new line
     * @return the list of all translations with Http status 200 OK or 500 in case of any error
     */
    @GetMapping(produces = {MediaType.TEXT_PLAIN_VALUE}, consumes = {MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody ResponseEntity<TransactionsModel> getTransactions(@RequestBody String transactions) {
        transactionsService.saveTransactions(transactions);
        TransactionsModel model = toModel(transactionsService.getTransactions());
        transactionsService.deleteAll();
        return ResponseEntity.ok(model);
    }

    private TransactionsModel toModel(Iterable<Transaction> transactions) {
        List<TransactionModel> models = new ArrayList<>();
        for (Transaction t : transactions) {
            String orderId = transactionsService.getOrderId(t.getPartner(), t.getDateTime());
            TransactionModel model = new TransactionModel(t.getPartner(), orderId, t.getName());
            models.add(model);
        }
        return new TransactionsModel(models);
    }
}