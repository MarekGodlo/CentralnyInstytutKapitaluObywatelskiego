package org.example;

import org.example.Repository.AccountRepository;
import org.example.Repository.LoanRepository;
import org.example.Repository.TransactionRepository;
import org.example.Service.AccountService;
import org.example.Service.LoanService;
import org.example.Service.TransactionService;
import org.example.UI.AccountView;
import org.example.UI.ConsoleUI;
import org.example.UI.LoanView;
import org.example.UI.TransactionView;
import org.example.Utils.Cryptography;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(16, 32, 1, 60000, 10);
        Cryptography crypto = new Cryptography(argon2PasswordEncoder);

        AccountRepository accountRepository = new AccountRepository();
        TransactionRepository transactionRepository = new TransactionRepository();
        LoanRepository loanRepository = new LoanRepository();

        AccountService accountService = new AccountService(crypto, accountRepository);
        TransactionService transactionService = new TransactionService(transactionRepository,  accountService);
        LoanService loanService = new LoanService(loanRepository, accountService);

        AccountView accountView = new AccountView();
        TransactionView transactionView = new TransactionView(scanner, transactionService);
        LoanView loanView = new LoanView(scanner, loanService, accountView);

        new ConsoleUI(scanner, accountService, accountView, transactionView, loanView).start();
    }
}
