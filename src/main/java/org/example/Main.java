package org.example;

import org.example.Repository.AccountRepository;
import org.example.Repository.LoanRepository;
import org.example.Repository.TransactionRepository;
import org.example.Service.AccountService;
import org.example.Service.LoanService;
import org.example.Service.TransactionService;
import org.example.UI.ConsoleUI;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        AccountRepository accountRepository = new AccountRepository();
        TransactionRepository transactionRepository = new TransactionRepository();
        LoanRepository loanRepository = new LoanRepository();

        AccountService accountService = new AccountService(accountRepository);
        TransactionService transactionService = new TransactionService(transactionRepository,  accountService);
        LoanService loanService = new LoanService(loanRepository, accountService);

        new ConsoleUI(scanner, accountService, transactionService, loanService).start();
    }
}
