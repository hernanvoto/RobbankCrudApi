package com.robbank.crudApis.request;

import com.robbank.crudApis.model.CreditAccount.CardLevel;
import com.robbank.crudApis.model.CreditAccount.CardType;

public class CreditAccountRequest {

    private long customerId;
    private String accountName;
    private CardType cardType;
    private CardLevel cardLevel;
    private double creditLimit;

    public CreditAccountRequest() {

    }

    public CreditAccountRequest(
            long customerId, String accountName, CardType cardType, CardLevel cardLevel, double creditLimit
    ) {

        super();
        this.customerId = customerId;
        this.accountName = accountName;
        this.cardType = cardType;
        this.cardLevel = cardLevel;
        this.creditLimit = creditLimit;
    }

    public long getCustomerId() {

        return customerId;
    }

    public void setCustomerId(long customerId) {

        this.customerId = customerId;
    }

    public String getAccountName() {

        return accountName;
    }

    public void setAccountName(String accountName) {

        this.accountName = accountName;
    }

    public CardType getCardType() {

        return cardType;
    }

    public void setCardType(CardType cardType) {

        this.cardType = cardType;
    }

    public CardLevel getCardLevel() {

        return cardLevel;
    }

    public void setCardLevel(CardLevel cardLevel) {

        this.cardLevel = cardLevel;
    }

    public double getCreditLimit() {

        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {

        this.creditLimit = creditLimit;
    }

}
