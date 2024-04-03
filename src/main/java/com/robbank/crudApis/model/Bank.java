package com.robbank.crudApis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Bank {

    @Id
    @SequenceGenerator(name = "bank_sequence", sequenceName = "bank_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_sequence")
    private Long id;

    private String name;
    private String bsb;
    private ContactDetail contactDetail;
//    private Set<Account> accounts;

    public enum AccountType {
        SAVINGS, CREDIT
    }

    public Bank(final String name, final String bsb, final ContactDetail contactDetail) {

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
//
//    public Set<Account> getAccounts() {
//
//        return accounts;
//    }
//
//    public void setAccounts(Set<Account> accounts) {
//
//        this.accounts = accounts;
//    }
//
//    Set<Account> getAllAccounts() {
//
//        return accounts;
//    }

    public ContactDetail getContactDetails() {

        return contactDetail;
    }

    public void setContactDetails(ContactDetail contactDetails) {

        this.contactDetail = contactDetails;
    }

    Account createAccount(final String accountName, final AccountType accountType) {

        Account account = null;

//        switch (accountType) {
//
//        case SAVINGS:
//            account = new SavingsAccount(accountName, 5.0, 0.0);
//            break;
//        case CREDIT:
//            account = new CreditAccount(accountName);
//            break;
//        }
//        account.setAccountName(accountName);
//
//        // Add the created account to the accounts collection
//        if (accounts == null) {
//
//            accounts = new HashSet<Account>(); // Ensure accounts collection is initialized
//        }
//        accounts.add(account);

        return account;
    }
//
//    void removeAccount(final long id) {
//
//        accounts.remove(id);
//
//    }
}
