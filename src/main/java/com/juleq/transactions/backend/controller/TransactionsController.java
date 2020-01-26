package com.juleq.transactions.backend.controller;

import com.juleq.transactions.backend.converter.TransactionsConverter;
import com.juleq.transactions.backend.model.TransactionsModel;
import com.juleq.transactions.backend.service.TransactionsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionsController {

    private final TransactionsService transactionsService;
    private final TransactionsConverter converter;

    public TransactionsController(TransactionsService transactionsService, TransactionsConverter converter) {
        this.transactionsService = transactionsService;
        this.converter = converter;
    }

    /**
     * REST API for retrieve all transactions.
     * Example: GET http://localhost:8080/transactions with request body with location of file
     *
     * param location the location of input file
     * @return the list of all translations with Http status 200 OK or 500 in case of any error
     */
    @GetMapping(produces = {MediaType.TEXT_PLAIN_VALUE}, consumes = {MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody ResponseEntity<TransactionsModel> getTransactions(@RequestBody String location) {
        transactionsService.saveTransactions(location);
        TransactionsModel transactions = converter.toModel(transactionsService.getTransactions());
        return ResponseEntity.ok(transactions);
    }
}