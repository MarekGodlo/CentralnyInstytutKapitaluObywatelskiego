package org.example;


import java.util.UUID;

public class TransactionService {
    private final TransactionRepository repository;
    private final AccountService accountService;

    public TransactionService(TransactionRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    public void transfer(UUID senderId, UUID receiverId, double amount) {

    }
}
