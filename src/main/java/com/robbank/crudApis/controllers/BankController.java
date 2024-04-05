package com.robbank.crudApis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robbank.crudApis.model.Bank;
import com.robbank.crudApis.services.BankService;

@RestController @RequestMapping(path = "api/v1/robbankapi/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    public BankController() {

    }

    @PostMapping
    public void addNewBank(@RequestBody Bank bank) {

        this.bankService.addNewBank(bank);
    }

}