package com.robbank.crudApis.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Account implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id

//    @SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    private long id;
//    @SequenceGenerator(name = "accountNo_sequence", sequenceName = "accountNo_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountNo_sequence")
//    private int accountNo;
//
//    private String accountName;
//    private double balance;
//    private double pending;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
//    private Set<Statement> statements;

//    public Account(final String accountName) {
//
//        this.accountName = accountName;
//        balance = 0;
//        pending = 0;
////        statements = new HashSet<Statement>();
//    }

//    public int getAccountNo() {
//
//        return accountNo;
//    }
//
//    public void setAccountNo(int accountNo) {
//
//        this.accountNo = accountNo;
//    }
//
//    public String getAccountName() {
//
//        return accountName;
//    }
//
//    public void setAccountName(String accountName) {
//
//        this.accountName = accountName;
//    }
//
//    public double getPending() {
//
//        return pending;
//    }
//
//    public void setPending(double pending) {
//
//        this.pending = pending;
//    }

//    public Set<Statement> getStatements() {
//
//        return statements;
//    }
//
//    public void setStatements(Set<Statement> statements) {
//
//        this.statements = statements;
//    }
//
//    public void setBalance(double balance) {
//
//        this.balance = balance;
//    }
//
//    void deposit(double amount) {
//
//        balance += amount;
//    }
//
//    void withdraw(double amount) {// throw OverdraftException{
//
//        balance -= amount;
//    }
//
//    double getBalance() {
//
//        return balance;
//    }
//
//    double getAvailableBalance() {
//
//        return balance - pending;
//    }
}
