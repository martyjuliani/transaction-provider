package com.juleq.transactions.backend.controller;

import com.juleq.transactions.backend.service.TransactionProviderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transaction-provider")
public class TransactionProviderController {

    private final TransactionProviderService providerService;

    public TransactionProviderController(TransactionProviderService providerService) {
        this.providerService = providerService;
    }

    /**
     * REST API for retrieve all transactions.
     * Example: GET http://localhost:8080/transaction-provider/transactions
     *
     * @return the list of all translations with Http status 200 OK or 500 in case of any error
     */
    @GetMapping(value = "/transactions", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody List<String> getTransactions(String location) {
        return providerService.getTransactions(location);
    }
}