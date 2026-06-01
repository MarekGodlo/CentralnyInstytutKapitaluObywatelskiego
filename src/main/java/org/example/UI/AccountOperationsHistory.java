package org.example.UI;

import org.example.Model.Account;
import org.example.Model.AccountType;
import org.example.Model.Transaction;
import org.example.Repository.AccountRepository;

import java.util.List;

public class AccountOperationsHistory {
    public void showOperationsHistory(Account currentAccount, List<Transaction> transactions, AccountRepository accountRepo) {
        System.out.println("\n=================== HISTORIA OPERACJI ===================");

        if (transactions.isEmpty()) {
            System.out.println("Brak transakcji do wyświetlenia.");
        } else {
            for (Transaction t : transactions) {
                boolean isSender = t.getSenderId().equals(currentAccount.getId());
                double tax = (currentAccount.getType() == AccountType.STUDENT) ? 0.00 : 0.05;
                String partyName;
                String type;
                String amountStr;

                if (isSender) {
                    Account receiver = accountRepo.findById(t.getReceiverId()).orElse(null);
                    partyName = (receiver != null) ? receiver.getUsername() : "Nieznany";
                    type = "PRZELEW DO: ";
                    amountStr = "-" + t.getAmount();
                } else {
                    Account sender = accountRepo.findById(t.getSenderId()).orElse(null);
                    partyName = (sender != null) ? sender.getUsername() : "Nieznany";
                    type = "PRZELEW OD: ";
                    amountStr = "+" + t.getAmount();
                }

                String taxInfo = isSender ? " | PROWIZJA: " + (t.getAmount() * tax) + " " : "";
                System.out.println(type + partyName + " | KWOTA: " + amountStr + " PLN" + taxInfo);
            }
        }
        System.out.println("=========================================================");
    }
}