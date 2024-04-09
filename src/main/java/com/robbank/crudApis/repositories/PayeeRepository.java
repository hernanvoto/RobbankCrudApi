package com.robbank.crudApis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robbank.crudApis.model.Payee;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long> {

//    Optional<Bank> findBankByName(final String name);
}
