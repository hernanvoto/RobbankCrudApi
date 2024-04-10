package com.robbank.crudApis;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.robbank.crudApis.model.Bank;
import com.robbank.crudApis.model.BankTransferPayee;
import com.robbank.crudApis.model.BpayPayee;
import com.robbank.crudApis.model.ContactDetail;
import com.robbank.crudApis.model.ContactDetail.ContactType;
import com.robbank.crudApis.model.Payee;
import com.robbank.crudApis.model.PersonalCustomer;
import com.robbank.crudApis.model.Transaction;
import com.robbank.crudApis.repositories.AccountRepository;
import com.robbank.crudApis.repositories.BankRepository;
import com.robbank.crudApis.repositories.PayeeRepository;
import com.robbank.crudApis.repositories.PersonalCustomerRepository;
import com.robbank.crudApis.services.AccountService;
import com.robbank.crudApis.services.TransactionService;

@Configuration
public class RobbankApiConfig {

    /**
     * Developers can implement this interface CommandLineRunner in their Spring
     * Boot applications to execute custom logic when the application starts up.
     * 
     * @param repository
     * @return
     */
    @Bean
    CommandLineRunner commandLineRunner(
            BankRepository bankRepository,
            PersonalCustomerRepository personalCustomerRepository,
            AccountRepository accountRepository,
            AccountService accountService,
            TransactionService transactionService,
            PayeeRepository payeeRepository
    ) {

//       LocalDate.of(1980, Month.SEPTEMBER, 18)
        return args -> {
            /** SAMPLE DATA FOR TEST **/

            /** Create ROBBANK Bank Contact Details **/
            ContactDetail cd1 = new ContactDetail("robbankapp@gmail.com", "02555-1234",
                    "1 Fake St Sydney NSW 2000 Australia", ContactType.PRIMARY);

            /** Create Bank Details **/
            Bank bank = new Bank("ROBBANK", 0123456).addContactDetails(cd1);
            bankRepository.save(bank);

            /***********************************
             * Create Personal Customer Xavier
             */
            PersonalCustomer xavier = new PersonalCustomer("Xavier", "Xmen");
            cd1 = new ContactDetail("xavier@customer.com", "444-1234", "11 c_fake st Sydney NSW 2000",
                    ContactType.PRIMARY);
            ContactDetail cd2 = new ContactDetail("xavier2@customer.com", "444-2345", "21 c_fake st Sydney NSW 2000",
                    ContactType.SECONDARY);

            /** Add PC Contact Details **/
            xavier.addContactDetails(cd1, cd2);
            personalCustomerRepository.save(xavier);

            final double interestRate = 5.0;
            final double minimumBalanceAllowed = 0.0;
            double initialDeposit = 1000.0;
            double overdraftLimit = 0.0;

            /** Create Saving Account for xavier - link bank PC and account **/
            accountService.createSavingsAccount(bank.getId(), xavier.getId(), xavier.getFirstName() + " " + xavier
                    .getLastName() + " Account", interestRate, minimumBalanceAllowed, Optional.ofNullable(
                            initialDeposit), overdraftLimit);

            /** Create Payees for Xavier **/
            /** BPAY **/
            String waterBillerName = "Water Sydney";
            String waterBillerCode = "3213121";
            String waterRef = "3424314245";

            /** Bank Transfer **/
            String mariaPayeeName = "Maria Perl";
            String mariaBankName = "HSBC";
            int mariaPayeeBSB = 123456;
            String mariaAccountNumber = "7890123";

            Payee waterPayee = new BpayPayee(xavier, waterBillerCode, waterRef, waterBillerName);
            waterPayee = payeeRepository.save(waterPayee);
            Payee mariaPayee = new BankTransferPayee(xavier, mariaPayeeName, mariaBankName, mariaPayeeBSB,
                    mariaAccountNumber);
            mariaPayee = payeeRepository.save(mariaPayee);

            /*****************************
             * Transfer Xavier to Maria 100*
             ***************************/
            Transaction trx = transactionService.transfer(xavier.getId(), mariaPayee.getId(),
                    "Transfer Xavier to Maria 100", 100.00);

            /***********************************
             * Create Personal Customer Marla *
             ***********************************/
            PersonalCustomer marla = new PersonalCustomer("Marla", "Seinfeld");
            cd1 = new ContactDetail("marla@customer.com", "444-1234", "1 d_fake st Sydney NSW 2000",
                    ContactType.PRIMARY);

            /** Add PC Contact Details **/
            marla.addContactDetails(cd1);
            personalCustomerRepository.save(marla);

            /** Create Saving Account for Marla - link bank PC and account **/
            accountService.createSavingsAccount(bank.getId(), marla.getId(), marla.getFirstName() + " " + marla
                    .getLastName() + " Account", interestRate, minimumBalanceAllowed, Optional.ofNullable(
                            initialDeposit), overdraftLimit);

            /** Create Payees for Marla **/
            /** Bank Transfer **/
            String jerryPayeeName = "Jerry my friend";
            String jerryBankName = "Commonwealth Bank";
            int jerryPayeeBSB = 325458;
            String jerryAccountNumber = "6632198";

            Payee jerryPayee = new BankTransferPayee(marla, jerryPayeeName, jerryBankName, jerryPayeeBSB,
                    jerryAccountNumber);
            jerryPayee = payeeRepository.save(jerryPayee);

        };
    }

}
