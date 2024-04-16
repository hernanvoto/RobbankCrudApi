package com.robbank.crudApis.model;

import jakarta.persistence.Entity;

@Entity
public class SavingsAccount extends Account {

    private double savingsInterestRate;

    /**
     * Required for unit testing org.springframework.orm.jpa.JpaSystemException: No
     * default constructor for entity '
     */
    public SavingsAccount() {

    }

    public SavingsAccount(final String accountName, final double savingsInterestRate) {

        super(accountName);
        this.savingsInterestRate = savingsInterestRate;
    }

    public double getSavingsInterestRate() {

        return savingsInterestRate;
    }

    public void setSavingsInterestRate(double savingsInterestRate) {

        this.savingsInterestRate = savingsInterestRate;
    }

}
