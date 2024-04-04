package com.robbank.crudApis.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Statement {

    @Id
    @SequenceGenerator(name = "statement_sequence", sequenceName = "statement_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statement_sequence")
    private long id;
//    private T account;
    private LocalDate starDate;
    private LocalDate endDate;
//    private List<Transaction> transactions;
    @ManyToOne
    @JoinColumn
    private Account account;

    public Statement(LocalDate starDate, LocalDate endDate/* , List<Transaction> transactions */) {

        super();
        this.starDate = starDate;
        this.endDate = endDate;
//        this.transactions = transactions;
    }

    public void setAccount(Account account) {

        this.account = account;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public LocalDate getStarDate() {

        return starDate;
    }

    public void setStarDate(LocalDate starDate) {

        this.starDate = starDate;
    }

    public LocalDate getEndDate() {

        return endDate;
    }

    public void setEndDate(LocalDate endDate) {

        this.endDate = endDate;
    }

//    public List<Transaction> getTransactions() {
//
//        return transactions;
//    }
//
//    public void setTransactions(List<Transaction> transactions) {
//
//        this.transactions = transactions;
//    }

}
