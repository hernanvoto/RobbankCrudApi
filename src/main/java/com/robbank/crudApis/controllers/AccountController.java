package com.robbank.crudApis.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robbank.crudApis.model.Account;
import com.robbank.crudApis.model.CreditAccount;
import com.robbank.crudApis.model.CreditAccount.CardLevel;
import com.robbank.crudApis.model.CreditAccount.CardType;
import com.robbank.crudApis.model.SavingsAccount;
import com.robbank.crudApis.model.TransactionAccount;
import com.robbank.crudApis.request.CreditAccountRequest;
import com.robbank.crudApis.request.SavingsAccountRequest;
import com.robbank.crudApis.request.TransactionAccountRequest;
import com.robbank.crudApis.services.AccountService;

@RestController @RequestMapping(path = "api/v1/robbankapi/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    public AccountController() {

    }

    @PostMapping("/savings")
    public ResponseEntity<SavingsAccount> createSavingsAccount(@RequestBody SavingsAccountRequest request) {

        // Convert request parameters to appropriate types
//        long bankId = request.getBankId();
        long customerId = request.getCustomerId();
        String accountName = request.getAccountName();
        double interestRate = request.getInterestRate();
        double initialDeposit = request.getInitialDeposit();

        SavingsAccount savingsAccount = accountService.createSavingsAccount(customerId, accountName, interestRate,
                Optional.ofNullable(initialDeposit));

        return ResponseEntity.status(HttpStatus.CREATED).body(savingsAccount);
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionAccount> createTransactionAccount(@RequestBody TransactionAccountRequest request) {

        long customerId = request.getCustomerId();
        String accountName = request.getAccountName();
        double minBalance = request.getMinBalance();
        double initialDeposit = request.getInitialDeposit();

        TransactionAccount transactionAccount = accountService.createTransactionAccount(customerId, accountName,
                minBalance, Optional.ofNullable(initialDeposit), request.getOverdraftLimit());

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionAccount);
    }

    @PostMapping("/credit")
    public ResponseEntity<Account> createCreditAccount(@RequestBody CreditAccountRequest request) {

        long customerId = request.getCustomerId();
        String accountName = request.getAccountName();
        double creditLimit = request.getCreditLimit();
        CardLevel cardLevel = request.getCardLevel();
        CardType cardType = request.getCardType();

        CreditAccount creditAccount = accountService.createCreditAccount(customerId, accountName, creditLimit,
                cardLevel, cardType);

        return ResponseEntity.status(HttpStatus.CREATED).body(creditAccount);
    }
}