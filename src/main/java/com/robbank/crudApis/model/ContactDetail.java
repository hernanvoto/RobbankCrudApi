package com.robbank.crudApis.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class ContactDetail implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2L;
    @Id
    @SequenceGenerator(name = "contactdetail_sequence", sequenceName = "contactdetail_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contactdetail_sequence")
    private long id;
    private String email;
    private String secondaryEmail;
    private String mainPhoneNo;
    private String address;

    private ContactType type;

    public enum ContactType {
        PRIMARY, SECONDARY
    }

    public ContactDetail(
            final String email, final String secondaryEmail, final String mainPhoneNo, final String address
    ) {

        this.email = email;
        this.mainPhoneNo = mainPhoneNo;
        this.secondaryEmail = secondaryEmail;
        this.address = address;
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

    public String getSecondaryEmail() {

        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {

        this.secondaryEmail = secondaryEmail;
    }

    public String getMainPhoneNo() {

        return mainPhoneNo;
    }

    public void setMainPhoneNo(String mainPhoneNo) {

        this.mainPhoneNo = mainPhoneNo;
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
}
