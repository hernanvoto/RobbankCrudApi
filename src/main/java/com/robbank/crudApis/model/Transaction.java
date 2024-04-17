package com.robbank.crudApis.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Transaction {

    private TransactionType transactionType;
    private LocalDateTime timestamp;
    private String description;
    private double transactionAmount;

    @Id
    @SequenceGenerator(name = "transaction_sequence", sequenceName = "transaction_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
    private long id;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "payee_id", referencedColumnName = "id")
    private Payee payee;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER, FEE, INTEREST
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public Payee getPayee() {

        return payee;
    }

    public void setPayee(Payee payee) {

        this.payee = payee;
    }

    public TransactionType getTransactionType() {

        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {

        this.transactionType = transactionType;
    }

    public LocalDateTime getTimestamp() {

        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {

        this.timestamp = timestamp;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public double getTransactionAmount() {

        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {

        this.transactionAmount = transactionAmount;
    }

    public Account getAccount() {

        return account;
    }

    public void setAccount(Account account) {

        this.account = account;
    }

}
