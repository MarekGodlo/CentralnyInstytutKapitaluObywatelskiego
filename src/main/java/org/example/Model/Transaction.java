package org.example.Model;

import java.time.LocalDate;
import java.util.UUID;

public record Transaction(UUID id,
                          UUID senderId,
                          UUID receiverId,
                          double amount,
                          LocalDate date) {
    public Transaction(UUID receiverId, UUID senderId, double amount) {
        this(UUID.randomUUID(), receiverId, senderId, amount, LocalDate.now());
    }
}