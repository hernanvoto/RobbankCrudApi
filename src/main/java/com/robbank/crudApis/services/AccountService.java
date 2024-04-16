package com.robbank.crudApis.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robbank.crudApis.model.Account;
import com.robbank.crudApis.model.CreditAccount;
import com.robbank.crudApis.model.CreditAccount.CardLevel;
import com.robbank.crudApis.model.CreditAccount.CardType;
import com.robbank.crudApis.model.Customer;
import com.robbank.crudApis.model.SavingsAccount;
import com.robbank.crudApis.model.TransactionAccount;
import com.robbank.crudApis.repositories.AccountRepository;
import com.robbank.crudApis.repositories.CustomerRepository;

@Service
public class AccountService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountNumberGeneratorService accountNumberGeneratorService;

    public SavingsAccount createSavingsAccount(
            long customerId,
            String accountName,
            double interestRate,
            Optional<Double> initialDeposit
    ) {

        // Retrieve bank and customer entities from repositories
        // Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new
        // IllegalArgumentException("Bank not found"));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException(
                "Customer not found"));

        Account account = new SavingsAccount(accountName, interestRate);
        // account.setBank(bank);
        account.setCustomer(customer);

        initialDeposit.ifPresent(account::deposit);
        // Generate account number
        account.setAccountNo(accountNumberGeneratorService.generateAccountNumber());

        return (SavingsAccount) accountRepository.save(account);
    }

    public TransactionAccount createTransactionAccount(
            long customerId,
            String accountName,
            double minBalance,
            Optional<Double> initialDeposit,
            double overdraftLimit
    ) {

        // Retrieve bank and customer entities from repositories
        // Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new
        // IllegalArgumentException("Bank not found"));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException(
                "Customer not found"));

        Account account = new TransactionAccount(accountName, minBalance, overdraftLimit);
        // account.setBank(bank);
        account.setCustomer(customer);

        initialDeposit.ifPresent(account::deposit);
        // Generate account number
        account.setAccountNo(accountNumberGeneratorService.generateAccountNumber());

        return (TransactionAccount) accountRepository.save(account);
    }

    public CreditAccount createCreditAccount(
            long customerId,
            String accountName,
            double creditLimit,
            CardLevel cardLevel,
            CardType cardType
    ) {

        // Retrieve bank and customer entities from repositories
        // Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new
        // IllegalArgumentException("Bank not found"));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException(
                "Customer not found"));

        Account account = new CreditAccount(accountName, creditLimit, cardType, cardLevel);
        // account.setBank(bank);
        account.setCustomer(customer);

        // Generate account number
        account.setAccountNo(accountNumberGeneratorService.generateAccountNumber());

        return (CreditAccount) accountRepository.save(account);
    }

}
