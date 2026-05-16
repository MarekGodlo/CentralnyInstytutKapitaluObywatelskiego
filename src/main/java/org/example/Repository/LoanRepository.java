package org.example.Repository;

import org.example.Model.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoanRepository {
    private final List<Loan> loans = new ArrayList<>();

    public void save(Loan loan) {
        if (!loans.contains(loan)) {
            loans.add(loan);
        }
    }

    public List<Loan> findActiveLoansByAccountId(UUID accountId) {
        return loans.stream()
                .filter(loan -> loan.getAccountId().equals(accountId))
                .filter(Loan::isActive)
                .toList();
    }
}
