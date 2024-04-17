package com.robbank.crudApis.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.robbank.crudApis.exceptions.EntityNotFoundException;
import com.robbank.crudApis.model.Account;
import com.robbank.crudApis.model.BusinessCustomer;
import com.robbank.crudApis.model.ContactDetails;
import com.robbank.crudApis.model.Customer;
import com.robbank.crudApis.model.PersonalCustomer;
import com.robbank.crudApis.model.SavingsAccount;
import com.robbank.crudApis.request.SavingsAccountRequest;
import com.robbank.crudApis.services.CustomerService;

@RestController @RequestMapping(path = "api/v1/robbankapi/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController() {

    }

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> customers() {

        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable Long customerId) throws EntityNotFoundException {

        return customerService.getCustomerById(customerId);
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<String> removeCustomer(@PathVariable Long customerId) {

        try {

            customerService.removeCustomer(customerId);
            return ResponseEntity.ok("Customer with ID " + customerId + " successfully removed.");
        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with ID " + customerId + " not found.");
        }
    }

    @DeleteMapping("/customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<String> removeAccountFromCustomer(
            @PathVariable Long customerId,
            @PathVariable Long accountId
    ) {

        try {

            customerService.removeAccountFromCustomer(customerId, accountId);
            return ResponseEntity.ok("Account with ID " + accountId + " successfully removed.");
        } catch (EntityNotFoundException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with ID " + customerId + " not found.");
        }
    }

    @PostMapping("/customers/personal")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonalCustomer addPersonalCustomer(@RequestBody PersonalCustomer personalCustomer) {

        return customerService.addPersonalCustomer(personalCustomer);
    }

    @PostMapping("/customers/business")
    @ResponseStatus(HttpStatus.CREATED)
    public BusinessCustomer addBusinessCustomer(@RequestBody BusinessCustomer businessCustomer) {

        return customerService.addBusinessCustomer(businessCustomer);
    }

    /**
     * ResponseEntity<SavingsAccount>, you have the flexibility to customize the
     * response further if needed.
     * 
     * @param customerId
     * @param request
     * @return
     */
    @PostMapping("/customers/{customerId}/accounts/savings")
    public ResponseEntity<SavingsAccount> createSavingsAccount(
            @PathVariable("customerId") long customerId,
            @RequestBody SavingsAccountRequest request
    ) {

        // Convert request parameters to appropriate types
//        long bankId = request.getBankId();
        String accountName = request.getAccountName();
        double interestRate = request.getInterestRate();
        double initialDeposit = request.getInitialDeposit();

        SavingsAccount savingsAccount = customerService.createSavingsAccountForCustomer(customerId, accountName,
                interestRate, Optional.ofNullable(initialDeposit));

        return ResponseEntity.status(HttpStatus.CREATED).body(savingsAccount);
    }

    @PutMapping("/customers/{customerId}/personal")
    @ResponseStatus(HttpStatus.OK)
    public PersonalCustomer updatePersonalCustomer(
            @PathVariable("customerId") long customerId,
            @RequestBody PersonalCustomer personalCustomer
    ) {

        return customerService.updatePersonalCustomerDetails(customerId, personalCustomer);
    }

    @PatchMapping("/customers/{customerId}/personal")
    @ResponseStatus(HttpStatus.OK)
    public PersonalCustomer partialUpdatePersonalCustomer(
            @PathVariable("customerId") long customerId,
            @RequestBody PersonalCustomer personalCustomer
    ) {

        return customerService.partialUpdatePersonalCustomerDetails(customerId, personalCustomer);
    }

    @PatchMapping("/customers/{customerId}/contactDetails")
    public ResponseEntity<Customer> addContactDetailsToCustomer(
            @PathVariable("customerId") long customerId,
            @RequestBody Set<ContactDetails> contactDetails
    ) {

        Customer updatedCustomer = customerService.createOrUpdateCustomerContactDetails(customerId, contactDetails);

        return ResponseEntity.ok(updatedCustomer);
    }

    @GetMapping("/customers/{customerId}/accounts")
    @ResponseStatus(HttpStatus.OK)
    public Set<Account> getCustomerAccounts(@PathVariable Long customerId) {

        Customer customer = customerService.getCustomerById(customerId);
        return customer.getAccounts();
    }

    @GetMapping("/customers/{customerId}/accounts/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Account> getCustomerAccountById(@PathVariable Long customerId, @PathVariable Long accountId) {

        try {

            Account account = customerService.getCustomerAccountById(customerId, accountId);

            if (account != null) {

                return ResponseEntity.ok(account);
            } else {

                return ResponseEntity.notFound().build();
            }
        } catch (EntityNotFoundException e) {

            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method FAILS if I use PUT and @RequestBody SavingsAccount instead of
     * SavingsAccountRequest updatedAccountRequest
     * 
     * @param customerId
     * @param accountId
     * @param updatedAccountRequest
     * @return
     */
    @PatchMapping("/customers/{customerId}/accounts/savings/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SavingsAccount> updateCustomerSavingAccountDetailsById(
            @PathVariable Long customerId,
            @PathVariable Long accountId,
            @RequestBody SavingsAccountRequest updatedAccountRequest
    ) {

        try {

            SavingsAccount account = customerService.updateCustomerSavingAccountDetails(customerId, accountId,
                    updatedAccountRequest.getAccountName(), updatedAccountRequest.getInterestRate());
            return ResponseEntity.ok(account);
        } catch (EntityNotFoundException e) {

            return ResponseEntity.notFound().build();
        }
    }

}