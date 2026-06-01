package org.example;

import org.example.Repository.AccountRepository;
import org.example.Repository.LoanRepository;
import org.example.Repository.TransactionRepository;
import org.example.Service.AccountService;
import org.example.Service.LoanService;
import org.example.Service.TransactionService;
import org.example.UI.*;
import org.example.Utils.Api;
import org.example.Utils.Cryptography;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        Api api = new Api();

        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(16, 32, 1, 60000, 10);
        Cryptography crypto = new Cryptography(argon2PasswordEncoder);

        try {
            AccountRepository accountRepository = new AccountRepository(api);
            accountRepository.syncAccounts();
            TransactionRepository transactionRepository = new TransactionRepository(api);
            transactionRepository.syncTransactions();
            LoanRepository loanRepository = new LoanRepository(api);
            loanRepository.syncLoans();
            AccountService accountService = new AccountService(crypto, accountRepository);
            TransactionService transactionService = new TransactionService(transactionRepository,  accountService);
            LoanService loanService = new LoanService(loanRepository, accountService);

            AccountView accountView = new AccountView();
            TransactionView transactionView = new TransactionView(scanner, transactionService);
            LoanView loanView = new LoanView(scanner, loanService, accountView);
            AccountOperationsHistory view = new AccountOperationsHistory();
            AccountInfo accountInfo = new AccountInfo();

            new ConsoleUI(scanner, accountService, accountView, transactionView, loanView, transactionService,view,accountInfo).start();
        } catch (IOException | InterruptedException e) {
            System.out.println("Nie udalo sie uruchomic aplikacji: " + e.getMessage());
        }



    }
}
