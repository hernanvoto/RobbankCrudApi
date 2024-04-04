package com.robbank.crudApis.model;

import jakarta.persistence.Entity;

@Entity
public class SavingsAccount extends Account {

    private static final long serialVersionUID = -6957210575503721514L;

    private double interestRate;
    private double minimumBalance;

    public SavingsAccount(final String accountName, final double interestRate, final double minimumBalance) {

        super(accountName);
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
    }

    public double getInterestRate() {

        return interestRate;
    }

    public void setInterestRate(double interestRate) {

        this.interestRate = interestRate;
    }

    public double getMinimumBalance() {

        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {

        this.minimumBalance = minimumBalance;
    }

}
