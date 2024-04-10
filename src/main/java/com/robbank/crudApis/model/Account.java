package com.robbank.crudApis.model;

import java.util.HashSet;
import java.util.Set;

import com.robbank.crudApis.exceptions.OverdraftException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public abstract class Account {

    @Id
    @SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    private long id;

    @Column(nullable = false, unique = true)
    private String accountNo;

    @Column(nullable = false)
    private String accountName;

    private double balance;
    private double pending;
    private double overdraftLimit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Transaction> transactions;

    /**
     * Lesson: without defining a bidirectional relationship (both in Bank and
     * Account) it fails with collection 'com.robbank.crudApis.model.Bank.accounts'
     * is 'mappedBy' a property named 'bank' which does not exist in the target
     * entity 'com.robbank.crudApis.model.Account'
     * 
     * Cascade issue, if ALL saving Account will try to persist Bank. So you need a
     * complete Bank object linked to Account
     */
    @ManyToOne()
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private Bank bank;

    /**
     * To make it bidirectional and circumvent JPOA/Hibernate limitations. Account
     * doesn't need to know about customer per se
     */
    @ManyToOne()
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Statement> statements;

    /**
     * Required for unit testing org.springframework.orm.jpa.JpaSystemException: No
     * default constructor for entity '
     */
    public Account() {

    }

    public Account(final String accountName, final double overdraftLimit) {

        this.accountName = accountName;
        balance = 0;
        pending = 0;
        this.overdraftLimit = overdraftLimit;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
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

    public String getAccountNo() {

        return accountNo;
    }

    public void setAccountNo(String accountNo) {

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

    public void deposit(double amount) {

        balance += amount;
    }

    public double withdraw(double amount) throws OverdraftException {

        if (amount > balance + overdraftLimit) {

            throw new OverdraftException("Overdraft limit exceeded.");
        }
        balance -= amount;

        return balance;
    }

    public double getBalance() {

        return balance;
    }

    public double getAvailableBalance() {

        return balance - pending;
    }

    public Set<Transaction> getTransaction() {

        return transactions;
    }

    public void setTransaction(Set<Transaction> transactions) {

        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {

        if (this.transactions == null) {

            this.transactions = new HashSet<>();
        }
        this.transactions.add(transaction);
    }

    public Set<Statement> getStatements() {

        return statements;
    }

    public void setStatements(Set<Statement> statements) {

        this.statements = statements;
    }

    public void addStatement(Statement statement) {

        if (this.statements == null) {

            this.statements = new HashSet<>();
        }
        this.statements.add(statement);
    }

    @Override
    public String toString() {

        return "Account{" + "id=" + id + ", accountNo='" + accountNo + '\'' + ", accountName='" + accountName + '\''
                + ", balance=" + balance + ", pending=" + pending + ", overdraftLimit=" + overdraftLimit
                + ", transactions=" + transactions + ", bank=" + bank + ", customer=" + customer + ", statements="
                + statements + '}';
    }

}
