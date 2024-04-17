package com.robbank.crudApis.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity @Inheritance(strategy = InheritanceType.JOINED)
public class Payee {

    @Id
    @SequenceGenerator(name = "payee_sequence", sequenceName = "payee_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payee_sequence")
    private long id;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

//    public enum PaymentType {   BPAY, PAYID, BANK_TRANSFER  }

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
}
