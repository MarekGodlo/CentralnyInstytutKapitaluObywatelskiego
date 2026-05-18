package org.example.Model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private UUID senderId;
    private UUID receiverId;
    private double amount;


    public Transaction() {}

    public Transaction(UUID id, UUID senderId, UUID receiverId, double amount, LocalDateTime date) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public Transaction(UUID senderId, UUID receiverId, double amount) {
        this.id = UUID.randomUUID();
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public UUID getReceiverId() {
        return receiverId;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(UUID receiverId) {
        this.receiverId = receiverId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}