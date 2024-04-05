package com.robbank.crudApis.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robbank.crudApis.model.Account;

@Repository
public interface SavingsAccount extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByAccountNo(final long accountNo);
//    List<Account> findAccountsByAccountNo(int accountNo);
}
