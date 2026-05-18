package org.example.Service;


import org.example.Exception.NotEnoughBalanceException;
import org.example.Model.Account;
import org.example.Model.Transaction;
import org.example.Repository.AccountRepository;
import org.example.Repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    private final TransactionRepository repository;
    private final AccountService accountService;

    public TransactionService(TransactionRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    public void createTransaction(String receiverName, double amount) {
        Account acc = accountService.findAccountByUsername(receiverName);
        Account currentAcc = accountService.getCurrentAccount();

        transfer(currentAcc.getId(), acc.getId(), amount);
    }

    private void transfer(UUID senderId, UUID receiverId, double amount) {
        Account sender = accountService.findAccountById(senderId);
        Account receiver = accountService.findAccountById(receiverId);

        if (amount < 0) throw new IllegalArgumentException("Niepoprawna wartość kwoty");

        if (sender.getBalance() < amount) {
            throw new NotEnoughBalanceException("Brak wystraczającej ilości środków na koncie");
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        repository.save(new Transaction(senderId, receiverId, amount));
        accountService.save(sender);
        accountService.save(receiver);
    }

    public List<Transaction> getHistoryForCurrentAccount() {
        UUID currentId = accountService.getCurrentAccount().getId();
        List<Transaction> history = new ArrayList<>();

        history.addAll(repository.getTransactionBySenderId(currentId));
        history.addAll(repository.getTransactionByReceiverId(currentId));

        return history;
    }
}
