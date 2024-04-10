package com.robbank.crudApis.model;

import jakarta.persistence.Entity;

@Entity
public class SavingsAccount extends Account {

    private double interestRate;
    private double minimumBalance;

    /**
     * Required for unit testing org.springframework.orm.jpa.JpaSystemException: No
     * default constructor for entity '
     */
    public SavingsAccount() {

    }

    public SavingsAccount(
            final String accountName, final double interestRate, final double minimumBalance,
            final double overdraftLimit
    ) {

        super(accountName, overdraftLimit);
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
