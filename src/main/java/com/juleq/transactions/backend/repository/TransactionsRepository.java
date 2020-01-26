package com.juleq.transactions.backend.repository;

import com.juleq.transactions.backend.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionsRepository extends CrudRepository<Transaction, Long> {}