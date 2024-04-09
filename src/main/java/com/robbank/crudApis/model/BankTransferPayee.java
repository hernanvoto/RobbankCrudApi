package com.robbank.crudApis.model;

import jakarta.persistence.Entity;

@Entity
public class BankTransferPayee extends Payee {

    private String name;
    private String bankName;
    private int bsb;
    private String accountNumber;

    public BankTransferPayee(Customer customer, String payeeName, String bankName, int bsb, String accountNumber) {

        setCustomer(customer);
        this.name = payeeName;
        this.bankName = bankName;
        this.bsb = bsb;
        this.accountNumber = accountNumber;
//        this.paymentType = paymentType;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getBankName() {

        return bankName;
    }

    public void setBankName(String bankName) {

        this.bankName = bankName;
    }

    public String getAccountNumber() {

        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {

        this.accountNumber = accountNumber;
    }

    public int getBsb() {

        return bsb;
    }

    public void setBsb(int bsb) {

        this.bsb = bsb;
    }

}
