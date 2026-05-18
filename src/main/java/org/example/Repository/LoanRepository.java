package org.example.Repository;

import org.example.Model.Loan;
import org.example.Utils.Api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoanRepository {
    private List<Loan> loans = new ArrayList<>();
    private final Api api;

    public LoanRepository(Api api) {
        this.api = api;
    }

    public void save(Loan loan) {
        if (!loans.contains(loan)) {
            loans.add(loan);
        }
        api.saveLoan(loan);
    }

    public void syncLoans() {
        loans = api.getAllLoans();
    }

    public List<Loan> findActiveLoansByAccountId(UUID accountId) {
        return loans.stream()
                .filter(loan -> loan.getAccountId().equals(accountId))
                .filter(Loan::isActive)
                .toList();
    }
}
