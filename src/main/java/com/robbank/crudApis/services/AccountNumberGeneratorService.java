package com.robbank.crudApis.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * @Transactional is an annotation used in Spring to indicate that a method (or
 *                all methods in a class) should be executed within a
 *                transactional context. Transactions ensure the consistency and
 *                integrity of data by grouping database operations into a
 *                single unit of work.
 */
@Service @Transactional
public class AccountNumberGeneratorService {

    @PersistenceContext
    private EntityManager entityManager;

//    public long generateAccountNumber() {
//
//        Optional<Long> latestAccountNumberOptional = entityManager.createQuery("SELECT MAX(a.accountNo) FROM Account a",
//                Long.class).getResultList().stream().findFirst();
//
//        long latestAccountNumber = latestAccountNumberOptional.orElse(0L);
//        return latestAccountNumber + 10;
//    }

    public String generateAccountNumber() {

        // Query to retrieve the next value from a sequence and add 10
        Long nextSequenceValue = entityManager.createQuery("SELECT nextval('account_number_sequence')", Long.class)
                .getSingleResult();

        return String.format("%08d", nextSequenceValue + 10);

    }
}
