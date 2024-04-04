package com.robbank.crudApis.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

/**
 * Single Table Inheritance (SINGLE_TABLE): Recommended if the number of
 * subclasses is relatively small and the differences between subclasses are
 * minimal. Suitable when querying across all account types is common and
 * performance is a concern. Ensures a simple and efficient database schema with
 * all account data stored in a single table. Table Size and Performance: As all
 * subclasses share the same table, it can lead to a larger table size Data
 * Integrity: Since all subclasses share the same table, there might be columns
 * that are not applicable to certain subclasses
 */
@Entity @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Customer {

    @Id
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    private long id;

//    private Set<Payee> payees;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<ContactDetail> contactDetails;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Account> accounts;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

//    public Set<Payee> getPayees() {
//
//        return payees;
//    }
//
//    public void setPayees(Set<Payee> payees) {
//
//        this.payees = payees;
//    }

    public void addContactDetails(ContactDetail... contactDetails) {

        if (this.contactDetails == null) {

            this.contactDetails = new HashSet<>();
        }

        for (ContactDetail contactDetail : contactDetails) {

            this.contactDetails.add(contactDetail);
            contactDetail.setCustomer(this); // Make sure to set the back reference
        }
    }

    public void addAccount(Account account) {

        if (account != null) {

            if (accounts == null) {

                accounts = new HashSet<>();
            }
            accounts.add(account);
            account.setCustomer(this);
        } else {

            throw new IllegalArgumentException("Account cannot be null");
        }
    }

    public Set<ContactDetail> getContactDetails() {

        return contactDetails;
    }

    public void setContactDetails(Set<ContactDetail> contactDetails) {

        this.contactDetails = contactDetails;
    }

//    public void setAccounts(Set<Account> accounts) {
//
//        this.accounts = accounts;
//    }
//
//    Set<Account> getAccounts() {
//
//        return accounts;
//    }

    void openAccount(Account account) {

    }

    void closeAccount(Account account) {

    }

}
