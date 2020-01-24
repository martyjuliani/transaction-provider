package com.juleq.transactions;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@EnableVaadin
public class TransactionProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionProviderApplication.class, args);
    }
}
