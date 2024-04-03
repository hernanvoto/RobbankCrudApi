package com.robbank.crudApis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class BusinessCustomer extends Customer {

    @Id
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    private long id;
    private String abn;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getAbn() {

        return abn;
    }

    public void setAbn(String abn) {

        this.abn = abn;
    }

}
