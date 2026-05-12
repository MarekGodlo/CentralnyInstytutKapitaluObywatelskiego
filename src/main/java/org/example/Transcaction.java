package org.example;

import java.time.LocalDate;
import java.util.UUID;

public record Transcaction(UUID id,
                           UUID receiverId,
                           UUID senderId,
                           double amount,
                           LocalDate date) {
    public Transcaction(UUID receiverId, UUID senderId, double amount) {
        this(UUID.randomUUID(), receiverId, senderId, amount, LocalDate.now());
    }
}