package com.robbank.crudApis.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robbank.crudApis.model.Bank;
import com.robbank.crudApis.services.BankService;

@RestController @RequestMapping(path = "api/v1/robbankapi/bank")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {

        this.bankService = bankService;
    }

//    @GetMapping
//    public Set<Account> getAccounts() {
//
//        return _bankService.getStudents();
//    }
//
    @PostMapping
    public void addNewBank(@RequestBody Bank bank) {

        this.bankService.addNewBank(bank);
    }

//    @DeleteMapping(path = "{studentId}")
//    public void deleteStudent(@PathVariable("studentId") Long id) {
//
//        _studentService.deleteStudent(id);
//    }
//
//    @PutMapping(path = "{studentId}")
//    public void updateStudent(
//            @PathVariable("studentId") Long studentId,
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String email
//    ) {
//
//        _studentService.updateStudent(studentId, name, email);
//    }
}