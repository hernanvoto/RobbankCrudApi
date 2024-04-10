package com.robbank.crudApis.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robbank.crudApis.exceptions.OverdraftException;
import com.robbank.crudApis.model.Account;
import com.robbank.crudApis.model.BankTransferPayee;
import com.robbank.crudApis.model.Transaction;
import com.robbank.crudApis.repositories.AccountRepository;
import com.robbank.crudApis.repositories.PayeeRepository;
import com.robbank.crudApis.repositories.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PayeeRepository payeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Payment/Transfer to 3rd party
     */
    @Transactional(rollbackOn = {
            OverdraftException.class, RuntimeException.class })
    public Transaction transfer(
            final long fromAccountId,
            final long payeeId,
            final String description,
            final double transactionAmount
    ) {

        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(() -> new RuntimeException(
                "Account not found:" + fromAccountId));
        BankTransferPayee bankTransferPayee = (BankTransferPayee) payeeRepository.findById(payeeId).orElseThrow(
                () -> new RuntimeException("Payee not found:" + payeeId));

        /** TO-DO: simulate calling other bank api and depo **/

        fromAccount.withdraw(transactionAmount);

        // Create a new transaction for the withdrawal
        Transaction withdrawalTransaction = new Transaction();
        withdrawalTransaction.setAccount(fromAccount);
        withdrawalTransaction.setDescription(description);
        withdrawalTransaction.setTransactionAmount(-transactionAmount); // Negative amount for withdrawal
        withdrawalTransaction.setTimestamp(LocalDateTime.now());
        withdrawalTransaction.setPayee(bankTransferPayee);
        withdrawalTransaction.setTransactionType(Transaction.TransactionType.TRANSFER);

        fromAccount.addTransaction(withdrawalTransaction);
        System.out.print(fromAccount);
        return transactionRepository.save(withdrawalTransaction);
    }
//    
//    /**
//     * Deposit
//     */
//    public void deposit(Payee payee, TransactionType transactionType, LocalDateTime timestamp, String description,
//        double transactionAmount
//
//) {
//
//    this.transactionType = transactionType;
//    this.timestamp = timestamp;
//    this.description = description;
//    this.transactionAmount = transactionAmount;
//    this.payee = payee;
//    }     
}
