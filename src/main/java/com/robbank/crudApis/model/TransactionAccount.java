package com.robbank.crudApis.model;

import jakarta.persistence.Entity;

@Entity
public class TransactionAccount extends Account {

    double minimumBalance;
    double overdraftLimit;

    public TransactionAccount() {

    }

    public TransactionAccount(final String accountName, final double minimumBalance, final double overdraftLimit) {

        super(accountName);

        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {

        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {

        this.overdraftLimit = overdraftLimit;
    }

    public double getMinimumBalance() {

        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {

        this.minimumBalance = minimumBalance;
    }

}