package org.example.Model;

import java.time.LocalDate;
import java.util.UUID;

public class Loan {
    private final UUID id;
    private final UUID accountId;
    private final double originalAmount;
    private final double remainingAmount;
    private final double interestRate;
    private final int repaymentMonths;
    private final LocalDate createDate;
    private final boolean isActive;

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
}
