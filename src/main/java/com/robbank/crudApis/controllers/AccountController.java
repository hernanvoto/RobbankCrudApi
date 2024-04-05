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
import com.robbank.crudApis.model.SavingsAccount;
import com.robbank.crudApis.request.SavingsAccountRequest;
import com.robbank.crudApis.services.AccountService;

@RestController @RequestMapping(path = "api/v1/robbankapi/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    public AccountController() {

    }

    @PostMapping("/savings")
    public ResponseEntity<Account> createSavingsAccount(@RequestBody SavingsAccountRequest request) {

        // Convert request parameters to appropriate types
        long bankId = request.getBankId();
        long customerId = request.getCustomerId();
        String accountName = request.getAccountName();
        double interestRate = request.getInterestRate();
        double minBalance = request.getMinBalance();
        double initialDeposit = request.getInitialDeposit();

        SavingsAccount savingsAccount = accountService.createSavingsAccount(bankId, customerId, accountName,
                interestRate, minBalance, Optional.ofNullable(initialDeposit));

        return ResponseEntity.status(HttpStatus.CREATED).body(savingsAccount);
    }

}