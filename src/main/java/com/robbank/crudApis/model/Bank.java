package com.robbank.crudApis.model;

import java.util.HashSet;
import java.util.Set;

import com.robbank.crudApis.model.CreditAccount.CardLevel;
import com.robbank.crudApis.model.CreditAccount.CardType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Bank {

    @Id
    @SequenceGenerator(name = "bank_sequence", sequenceName = "bank_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_sequence")
    private Long id;

    private String name;
    private String bsb;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private Set<ContactDetail> contactDetails;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private Set<Account> accounts;

    public enum AccountType {
        SAVINGS, CREDIT
    }

    public Bank(final String name, final String bsb) {

        this.name = name;
        this.bsb = bsb;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getBsb() {

        return bsb;
    }

    public void setBsb(String bsb) {

        this.bsb = bsb;
    }

    public Set<Account> getAccounts() {

        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {

        this.accounts = accounts;
    }

    public Set<Account> getAllAccounts() {

        return accounts;
    }

    public Set<ContactDetail> getContactDetails() {

        return contactDetails;
    }

    public void setContactDetails(Set<ContactDetail> contactDetails) {

        this.contactDetails = contactDetails;
    }

    public Bank addContactDetails(ContactDetail... contactDetails) {

        this.contactDetails = (this.contactDetails == null) ? new HashSet<>() : this.contactDetails;

        for (ContactDetail contactDetail : contactDetails) {

            this.contactDetails.add(contactDetail);
            contactDetail.setBank(this); // Make sure to set the back reference
        }

        return this;
    }

    public Account createSavingsAccount(
            final Customer customer,
            final String accountName,
            final double interestRate,
            final double initialDeposit
    ) {

        Account account = new SavingsAccount(accountName, interestRate, initialDeposit);
        addAccount(customer, account);
        return account;
    }

    public Account createCreditAccount(
            final Customer customer,
            final String accountName,
            final CardType cardType,
            final CardLevel cardLevel
    ) {

        Account account = new CreditAccount(accountName, cardType, cardLevel);
        addAccount(customer, account);
        return account;
    }

    public void addAccount(Customer customer, Account account) {

        if (customer != null && account != null) {

            // Associate the account with the customer
            customer.addAccount(account);
            account.setCustomer(customer);

            // Add the account to the bank's collection of accounts
            accounts = (accounts == null) ? new HashSet<>() : accounts;
            account.setBank(this); /// without this there is no link between the account and the bank
            accounts.add(account);
        } else {

            throw new IllegalArgumentException("Customer and account cannot be null");
        }
    }

    void removeAccount(final long id) {

        accounts.remove(id);

    }
}
