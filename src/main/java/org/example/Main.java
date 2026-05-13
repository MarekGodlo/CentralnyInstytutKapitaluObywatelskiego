package org.example;

import org.example.Model.Account;
import org.example.Repository.AccountRepository;
import org.example.Repository.TransactionRepository;
import org.example.Service.AccountService;
import org.example.Service.TransactionService;
import org.example.UI.ConsoleUI;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        AccountRepository accountRepository = new AccountRepository();
        TransactionRepository transactionRepository = new TransactionRepository();

        AccountService accountService = new AccountService(accountRepository);
        TransactionService transactionService = new TransactionService(transactionRepository,  accountService);

        new ConsoleUI(scanner, accountService, transactionService).start();
    }
}
