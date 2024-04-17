package com.robbank.crudApis.model;

import jakarta.persistence.Entity;

@Entity
public class PersonalCustomer extends Customer {

//    @Column(nullable = false)
    private String firstName;

    /**
     * if set as nullable, Business Cusomter saving fails
     */
//    @Column(nullable = false)
    private String lastName;

    /**
     * Required for unit testing org.springframework.orm.jpa.JpaSystemException: No
     * default constructor for entity '
     */
    public PersonalCustomer() {

        setCustomerType(CustomerType.PERSONAL);
    }

    public PersonalCustomer(String firstName, String lastName) {

        super();
        setCustomerType(CustomerType.PERSONAL);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

}
