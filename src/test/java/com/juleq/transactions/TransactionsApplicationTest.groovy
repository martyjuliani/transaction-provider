package com.juleq.transactions;

import com.juleq.transactions.backend.controller.TransactionsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spock.lang.Specification;

@SpringBootTest
class TransactionsApplicationTest extends Specification {

    @Autowired (required = false)
    private TransactionsController controller

    def "when context is loaded then all expected beans are created"() {
        expect: "the TransactionsController is created"
        controller
    }
}