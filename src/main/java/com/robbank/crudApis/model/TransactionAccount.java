package com.robbank.crudApis.model;

import jakarta.persistence.Entity;

@Entity
public class TransactionAccount extends Account {

    public TransactionAccount(final String accountName, final double overdraftLimit) {

        super(accountName, overdraftLimit);
    }

    private static final long serialVersionUID = -6709693105591150713L;

    private double overdraftLimit;

    public double getOverdraftLimit() {

        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {

        this.overdraftLimit = overdraftLimit;
    }

    void executeTransaction(Account fromAccount, Account toAccountNo, double amount) {

    }
}