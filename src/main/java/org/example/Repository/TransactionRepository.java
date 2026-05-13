package org.example.Repository;

import org.example.Model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    public void save(Transaction transcaction) {
        // FIXME: It's temporary solution too
        if (!transactions.contains(transcaction)) {
            transactions.add(transcaction);
        }
    }
}
