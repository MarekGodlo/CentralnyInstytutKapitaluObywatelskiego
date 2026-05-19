package org.example.Repository;

import org.example.Model.Transaction;
import org.example.Utils.Api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionRepository {
    private List<Transaction> transactions = new ArrayList<>();
    private final Api api;

    public TransactionRepository(Api api) {
        this.api = api;
    }

    public void save(Transaction transaction) {
        if (!transactions.contains(transaction)) {
            transactions.add(transaction);
        }
        api.saveTransaction(transaction);
    }

    public void syncTransactions()  throws InterruptedException, IOException {
        transactions = api.getAllTransactions();
    }

    public List<Transaction> getTransactionBySenderId(UUID id) {
        return transactions.stream()
                .filter(transaction -> transaction.getSenderId().equals(id))
                .toList();
    }

    public List<Transaction> getTransactionByReceiverId(UUID id) {
        return transactions.stream()
                .filter(transaction -> transaction.getReceiverId().equals(id))
                .toList();
    }


}
