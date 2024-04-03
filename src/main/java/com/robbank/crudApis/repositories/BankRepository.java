package com.robbank.crudApis.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.robbank.crudApis.model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query("SELECT s From Student s WHERE s.email = ?1") // JPA
    Optional<Bank> findBankByName(final String name);
}
