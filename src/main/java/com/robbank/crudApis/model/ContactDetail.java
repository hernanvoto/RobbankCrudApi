package com.robbank.crudApis.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class ContactDetail implements Serializable {

    private static final long serialVersionUID = 9034367072319942527L;

    @Id
    @SequenceGenerator(name = "contactdetail_sequence", sequenceName = "contactdetail_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contactdetail_sequence")
    private long id;

    private String email;
    private String phoneNo;
    private String address;

    /**
     * To make it bidirectional and circumvent JPOA/Hibernate limitations.
     * ContactDetail doesn't need to know about Customer
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    /**
     * To make it bidirectional and circumvent JPOA/Hibernate limitations.
     * ContactDetail doesn't need to know about bank
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private Bank bank;

    private ContactType type;

    public enum ContactType {
        PRIMARY, SECONDARY
    }

    public ContactDetail(final String email, final String phoneNo, final ContactType contactType) {

        this.email = email;
        this.setPhoneNo(phoneNo);
        this.type = contactType;
    }

    public ContactDetail(
            final String email, final String phoneNo, final String address, final ContactType contactType
    ) {

        this.email = email;
        this.setPhoneNo(phoneNo);
        this.address = address;
        this.type = contactType;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public ContactType getType() {

        return type;
    }

    public void setType(ContactType type) {

        this.type = type;
    }

    public String getPhoneNo() {

        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {

        this.phoneNo = phoneNo;
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

}
