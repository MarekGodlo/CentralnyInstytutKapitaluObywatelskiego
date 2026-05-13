package org.example;

import org.example.Repository.AccountRepository;
import org.example.Service.AccountService;
import org.example.UI.ConsoleUI;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        AccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountService(accountRepository);

        new ConsoleUI(scanner, accountService).start();
    }
}
