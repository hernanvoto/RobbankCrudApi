package com.robbank.crudApis;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.robbank.crudApis.model.Bank;
import com.robbank.crudApis.model.ContactDetail;
import com.robbank.crudApis.model.ContactDetail.ContactType;
import com.robbank.crudApis.model.PersonalCustomer;
import com.robbank.crudApis.repositories.AccountRepository;
import com.robbank.crudApis.repositories.BankRepository;
import com.robbank.crudApis.repositories.PersonalCustomerRepository;
import com.robbank.crudApis.services.AccountService;

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
            AccountService accountService
    ) {

//       LocalDate.of(1980, Month.SEPTEMBER, 18)
        return args -> {
            /** SAMPLE DATA FOR TEST **/

            ContactDetail cd1 = new ContactDetail("bank1@bank.com", "555-1234", "1 b_fake st Sydney NSW 2000",
                    ContactType.PRIMARY);
            ContactDetail cd2 = new ContactDetail("bank2Sec@bank.com", "555-2345", "2 b_fake st Sydney NSW 2000",
                    ContactType.SECONDARY);

            Bank b1 = new Bank("Bank1", "000001").addContactDetails(cd1);
            Bank b2 = new Bank("Bank2", "000002").addContactDetails(cd2);
            bankRepository.saveAll(List.of(b1, b2));

            PersonalCustomer pc = new PersonalCustomer("Rob", "Vita");
            cd1 = new ContactDetail("rob@customer.com", "444-1234", "11 c_fake st Sydney NSW 2000",
                    ContactType.PRIMARY);
            cd2 = new ContactDetail("rob2@customer.com", "444-2345", "21 c_fake st Sydney NSW 2000",
                    ContactType.SECONDARY);

            pc.addContactDetails(cd1, cd2);
            personalCustomerRepository.save(pc);

            final double interestRate = 5.0;
            final double minimumBalanceAllowed = 0.0;
            final double initialDeposit = 100.0;

            accountService.createSavingsAccount(b1.getId(), pc.getId(), pc.getFirstName() + " " + pc.getLastName()
                    + " Account", interestRate, minimumBalanceAllowed, Optional.ofNullable(initialDeposit));

        };
    }

}
