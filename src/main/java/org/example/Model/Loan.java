package org.example.Model;

import java.time.LocalDate;
import java.util.UUID;

public class Loan {
    private final UUID id;
    private final UUID accountId;
    private final double originalAmount;
    private double remainingAmount;
    private final double interestRate;
    private int repaymentMonths;
    private final LocalDate createDate;
    private boolean isActive;

    public Loan(UUID accountId, double originalAmount, double remainingAmount, double interestRate, int repaymentMonths) {
        this.id = UUID.randomUUID();
        this.accountId = accountId;
        this.originalAmount = originalAmount;
        this.remainingAmount = remainingAmount;
        this.interestRate = interestRate;
        this.repaymentMonths = repaymentMonths;
        this.createDate = LocalDate.now();
        this.isActive = true;
    }

    public void repay(double amount) {
        remainingAmount -= amount;
    }

    public void decrementMonths() {
        repaymentMonths--;
    }

    public boolean isRepaid() {
        return remainingAmount <= 0;
    }

    public void close() {
        isActive = false;
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

    public LocalDate getCreateDate() {
        return createDate;
    }


    public boolean isActive() {
        return isActive;
    }
}
