package com.robbank.crudApis.request;

public class TransactionAccountRequest {

    private long bankId;
    private long customerId;
    private String accountName;
    private double minBalance;
    private double initialDeposit;
    private double overdraftLimit;

    public TransactionAccountRequest() {

    }

    public TransactionAccountRequest(
            final long customerId, final String accountName, final double minBalance, final double initialDeposit,
            final double overdraftLimit
    ) {

//        this.setBankId(bankId);
        this.setCustomerId(customerId);
        this.accountName = accountName;
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
