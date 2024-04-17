package com.robbank.crudApis.model;

import jakarta.persistence.Entity;

@Entity
public class BpayPayee extends Payee {

    private String billerCode;
    private String ref;
    private String billerName;

    public BpayPayee() {

    }

    public BpayPayee(Customer customer, String billerCode, String ref, String billerName) {

        setCustomer(customer);
        this.billerCode = billerCode;
        this.ref = ref;
        this.billerName = billerName;
//        this.paymentType = PaymentType.BPAY;
    }

    public String getBillerCode() {

        return billerCode;
    }

    public void setBillerCode(String billerCode) {

        this.billerCode = billerCode;
    }

    public String getRef() {

        return ref;
    }

    public void setRef(String ref) {

        this.ref = ref;
    }

    public String getBillerName() {

        return billerName;
    }

    public void setBillerName(String billerName) {

        this.billerName = billerName;
    }

}
