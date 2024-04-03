package com.robbank.crudApis.model;

import jakarta.persistence.Entity;

@Entity
public class TransactionAccount extends Account {

//    @Id
//    @SequenceGenerator(name = "transaction_sequence", sequenceName = "transaction_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
//    private long id;
//
//    public TransactionAccount(final String accountName) {
//
//        super(accountName);
//    }

    /**
     * 
     */
    private static final long serialVersionUID = -6709693105591150713L;

    // private double overdraftLimit;
//
//    public long getId() {
//
//        return id;
//    }
//
//    public void setId(long id) {
//
//        this.id = id;
//    }
//
//    public double getOverdraftLimit() {
//
//        return overdraftLimit;
//    }
//
//    public void setOverdraftLimit(double overdraftLimit) {
//
//        this.overdraftLimit = overdraftLimit;
//    }
//
    void executeTransaction(Account fromAccount, Account toAccountNo, double amount) {

    }
}