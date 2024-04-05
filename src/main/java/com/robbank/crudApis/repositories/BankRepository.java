package com.robbank.crudApis.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robbank.crudApis.model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findBankById(final Long id);

    Optional<Bank> findBankByName(final String name);
}
