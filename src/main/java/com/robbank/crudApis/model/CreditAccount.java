package com.robbank.crudApis.model;

import jakarta.persistence.Entity;

@Entity
public class CreditAccount extends Account {

    private CardType cardType;
    private CardLevel cardLevel;
    private double creditLimit;

    public enum CardType {
        VISA, MASTERCARD, AMERICAN_EXPRESS
    }

    public enum CardLevel {
        STANDARD, GOLD, PLATINUM, BLACK

    }

    public CreditAccount(final String accountName, final CardType cardType, final CardLevel cardLevel) {

        super(accountName, 0.0);
        this.setCardType(cardType);
        this.setCardLevel(cardLevel);
    }

    public double getCreditLimit() {

        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {

        this.creditLimit = creditLimit;
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
}
