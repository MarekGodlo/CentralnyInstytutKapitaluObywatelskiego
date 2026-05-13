package org.example.UI;

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
        boolean isActive = true;

        while (isActive) {
            displayLoginMenu();
        }
    }

    private void displayLoginMenu() {
        System.out.println("Wybierz opcje");
        System.out.println("1. zaloguj się");
        System.out.println("2. zarejestruj się");


        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> handleLogin();
            case 2 -> handleRegistration();
            default -> {
                System.out.println("Nieznana komenda");
            }
        }
    }

    private void displayMainMenu() {

    }

    private void handleLogin() {
        System.out.println("Podaj nazwe uzytkownika");
        String username = scanner.nextLine();

        System.out.println("Podaj haslo uzytkownika");
        String password = scanner.nextLine();

        if (accountService.login(username, password)) {
            System.out.println("Pomyslnie zalogowano");
        } else {
            System.out.println("Niepoprawny login lub haslo");
        }
    }

    private void handleRegistration() {
        System.out.println("Podaj nazwe uzytkownika");
        String username = scanner.nextLine();

        System.out.println("Podaj haslo uzytkownika");
        String password = scanner.nextLine();

        if (accountService.register(username, password)) {
            System.out.println("Pomyslnie zarejestrowano");
        } else {
            System.out.println("Rejestracja sie nie udala");
        }
    }
}
