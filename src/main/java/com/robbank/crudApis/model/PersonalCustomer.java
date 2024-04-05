package com.robbank.crudApis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PersonalCustomer extends Customer {
//
//    @Id
//    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
//    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    /**
     * Required for unit testing org.springframework.orm.jpa.JpaSystemException: No
     * default constructor for entity '
     */
    public PersonalCustomer() {

    }

    public PersonalCustomer(String firstName, String lastName) {

        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }
//
//    @Override
//    public long getId() {
//
//        return id;
//    }
//
//    @Override
//    public void setId(long id) {
//
//        this.id = id;
//    }

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
