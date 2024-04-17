package com.robbank.crudApis;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.robbank.crudApis.model.BankTransferPayee;
import com.robbank.crudApis.model.BpayPayee;
import com.robbank.crudApis.model.BusinessCustomer;
import com.robbank.crudApis.model.ContactDetails;
import com.robbank.crudApis.model.ContactDetails.ContactType;
import com.robbank.crudApis.model.Payee;
import com.robbank.crudApis.model.PersonalCustomer;
import com.robbank.crudApis.model.Transaction;
import com.robbank.crudApis.repositories.AccountRepository;
import com.robbank.crudApis.repositories.CustomerRepository;
import com.robbank.crudApis.repositories.PayeeRepository;
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
            CustomerRepository customerRepository,
            AccountRepository accountRepository,
            AccountService accountService,
            TransactionService transactionService,
            PayeeRepository payeeRepository
    ) {

//       LocalDate.of(1980, Month.SEPTEMBER, 18)
        return args -> {
            /** SAMPLE DATA FOR TEST **/

            /** Create ROBBANK Bank Contact Details **/
//            ContactDetail cd1 = new ContactDetail("robbankapp@gmail.com", "02555-1234",
//                    "1 Fake St Sydney NSW 2000 Australia", ContactType.PRIMARY);

            /** Create Bank Details **/
//            Bank bank = new Bank("ROBBANK", 0123456).addContactDetails(cd1);
//            bankRepository.save(bank);

            /***********************************
             * Create Personal Customer Xavier
             */
            PersonalCustomer xavier = new PersonalCustomer("Xavier", "Xmen");
            ContactDetails cd1 = new ContactDetails("xavier@customer.com", "444-1234", "11 c_fake st Sydney NSW 2000",
                    ContactType.PRIMARY);
            ContactDetails cd2 = new ContactDetails("xavier2@customer.com", "444-2345", "21 c_fake st Sydney NSW 2000",
                    ContactType.SECONDARY);

            /** Add PC Contact Details **/
            xavier.addContactDetails(cd1, cd2);
            customerRepository.save(xavier);

            final double interestRate = 5.0;
            double initialDeposit = 1000.0;

            /** Create Saving Account for Xavier - link bank PC and account **/
            accountService.createSavingsAccount(xavier.getId(), xavier.getFirstName() + " " + xavier.getLastName()
                    + " Account", interestRate, Optional.ofNullable(initialDeposit));

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
            cd1 = new ContactDetails("marla@customer.com", "444-1234", "1 d_fake st Sydney NSW 2000",
                    ContactType.PRIMARY);

            /** Add PC Contact Details **/
            marla.addContactDetails(cd1);
            customerRepository.save(marla);

            /** Create Saving Account for Marla - link bank PC and account **/
            accountService.createSavingsAccount(marla.getId(), marla.getFirstName() + " " + marla.getLastName()
                    + " Account", interestRate, Optional.ofNullable(initialDeposit));

            /** Create Payees for Marla **/
            /** Bank Transfer **/
            String jerryPayeeName = "Jerry my friend";
            String jerryBankName = "Commonwealth Bank";
            int jerryPayeeBSB = 325458;
            String jerryAccountNumber = "6632198";

            Payee jerryPayee = new BankTransferPayee(marla, jerryPayeeName, jerryBankName, jerryPayeeBSB,
                    jerryAccountNumber);
            jerryPayee = payeeRepository.save(jerryPayee);

            /***********************************
             * Create Business Customer Rob Computing PTY LTD*
             ***********************************/
            BusinessCustomer robcomputing = new BusinessCustomer("01234567891", "Rob Computing PTY LTD");
            cd1 = new ContactDetails("robcomputing@business_customer.com", "111-1234", "1 monaco st Sydney NSW 5245",
                    ContactType.PRIMARY);

            /** Add PC Contact Details **/
            robcomputing.addContactDetails(cd1);
            customerRepository.save(robcomputing);

            double minimumBalanceAllowedBuss = 0.0;
            initialDeposit = 10000.0;
            double overdraftLimit = 1000.0;

            /** Create Account for - link bank c and account **/
            accountService.createTransactionAccount(robcomputing.getId(), robcomputing.getName()
                    + " - Business Account", minimumBalanceAllowedBuss, Optional.ofNullable(initialDeposit),
                    overdraftLimit);
        };
    }

}
