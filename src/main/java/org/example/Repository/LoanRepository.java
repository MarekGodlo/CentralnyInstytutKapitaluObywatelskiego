package org.example.Repository;

import org.example.Model.Account;
import org.example.Model.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanRepository {
    private final List<Loan> loans = new ArrayList<>();

    public void save(Loan loan) {
        if (!loans.contains(loan)) {
            loans.add(loan);
        }
    }
}
