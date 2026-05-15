package org.example.Service;

import org.example.Exception.LoanException;
import org.example.Model.Account;
import org.example.Model.Loan;
import org.example.Repository.LoanRepository;

public class LoanService {
    private static final int REPAYMENT_MONTHS = 12;
    private static final double MAX_DEBT_FOR_LOAN = 5000;
    private static final double INTEREST_RATE = 0.1;

    private final LoanRepository loanRepository;
    private final AccountService accountService;

    public LoanService(LoanRepository loanRepository, AccountService accountService) {
        this.loanRepository = loanRepository;
        this.accountService = accountService;
    }

    public void createLoan(double amount) {
        Account currentAcc = accountService.getCurrentAccount();

        validateLoanData(currentAcc, amount);

        double debt = calculateDebt(amount, INTEREST_RATE, REPAYMENT_MONTHS);

        currentAcc.increaseDebt(debt);
        currentAcc.deposit(amount);

        Loan loan = new Loan(currentAcc.getId(), amount, amount, INTEREST_RATE, REPAYMENT_MONTHS);
        loanRepository.save(loan);

        // save to db logic
    }

    private void validateLoanData(Account account, double amount) {
        if (amount <= 0) {
            throw new LoanException("Odmowa: Kwota pożyczki musi być większa niż 0 zł");
        }

        if (account.getDebt() > MAX_DEBT_FOR_LOAN) {
            throw new LoanException("Przekroczono limit zadłużenia dla tego konta");
        }
    }

    private double calculateRepayment(double amount, int repaymentMonths) {
        double rawRepayment = amount / repaymentMonths;
        rawRepayment = Math.round(rawRepayment * 100.0) / 100.0;
        return rawRepayment;
    }

    private double calculateDebt(double amount, double interestRate, int months) {
        // 0% Credit
        if (interestRate == 0) {
            return amount;
        }

        double interest = amount * interestRate;

        double result = amount + interest;
        return Math.round(result * 100.0) / 100.0;
    }
}
