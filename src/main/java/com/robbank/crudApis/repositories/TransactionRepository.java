package com.robbank.crudApis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robbank.crudApis.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
