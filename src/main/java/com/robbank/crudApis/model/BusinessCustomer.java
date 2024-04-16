package com.robbank.crudApis.model;

import jakarta.persistence.Entity;

@Entity
public class BusinessCustomer extends Customer {

    private String abn;
    private String name;

    public BusinessCustomer() {

        setCustomerType(CustomerType.BUSINESS);
    }

    public BusinessCustomer(final String abn, final String name) {

        setCustomerType(CustomerType.BUSINESS);
        this.abn = abn;
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAbn() {

        return abn;
    }

    public void setAbn(String abn) {

        this.abn = abn;
    }

}
