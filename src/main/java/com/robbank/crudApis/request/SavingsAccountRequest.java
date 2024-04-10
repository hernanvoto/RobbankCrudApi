package com.robbank.crudApis.request;

public class SavingsAccountRequest {

    private long bankId;
    private long customerId;
    private String accountName;
    private double interestRate;
    private double minBalance;
    private double initialDeposit;
    private double overdraftLimit;

    public SavingsAccountRequest() {

    }

    public SavingsAccountRequest(
            final long bankId, final long customerId, final String accountName, final double interestRate,
            final double minBalance, final double initialDeposit, final double overdraftLimit
    ) {

        this.setBankId(bankId);
        this.setCustomerId(customerId);
        this.accountName = accountName;
        this.interestRate = interestRate;
        this.minBalance = minBalance;
        this.initialDeposit = initialDeposit;
        this.setOverdraftLimit(overdraftLimit);
    }

    public String getAccountName() {

        return accountName;
    }

    public void setAccountName(String accountName) {

        this.accountName = accountName;
    }

    public double getInterestRate() {

        return interestRate;
    }

    public void setInterestRate(double interestRate) {

        this.interestRate = interestRate;
    }

    public double getMinBalance() {

        return minBalance;
    }

    public void setMinBalance(double minBalance) {

        this.minBalance = minBalance;
    }

    public double getInitialDeposit() {

        return initialDeposit;
    }

    public void setInitialDeposit(double initialDeposit) {

        this.initialDeposit = initialDeposit;
    }

    public long getBankId() {

        return bankId;
    }

    public void setBankId(long bankId) {

        this.bankId = bankId;
    }

    public long getCustomerId() {

        return customerId;
    }

    public void setCustomerId(long customerId) {

        this.customerId = customerId;
    }

    public double getOverdraftLimit() {

        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {

        this.overdraftLimit = overdraftLimit;
    }
}
