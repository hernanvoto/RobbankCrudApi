package com.robbank.crudApis.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
/**
 * Lesson: For mysterious reasons, implements Serializable is neeed if not:
 * Error creating bean with name 'entityManagerFactory' defined in class path
 * resource
 * [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]:
 * Could not determine recommended JdbcType for Java type
 * 'com.robbank.crudApis.model.Account'
 */
public class Account implements Serializable {

    private static final long serialVersionUID = 8448768907639099873L;

    @Id
    @SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    private long id;

    @SequenceGenerator(name = "accountNo_sequence", sequenceName = "accountNo_sequence", allocationSize = 1)
    private int accountNo;

    private double balance;
    private double pending;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
//    private Set<Statement> statements;

    /**
     * To make it bidirectional and circumvent JPOA/Hibernate limitations. Account
     * doesn't need to know about customer per se
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    private String accountName;
    /**
     * Lesson: without defining a bidirectional relationship (both in Bank and
     * Account) it fails with collection 'com.robbank.crudApis.model.Bank.accounts'
     * is 'mappedBy' a property named 'bank' which does not exist in the target
     * entity 'com.robbank.crudApis.model.Account'
     * 
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private Bank bank;

    public Account(final String accountName) {

        this.accountName = accountName;
        balance = 0;
        pending = 0;
    }

    public Customer getCustomer() {

        return customer;
    }

    public void setCustomer(Customer customer) {

        this.customer = customer;
    }

    public Bank getBank() {

        return bank;
    }

    public void setBank(Bank bank) {

        this.bank = bank;
    }

    public int getAccountNo() {

        return accountNo;
    }

    public void setAccountNo(int accountNo) {

        this.accountNo = accountNo;
    }

    public String getAccountName() {

        return accountName;
    }

    public void setAccountName(String accountName) {

        this.accountName = accountName;
    }

    public double getPending() {

        return pending;
    }

    public void setPending(double pending) {

        this.pending = pending;
    }

    public void setBalance(double balance) {

        this.balance = balance;
    }

    void deposit(double amount) {

        balance += amount;
    }

    void withdraw(double amount) {// throw OverdraftException{

        balance -= amount;
    }

    double getBalance() {

        return balance;
    }

    double getAvailableBalance() {

        return balance - pending;
    }
}
