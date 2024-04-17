package com.robbank.crudApis.request;

import java.util.Set;

import com.robbank.crudApis.model.Account;
import com.robbank.crudApis.model.ContactDetails;
import com.robbank.crudApis.model.Payee;

public class BusinessCustomerRequest {

    private long id;
    private Set<ContactDetails> contactDetails;
    private Set<Account> accounts;
    private Set<Payee> payees;

    private String abn;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
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

    public String getAbn() {

        return abn;
    }

    public void setAbn(String abn) {

        this.abn = abn;
    }
}
