package org.example.Service;


import org.example.Exception.NotEnoughBalanceException;
import org.example.Model.Account;
import org.example.Model.AccountType;
import org.example.Model.Transaction;
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
        int transferLimit  = 2500;
        double tax = 0.05;

        if (sender.getType() == AccountType.STUDENT) {
            transferLimit = 1000;
            tax = 0.00;
        } else if (receiver.getType() == AccountType.BUSINESS) {
            transferLimit = 5000;
        }

        double amountWithTax = Math.round((amount + (amount * tax)) * 100.0) / 100.0;


        if (amount > transferLimit) {
            throw new NotEnoughBalanceException("Przekroczono limit kwoty dla jednej transakcji");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Niepoprawna wartość kwoty");
        }

        if (sender.getBalance() < amountWithTax) {
            throw new NotEnoughBalanceException("Brak wystraczającej ilości środków na koncie: " + amountWithTax);
        }

        sender.withdraw(amountWithTax);
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
