package com.robbank.crudApis.model;

import java.util.Set;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Customer {

//    @Id
//    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
//    private long id;
    private Set<Payee> payees;
    private Set<ContactDetail> contactDetails;
    private Set<Account> accounts;

//    public long getId() {
//
//        return id;
//    }
//
//    public void setId(long id) {
//
//        this.id = id;
//    }

    public Set<Payee> getPayees() {

        return payees;
    }

    public void setPayees(Set<Payee> payees) {

        this.payees = payees;
    }

    public Set<ContactDetail> getContactDetails() {

        return contactDetails;
    }

    public void setContactDetails(Set<ContactDetail> contactDetails) {

        this.contactDetails = contactDetails;
    }

    public void setAccounts(Set<Account> accounts) {

        this.accounts = accounts;
    }

    Set<Account> getAccounts() {

        return accounts;
    }

    void openAccount(Account account) {

    }

    void closeAccount(Account account) {

    }

}
