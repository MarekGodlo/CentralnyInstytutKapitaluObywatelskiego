package org.example.UI;

import org.example.Exception.*;
import org.example.Service.AccountService;
import org.example.Service.TransactionService;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;

    private final AccountService accountService;

    private final TransactionService transactionService;

    private final AccountView accountView;
    private final TransactionView transactionView;
    private final LoanView  loanView;
    private final AccountOperationsHistory view;

    public ConsoleUI(Scanner scanner, AccountService accountService, AccountView accountView, TransactionView transactionView, LoanView loanView,TransactionService transactionService,  AccountOperationsHistory view) {
        this.scanner = scanner;
        this.view = view;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.transactionView = transactionView;
        this.accountView = accountView;
        this.loanView = loanView;
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



    private void displayCustomerMenu() {
        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println();

            System.out.println("Wybierz opcje");
            System.out.println("1. Zrób przelew");
            System.out.println("2. Weź pożyczkę");
            System.out.println("3. Spłać pożyczkę");
            System.out.println("4. Zobacz stan konta");
            System.out.println("5. Zobacz historie operacji");
            System.out.println("6. Wyloguj się");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> transactionView.handleTransfer();
                case "2" -> loanView.displayMakingLoanMenu(accountService.getCurrentAccount());
                case "3" -> loanView.displayRepayingLoanMenu(accountService.getCurrentAccount());
                case "4" -> accountView.displayAccountStatus(accountService.getCurrentAccount());
                case "5" -> view.showOperationsHistory(accountService.getCurrentAccount(), transactionService.getHistoryForCurrentAccount(), accountService.getAccountRepository());
                case "6" -> isLoggedIn = false;
                default -> {
                    System.out.println("Nieznana komenda");
                }
            }
        }
    }
}
