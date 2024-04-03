package com.robbank.crudApis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class TransferTransaction extends Transaction {

    private static final long serialVersionUID = 600914595371213396L;

    @Id
    @SequenceGenerator(name = "transaction_sequence", sequenceName = "transaction_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
    private long id;

    private int fromAccountNo;
    private int toAccountNo;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }
}
