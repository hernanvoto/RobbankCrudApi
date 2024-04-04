package com.robbank.crudApis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity @Table
public class InterestTransaction extends Transaction {

    private static final long serialVersionUID = -448461932904017884L;

    @Id
    @SequenceGenerator(name = "transaction_sequence", sequenceName = "transaction_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
    private long id;

    private int targetAccountNo;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public int getTargetAccountNo() {

        return targetAccountNo;
    }

    public void setTargetAccountNo(int targetAccountNo) {

        this.targetAccountNo = targetAccountNo;
    }
}
