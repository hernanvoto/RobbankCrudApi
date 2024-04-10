package com.robbank.crudApis.exceptions;

public class OverdraftException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -8389494923021402926L;

    public OverdraftException() {

        super("Overdraft occurred.");
    }

    public OverdraftException(String message) {

        super(message);
    }
}
