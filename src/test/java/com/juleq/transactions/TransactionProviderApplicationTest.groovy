package com.juleq.transactions;

import com.juleq.transactions.backend.controller.TransactionProviderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spock.lang.Specification;

@SpringBootTest
class TransactionProviderApplicationTest extends Specification {

    @Autowired (required = false)
    private TransactionProviderController controller

    def "when context is loaded then all expected beans are created"() {
        expect: "the CipherTranslatorController is created"
        controller
    }
}