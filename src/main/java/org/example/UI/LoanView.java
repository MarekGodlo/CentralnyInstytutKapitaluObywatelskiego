package org.example.UI;

import org.example.Exception.LoanException;
import org.example.Model.Account;
import org.example.Model.Loan;
import org.example.Service.LoanService;

import java.util.List;
import java.util.Scanner;

public class LoanView {
    private final Scanner scanner;
    private final LoanService loanService;
    private final AccountView accountView;

    public LoanView(Scanner scanner, LoanService loanService, AccountView accountView) {
        this.scanner = scanner;
        this.loanService = loanService;
        this.accountView = accountView;
    }

    public void displayMakingLoanMenu(Account account) {
        System.out.println();
        accountView.displayAccountStatus(account);
        System.out.println();

        System.out.println("Czy chcesz wziąć pożyczkę? Y/N");

        String choice = scanner.nextLine().toLowerCase();

        switch (choice) {
            case "y" -> handleMakingLoad();
            case "n" -> {}
            default -> System.out.println("Niepoprawny format danych");
        }
    }

    public void displayRepayingLoanMenu(Account account) {
        List<Loan> loans = loanService.getActiveLoans();

        if (loans.isEmpty()) {
            System.out.println("Brak aktywnych pożyczek");
            return;
        }

        int counter = 1;

        for (Loan loan : loans) {
            System.out.println();
            System.out.println("Numer: " + counter++);
            displayLoan(loan);
            System.out.println();
        }

        System.out.println("Podaj numer pożyczki");
        if (!scanner.hasNextDouble()) {
            System.out.println("Niepoprawny format danych");
            scanner.nextLine();
            return;
        }
        int index = scanner.nextInt();
        scanner.nextLine();

        handleRepayingLoan(loans.get(index-1), account);
    }

    private void handleMakingLoad() {
        System.out.println();

        System.out.println("Podaj kwote pożyczki");

        if (!scanner.hasNextDouble()) {
            System.out.println("Niepoprawny format danych");
            scanner.nextLine();
            return;
        }
        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {
            loanService.createLoan(amount);
        } catch (LoanException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleRepayingLoan(Loan loan, Account account) {
        System.out.println();

        double repayment = loanService.getRepayment(loan);
        System.out.println("Rata pożyczki wynosi: " + repayment);
        System.out.println("Czy chesz spłacić ratę? Y/N");

        String choice = scanner.nextLine().toLowerCase();


        switch (choice) {
            case "y" -> {
                try {
                    loanService.repayLoan(loan);
                } catch (LoanException e) {
                    System.out.println(e.getMessage());
                }
            }
            case "n" -> {}
            default -> System.out.println("Niepoprawny format danych");
        }

        accountView.displayAccountStatus(account);
    }

    private void displayLoan(Loan loan) {
        System.out.println("Kwota pożyczki: " + loan.getOriginalAmount() + "zł");
        System.out.println("Pozostało: " + loan.getRemainingAmount() + "zł");
        System.out.println("Stopa procentowa: " + (loan.getInterestRate()*100) + "%");
        System.out.println("Data utworzenia: " + loan.getCreateDate());
        System.out.println("Liczba miesięcy do spłaty: " + loan.getRepaymentMonths() );
    }
}
