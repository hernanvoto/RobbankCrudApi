package com.robbank.crudApis.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robbank.crudApis.model.Account;
import com.robbank.crudApis.model.Bank;
import com.robbank.crudApis.model.Customer;
import com.robbank.crudApis.model.SavingsAccount;
import com.robbank.crudApis.repositories.AccountRepository;
import com.robbank.crudApis.repositories.BankRepository;
import com.robbank.crudApis.repositories.CustomerRepository;

@Service
public class AccountService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountNumberGeneratorService accountNumberGeneratorService;

    public SavingsAccount createSavingsAccount(
            long bankId,
            long customerId,
            String accountName,
            double interestRate,
            double minBalance,
            Optional<Double> initialDeposit
    ) {

        // Retrieve bank and customer entities from repositories
        Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new IllegalArgumentException("Bank not found"));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException(
                "Customer not found"));

        Account account = new SavingsAccount(accountName, interestRate, minBalance);
        account.setBank(bank);
        account.setCustomer(customer);

        initialDeposit.ifPresent(account::deposit);
        // Generate account number
        account.setAccountNo(accountNumberGeneratorService.generateAccountNumber());

        return (SavingsAccount) accountRepository.save(account);
    }
}
