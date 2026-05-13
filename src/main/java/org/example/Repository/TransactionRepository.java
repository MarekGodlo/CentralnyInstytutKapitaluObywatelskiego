package org.example.Repository;

import org.example.Model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private final List<Transaction> transcactions = new ArrayList<>();

    public void save(Transaction transcaction) {
        // FIXME: It's temporary solution too
        if (!transcactions.contains(transcaction)) {
            transcactions.add(transcaction);
        }
    }
}
