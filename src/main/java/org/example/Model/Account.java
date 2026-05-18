package org.example.Model;

import java.util.UUID;

public class Account {
    private  UUID id;
    private  String username;
    private  String password;
    private double balance;
    private double debt;

    public Account(String userName, String password, double balance, double debt) {
        id = UUID.randomUUID();
        this.username = userName;
        this.password = password;
        this.balance = balance;
        this.debt = debt;
    }

    public Account() {}

    public void deposit(double amount) {
        balance = Math.round((balance + amount) * 100.0) / 100.0;
    }

    public void withdraw(double amount) {
        balance = Math.round((balance - amount) * 100.0) / 100.0;
    }

    public void repayDebt(double amount) {
        debt = Math.round((debt - amount) * 100.0) / 100.0;
    }

    public void increaseDebt(double debtValue) {
        debt = Math.round((debt + debtValue) * 100.0) / 100.0;
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
