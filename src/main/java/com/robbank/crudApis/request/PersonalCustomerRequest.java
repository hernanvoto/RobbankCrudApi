package com.robbank.crudApis.request;

import java.util.Set;

import com.robbank.crudApis.model.Account;
import com.robbank.crudApis.model.ContactDetails;
import com.robbank.crudApis.model.Payee;

public class PersonalCustomerRequest {

    private long id;
    private String firstName;
    private String lastName;
    private Set<ContactDetails> contactDetails;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public Set<ContactDetails> getContactDetails() {

        return contactDetails;
    }

    public void setContactDetails(Set<ContactDetails> contactDetails) {

        this.contactDetails = contactDetails;
    }

    public Set<Account> getAccounts() {

        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {

        this.accounts = accounts;
    }

    public Set<Payee> getPayees() {

        return payees;
    }

    public void setPayees(Set<Payee> payees) {

        this.payees = payees;
    }

    private Set<Account> accounts;
    private Set<Payee> payees;

}
