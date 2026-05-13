package org.example.UI;

import org.example.Exception.AuthenticationException;
import org.example.Exception.RegistrationException;
import org.example.Service.AccountService;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;
    private final AccountService accountService;

    public ConsoleUI(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
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

            // logout
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

            // logout
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
            System.out.println("2. Wyjdź");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> System.out.println("tranfer");
                case "2" -> isLoggedIn = false;
                default -> {
                    System.out.println("Nieznana komenda");
                }
            }
        }
    }
}
