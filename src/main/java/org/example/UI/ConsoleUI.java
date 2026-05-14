package org.example.UI;

import org.example.Exception.*;
import org.example.Model.Account;
import org.example.Service.AccountService;
import org.example.Service.LoanService;
import org.example.Service.TransactionService;

import java.lang.classfile.instruction.LoadInstruction;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final LoanService loanService;

    public ConsoleUI(Scanner scanner, AccountService accountService, TransactionService transactionService, LoanService loanService) {
        this.scanner = scanner;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.loanService = loanService;
    }

    public void start() {
        boolean isRunning = true;

        System.out.println("=================== Bank ===================");

        while (isRunning) {
            System.out.println();

            System.out.println("Wybierz opcję");
            System.out.println("1. Zaloguj się");
            System.out.println("2. Zarejestruj się");
            System.out.println("3. Wyjdź");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> handleLogin();
                case "2" -> handleRegistration();
                case "3" -> isRunning = false;
                default -> {
                    System.out.println("Nieznana komenda");
                }
            }
        }
    }

    private void handleLogin() {
        System.out.println();

        System.out.println("Podaj nazwę użytkownika");
        String username = scanner.nextLine();

        System.out.println("Podaj hasło użytkownika");
        String password = scanner.nextLine();

        try {
            accountService.login(username, password);
            System.out.println("Pomyślnie zalogowano");

            displayCustomerMenu();

            accountService.logout();
        } catch (AuthenticationException e){
            System.out.println(e.getMessage());
        }
    }

    private void handleRegistration() {
        System.out.println();

        System.out.println("Podaj nazwę użytkownika");
        String username = scanner.nextLine();

        System.out.println("Podaj hasło użytkownika");
        String password = scanner.nextLine();

        try {
            accountService.register(username, password);
            System.out.println("Pomyślnie zarejestrowano");

            displayCustomerMenu();

            accountService.logout();
        } catch (RegistrationException e){
            System.out.println(e.getMessage());
        }
    }

    private void handleTransfer() {
        System.out.println();

        System.out.println("Podaj nazwe użytkownika");
        String username = scanner.nextLine();

        System.out.println("Podaj wartość kwoty");

        if (!scanner.hasNextDouble()) {
            System.out.println("Niepoprawny format danych");
            scanner.nextLine();
            return;
        }

        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {
            transactionService.createTransaction(username, amount);
            System.out.println("Pomyślnie wysłano przelew");
        } catch (AccountNotFoundException | NotEnoughBalanceException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleLoad() {
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

    private void displayLoanMenu() {
        System.out.println();
        displayAccountStatus(accountService.getCurrentAccount());
        System.out.println();

        System.out.println("Czy chcesz wziąć pożyczkę? Y/N");

        String choice = scanner.nextLine();

        switch (choice) {
            case "Y" -> handleLoad();
            case "N" -> {}
            default -> System.out.println("Niepoprawny format danych");
        }
    }

    private void displayAccountStatus(Account account) {
        System.out.println("=================== Status Konta ===================");
        System.out.println("Nazwa konta: " + account.getUsername());
        System.out.println("Saldo: " + account.getBalance());
        System.out.println("Zadłużenie: " + account.getDebt());
    }

    private void displayCustomerMenu() {
        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println();

            System.out.println("Wybierz opcje");
            System.out.println("1. Zrób przelew");
            System.out.println("2. Weźni pożyczkę");
            System.out.println("3. Zobacz stan konta");
            System.out.println("4. Wyloguj się");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> handleTransfer();
                case "2" -> displayLoanMenu();
                case "3" -> displayAccountStatus(accountService.getCurrentAccount());
                case "4" -> isLoggedIn = false;
                default -> {
                    System.out.println("Nieznana komenda");
                }
            }
        }
    }
}
