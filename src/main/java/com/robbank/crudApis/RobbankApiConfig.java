package com.robbank.crudApis;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.robbank.crudApis.model.Bank;
import com.robbank.crudApis.model.ContactDetail;
import com.robbank.crudApis.repositories.BankRepository;

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
    CommandLineRunner commandLineRunner(BankRepository bankRepository) {

        return args -> {

            ContactDetail cd1 = new ContactDetail("bank1@bank.com", "bank1Sec@bank.com", "555-1234",
                    "1 fake st Sydney NSW 2000");
            ContactDetail cd2 = new ContactDetail("bank2@bank.com", "bank2Sec@bank.com", "555-2345",
                    "2 fake st Sydney NSW 2000");

            Bank b1 = new Bank("Bank1", "000001", cd1);
            Bank b2 = new Bank("Bank2", "000002", cd2);

//       LocalDate.of(1980, Month.SEPTEMBER, 18)

            bankRepository.saveAll(List.of(b1, b2));
        };
    }

}
