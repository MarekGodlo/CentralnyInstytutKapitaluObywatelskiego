package org.example.Service;


import org.example.Exception.NotEnoughBalanceException;
import org.example.Model.Account;
import org.example.Model.Transaction;
import org.example.Repository.TransactionRepository;

import java.util.UUID;

public class TransactionService {
    private final TransactionRepository repository;
    private final AccountService accountService;

    public TransactionService(TransactionRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    // TODO: handle exceptions and connect with UI
    public void transfer(UUID senderId, UUID receiverId, double amount) {
        Account sender = accountService.findAccountById(senderId);
        Account receiver = accountService.findAccountById(receiverId);

        if (amount < 0) throw new IllegalArgumentException("Cannot transfer negative amount");

        if (sender.getBalance() < amount) {
            throw new NotEnoughBalanceException("Not enough balance on account");
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        repository.save(new Transaction(senderId, receiverId, amount));


        // save to db logic
    }
}
