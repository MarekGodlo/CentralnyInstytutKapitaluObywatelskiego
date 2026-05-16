package org.example.UI;

import org.example.Exception.AccountNotFoundException;
import org.example.Exception.NotEnoughBalanceException;
import org.example.Service.TransactionService;

import java.util.Scanner;

public class TransactionView {
    Scanner scanner;
    TransactionService transactionService;

    public TransactionView(Scanner scanner, TransactionService transactionService) {
        this.scanner = scanner;
        this.transactionService = transactionService;
    }

    public void handleTransfer() {
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
}
