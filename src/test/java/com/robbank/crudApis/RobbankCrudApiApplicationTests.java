package com.robbank.crudApis;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.robbank.crudApis.controllers.AccountController;
import com.robbank.crudApis.controllers.BankController;
import com.robbank.crudApis.model.Account;
import com.robbank.crudApis.model.Bank;
import com.robbank.crudApis.model.ContactDetail;
import com.robbank.crudApis.model.ContactDetail.ContactType;
import com.robbank.crudApis.model.PersonalCustomer;
import com.robbank.crudApis.repositories.AccountRepository;
import com.robbank.crudApis.repositories.BankRepository;
import com.robbank.crudApis.repositories.PersonalCustomerRepository;
import com.robbank.crudApis.request.SavingsAccountRequest;
import com.robbank.crudApis.services.BankService;

import jakarta.transaction.Transactional;

@SpringBootTest
class RobbankCrudApiApplicationTests {

    @Autowired
    private BankController bankController;

    @Autowired
    private AccountController accountController;

    @Autowired
    private BankService bankService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PersonalCustomerRepository personalCustomerRepository;

    @Autowired
    private BankRepository bankRepository;

    @Test
    @Transactional
    void accountFromBank() {

        /**** CREATE Bank ****/
        ContactDetail cd1 = new ContactDetail("bank1@bank.com", "555-1234", "1 b_fake st Sydney NSW 2000",
                ContactType.PRIMARY);
        ContactDetail cd2 = new ContactDetail("bank2Sec@bank.com", "555-2345", "2 b_fake st Sydney NSW 2000",
                ContactType.SECONDARY);

        Bank bank1 = new Bank("Bank1", "000001").addContactDetails(cd1);
        Bank bank2 = new Bank("Bank2", "000002").addContactDetails(cd2);
        // bankController.addNewBank(bank1); bankController.addNewBank(b2);
        bankRepository.saveAll(List.of(bank1, bank2));

        /**** CREATE Personal Customer ****/
        PersonalCustomer pc = new PersonalCustomer("Rob", "Vita");
        cd1 = new ContactDetail("rob@customer.com", "444-1234", "11 c_fake st Sydney NSW 2000", ContactType.PRIMARY);
        cd2 = new ContactDetail("rob2@customer.com", "444-2345", "21 c_fake st Sydney NSW 2000", ContactType.SECONDARY);

        pc.addContactDetails(cd1, cd2);

        personalCustomerRepository.save(pc);

        /**** CREATE Personal Customer Savings Account ****/
        final double interestRate = 5.0;
        final double minimumBalanceAllowed = 0.0;

        SavingsAccountRequest savReq = new SavingsAccountRequest();
        savReq.setBankId(bank1.getId());
        savReq.setCustomerId(pc.getId());
        savReq.setAccountName(pc.getFirstName() + " " + pc.getLastName() + " Savings Account");
        savReq.setMinBalance(minimumBalanceAllowed);
        savReq.setInterestRate(interestRate);
//        savReq.setInitialDeposit(null);

        ResponseEntity<Account> createdAccount = accountController.createSavingsAccount(savReq);

//        Optional<Account> accountFound = accountRepository.findAccountByAccountNo(createdAccount.getAccountNo());
//
//        accountFound.ifPresentOrElse(account -> {
//
//            // Remove the account from the bank
//            bank1.removeAccount(account);
//
//            // Verify that the account is no longer associated with the bank
//            Bank bankFound = bankRepository.findById(bank1.getId()).orElse(null);
//
//            assert bankFound != null;
//            assert bankFound.getAccounts().isEmpty(); // Ensure that the accounts set is empty
//        }, () -> {
//
//            // Account exist
//            assert false;
//        });

    }
}
