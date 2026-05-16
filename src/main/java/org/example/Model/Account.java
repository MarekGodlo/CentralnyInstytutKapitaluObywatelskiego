package org.example.Model;

import java.util.UUID;

public class Account {
    private final UUID id;
    private final String username;
    private final String password;
    private double balance;
    private double debt;

    public Account(String userName, String password, double balance, double debt) {
        id = UUID.randomUUID();
        this.username = userName;
        this.password = password;
        this.balance = balance;
        this.debt = debt;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void repayDebt(double amount) {
        debt -= amount;
    }

    public void increaseDebt(double debtValue) {
        debt += debtValue;
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

    public double getDebt() {
        return debt;
    }
}
