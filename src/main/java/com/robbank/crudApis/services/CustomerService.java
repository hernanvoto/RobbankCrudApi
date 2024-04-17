package com.robbank.crudApis.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robbank.crudApis.exceptions.EntityNotFoundException;
import com.robbank.crudApis.model.Account;
import com.robbank.crudApis.model.BusinessCustomer;
import com.robbank.crudApis.model.ContactDetails;
import com.robbank.crudApis.model.Customer;
import com.robbank.crudApis.model.PersonalCustomer;
import com.robbank.crudApis.model.SavingsAccount;
import com.robbank.crudApis.repositories.AccountRepository;
import com.robbank.crudApis.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountNumberGeneratorService accountNumberGeneratorService;

    @Autowired
    private AccountRepository accountRepository;

    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }

    public Customer getCustomerById(final Long customerId) throws EntityNotFoundException {

        return customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException(Customer.class,
                "id", customerId.toString()));
    }

    public PersonalCustomer getPersonalCustomerById(final Long customerId) throws EntityNotFoundException {

        return (PersonalCustomer) customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException(
                Customer.class, "id", customerId.toString()));
    }

    public PersonalCustomer addPersonalCustomer(PersonalCustomer personalCustomer) {

        return customerRepository.save(personalCustomer);
    }

    public BusinessCustomer addBusinessCustomer(BusinessCustomer businessCustomer) {

        return customerRepository.save(businessCustomer);
    }

    public void removeCustomer(final Long customerId) throws EntityNotFoundException {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException(
                Customer.class, "id", customerId.toString()));
        customerRepository.delete(customer);
    }

    public void removeAccountFromCustomer(final Long customerId, final Long accountId) throws EntityNotFoundException {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException(
                Customer.class, "id", customerId.toString()));

        // Find the account to remove
        Optional<Account> accountToRemove = customer.getAccounts().stream().filter(account -> account.getId().equals(
                accountId)).findFirst();

        if (accountToRemove.isPresent()) {

            // Remove the account from the customer's account list
            customer.getAccounts().remove(accountToRemove.get());

            // Save the updated customer back to the database
            customerRepository.save(customer);
        } else {

            throw new EntityNotFoundException(Account.class, "id", accountId.toString());
        }
    }

    public PersonalCustomer updatePersonalCustomerDetails(
            final long customerId,
            PersonalCustomer updatedPersonalCustomer
    ) {

        PersonalCustomer existingCustomer = getPersonalCustomerById(customerId);

        existingCustomer.setFirstName(updatedPersonalCustomer.getFirstName());
        existingCustomer.setLastName(updatedPersonalCustomer.getLastName());

        // Save the updated entity to the database
        return customerRepository.save(existingCustomer);
    }

    public PersonalCustomer partialUpdatePersonalCustomerDetails(
            final long customerId,
            PersonalCustomer updatedPersonalCustomer
    ) {

        PersonalCustomer existingCustomer = getPersonalCustomerById(customerId);

        if (updatedPersonalCustomer.getFirstName() != null) {

            existingCustomer.setFirstName(updatedPersonalCustomer.getFirstName());
        }

        if (updatedPersonalCustomer.getLastName() != null) {

            existingCustomer.setLastName(updatedPersonalCustomer.getLastName());
        }

        // Save the updated entity to the database
        return customerRepository.save(existingCustomer);
    }

    public Customer createOrUpdateCustomerContactDetails(final long customerId, Set<ContactDetails> contactDetails) {

        Customer existingCustomer = getCustomerById(customerId);

        // Set the customer for each contact detail
        // Without this, contact details are created but as orphans
        contactDetails.forEach(contactDetail -> contactDetail.setCustomer(existingCustomer));

        existingCustomer.setContactDetails(contactDetails);
        // Save the updated entity to the database
        return customerRepository.save(existingCustomer);
    }

    public SavingsAccount createSavingsAccountForCustomer(
            long customerId,
            String accountName,
            double interestRate,
            Optional<Double> initialDeposit
    ) throws IllegalArgumentException {

        // Retrieve bank and customer entities from repositories
        // Bank bank = bankRepository.findById(bankId).orElseThrow(() -> new
        // IllegalArgumentException("Bank not found"));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException(
                "Customer not found"));

        Account account = new SavingsAccount(accountName, interestRate);
        // account.setBank(bank);
        account.setCustomer(customer);

        initialDeposit.ifPresent(account::deposit);
        // Generate account number
        account.setAccountNo(accountNumberGeneratorService.generateAccountNumber());

        return (SavingsAccount) accountRepository.save(account);
    }

    public Account getCustomerAccountById(final Long customerId, final Long accountId) throws EntityNotFoundException {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException(
                Customer.class, "id", customerId.toString()));

        Optional<Account> optionalAccount = customer.getAccounts().stream().filter(account -> account.getId().equals(
                accountId)).findFirst();

        if (optionalAccount.isPresent()) {

            return optionalAccount.get();
        } else {

            throw new EntityNotFoundException(Account.class, "id", accountId.toString());
        }
    }

    public SavingsAccount getCustomerSavingAccountById(final Long customerId, final Long accountId) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException(
                Customer.class, "id", customerId.toString()));

        Optional<SavingsAccount> optionalAccount = customer.getAccounts().stream().filter(account -> account.getId()
                .equals(accountId)).map(account -> (SavingsAccount) account) // Cast each account to SavingsAccount
                .findFirst();

        return optionalAccount.orElseThrow(() -> new EntityNotFoundException(SavingsAccount.class, "id", accountId
                .toString()));
    }

    public SavingsAccount updateCustomerSavingAccountDetails(
            final Long customerId,
            final Long accountId,
            final String accountName,
            final double interestRate
    ) {

        Customer customer = getCustomerById(customerId);
        SavingsAccount account = (SavingsAccount) getCustomerAccountById(customerId, accountId);

        account.setCustomer(customer);
        account.setAccountName(accountName);
        account.setSavingsInterestRate(interestRate);

        customerRepository.save(customer);

        return account;
    }
}