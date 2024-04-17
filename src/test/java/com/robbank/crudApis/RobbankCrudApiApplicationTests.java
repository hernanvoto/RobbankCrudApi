package com.robbank.crudApis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.robbank.crudApis.controllers.AccountController;
import com.robbank.crudApis.model.ContactDetails;
import com.robbank.crudApis.model.ContactDetails.ContactType;
import com.robbank.crudApis.model.PersonalCustomer;
import com.robbank.crudApis.model.SavingsAccount;
import com.robbank.crudApis.repositories.PersonalCustomerRepository;
import com.robbank.crudApis.request.SavingsAccountRequest;

import jakarta.transaction.Transactional;

@SpringBootTest
class RobbankCrudApiApplicationTests {

    @Autowired
    private AccountController accountController;

    @Autowired
    private PersonalCustomerRepository personalCustomerRepository;

    @Test
    @Transactional
    void accountFromBank() {

        /**** CREATE Bank ****/
        ContactDetails cd1 = new ContactDetails("bank1@bank.com", "555-1234", "1 b_fake st Sydney NSW 2000",
                ContactType.PRIMARY);
        ContactDetails cd2 = new ContactDetails("bank2Sec@bank.com", "555-2345", "2 b_fake st Sydney NSW 2000",
                ContactType.SECONDARY);

//        Bank bank1 = new Bank("Bank1", 328596).addContactDetails(cd1);
//        Bank bank2 = new Bank("Bank2", 864565).addContactDetails(cd2);
//        // bankController.addNewBank(bank1); bankController.addNewBank(b2);
//        bankRepository.saveAll(List.of(bank1, bank2));

        /**** CREATE Personal Customer ****/
        PersonalCustomer pc = new PersonalCustomer("Rob", "Vita");
        cd1 = new ContactDetails("rob@customer.com", "444-1234", "11 c_fake st Sydney NSW 2000", ContactType.PRIMARY);
        cd2 = new ContactDetails("rob2@customer.com", "444-2345", "21 c_fake st Sydney NSW 2000",
                ContactType.SECONDARY);

        pc.addContactDetails(cd1, cd2);

        personalCustomerRepository.save(pc);

        /**** CREATE Personal Customer Savings Account ****/
        final double interestRate = 5.0;
        final double minimumBalanceAllowed = 0.0;

        SavingsAccountRequest savReq = new SavingsAccountRequest();
//        savReq.setBankId(bank1.getId());
        savReq.setCustomerId(pc.getId());
        savReq.setAccountName(pc.getFirstName() + " " + pc.getLastName() + " Savings Account");
        savReq.setInterestRate(interestRate);
//        savReq.setInitialDeposit(null);

        ResponseEntity<SavingsAccount> createdAccount = accountController.createSavingsAccount(savReq);

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
