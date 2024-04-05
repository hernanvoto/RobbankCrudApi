package com.robbank.crudApis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robbank.crudApis.model.Bank;
import com.robbank.crudApis.repositories.BankRepository;

@Service
/**
 * A service is a class responsible for encapsulating and implementing the
 * business logic of an application. Services perform operations related to
 * specific domains or functionalities, such as data processing, calculations,
 * validations, and interactions with other components. They are managed as
 * Spring beans and leverage dependency injection for loose coupling
 */
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public BankService() {

    }

    public Bank addNewBank(final Bank bank) {

        if (bankRepository.findBankByName(bank.getName()).isPresent()) {

            throw new IllegalStateException("Bank already exists");
        }
        return bankRepository.save(bank);
    }

}
