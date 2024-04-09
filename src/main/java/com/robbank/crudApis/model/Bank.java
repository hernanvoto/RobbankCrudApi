package com.robbank.crudApis.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.robbank.crudApis.model.CreditAccount.CardLevel;
import com.robbank.crudApis.model.CreditAccount.CardType;
import com.robbank.crudApis.services.AccountNumberGeneratorService;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;

@Entity
public class Bank {

    /**
     * This is undersired but I can't figure out a way to do this somewhere else
     * except hardwire the autoincrement in the database in the Account table Where
     * could accountNumberGeneratorService be invoked to generate the account
     * numbeR???
     */
    @Autowired
    @Transient
    AccountNumberGeneratorService accountNumberGeneratorService;

    @Id
    @SequenceGenerator(name = "bank_sequence", sequenceName = "bank_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_sequence")
    private Long id;

    private String name;

    @Column(nullable = false)
    private int bsb;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private Set<ContactDetail> contactDetails;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private Set<Account> accounts;
//
//    public enum AccountType {
//        SAVINGS, CREDIT
//    }

    /**
     * Required for unit testing org.springframework.orm.jpa.JpaSystemException: No
     * default constructor for entity '
     */
    public Bank() {

    }

    public Bank(final String name, final int bsb) {

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

    public int getBsb() {

        return bsb;
    }

    public void setBsb(int bsb) {

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

    public Account createCreditAccount(
            final Customer customer,
            final String accountName,
            final CardType cardType,
            final CardLevel cardLevel
    ) {

        Account account = new CreditAccount(accountName, cardType, cardLevel);
        account.setAccountNo(accountNumberGeneratorService.generateAccountNumber());
        addAccount(customer, account);
        return account;
    }

    /**
     * wondering if it is clear bank adds account and how would this be told to
     * users
     * 
     * @param customer
     * @param account
     */
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

    public void removeAccount(final Account account) {

        accounts.remove(account);

    }
}
