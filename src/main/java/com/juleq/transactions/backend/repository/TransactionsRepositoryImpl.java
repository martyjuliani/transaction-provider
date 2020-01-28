package com.juleq.transactions.backend.repository;

import com.juleq.transactions.backend.entity.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TransactionsRepositoryImpl implements TransactionsRepositoryCustom {

    final EntityManager em;

    public TransactionsRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Transaction> getOrderedPartnerTransactions(String partner) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);
        cq.where(cb.equal(root.get("partner"), partner));
        cq.orderBy(cb.asc(root.get("dateTime")));
        return em.createQuery(cq).getResultList();
    }
}