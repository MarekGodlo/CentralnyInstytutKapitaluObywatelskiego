package org.example;

import java.util.UUID;

public class Account {
    private UUID id;
    private String username;
    private String password;
    private double balance;

    public Account(String userName, String password, double balance) {
        id = UUID.randomUUID();
        this.username = userName;
        this.password = password;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }
}
