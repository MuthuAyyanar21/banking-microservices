package com.banking.transactionservice.service;

import com.banking.transactionservice.client.AccountClient;
import com.banking.transactionservice.dto.TransactionRequest;
import com.banking.transactionservice.dto.TransferRequest;
import com.banking.transactionservice.entity.Transaction;
import com.banking.transactionservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountClient accountClient;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountClient accountClient) {
        this.transactionRepository = transactionRepository;
        this.accountClient = accountClient;
    }

    public Transaction deposit(TransactionRequest request) {
        accountClient.updateBalance(request.getAccountNumber(), request.getAmount());

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(request.getAccountNumber());
        transaction.setType("DEPOSIT");
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription() != null ?
                request.getDescription() : "Deposit");
        return transactionRepository.save(transaction);
    }

    public Transaction withdraw(TransactionRequest request) {
        Map<String, Object> account = accountClient.getAccount(request.getAccountNumber());
        Double balance = (Double) account.get("balance");

        if (balance < request.getAmount().doubleValue()) {
            throw new RuntimeException("Insufficient balance!");
        }

        accountClient.updateBalance(request.getAccountNumber(),
                request.getAmount().negate());

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(request.getAccountNumber());
        transaction.setType("WITHDRAWAL");
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription() != null ?
                request.getDescription() : "Withdrawal");
        return transactionRepository.save(transaction);
    }

    public Transaction transfer(TransferRequest request) {
        Map<String, Object> fromAccount = accountClient.getAccount(
                request.getFromAccountNumber());
        Double balance = (Double) fromAccount.get("balance");

        if (balance < request.getAmount().doubleValue()) {
            throw new RuntimeException("Insufficient balance!");
        }

        accountClient.updateBalance(request.getFromAccountNumber(),
                request.getAmount().negate());
        accountClient.updateBalance(request.getToAccountNumber(),
                request.getAmount());

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(request.getFromAccountNumber());
        transaction.setToAccountNumber(request.getToAccountNumber());
        transaction.setType("TRANSFER");
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription() != null ?
                request.getDescription() : "Transfer");
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getHistory(String accountNumber) {
        return transactionRepository
                .findByAccountNumberOrderByCreatedAtDesc(accountNumber);
    }
}