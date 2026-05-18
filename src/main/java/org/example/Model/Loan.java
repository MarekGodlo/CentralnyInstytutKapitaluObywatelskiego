package org.example.Model;

import java.time.LocalDate;
import java.util.UUID;

public class Loan {
    private UUID id;
    private UUID accountId;
    private double originalAmount;
    private double remainingAmount;
    private double interestRate;
    private int repaymentMonths;

    private boolean active;

    public Loan() {}

    public Loan(UUID accountId, double originalAmount, double remainingAmount, double interestRate, int repaymentMonths) {
        this.id = UUID.randomUUID();
        this.accountId = accountId;
        this.originalAmount = originalAmount;
        this.remainingAmount = remainingAmount;
        this.interestRate = interestRate;
        this.repaymentMonths = repaymentMonths;
        this.active = true;
    }

    public void repay(double amount) {
        remainingAmount = Math.round((remainingAmount - amount) * 100.0) / 100.0;
    }

    public void decrementMonths() {
        repaymentMonths--;
    }

    public boolean isRepaid() {
        return remainingAmount <= 0;
    }

    public void close() {
        active = false;
    }

    public UUID getId() {
        return id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getRepaymentMonths() {
        return repaymentMonths;
    }

    public boolean isActive() {
        return active;
    }
}
