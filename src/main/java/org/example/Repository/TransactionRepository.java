package org.example.Repository;

import org.example.Model.Transaction;
import org.example.Utils.Api;

import java.util.ArrayList;
import java.util.List;

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

    public void syncTransactions() {
        transactions = api.getAllTransactions();
    }
}
