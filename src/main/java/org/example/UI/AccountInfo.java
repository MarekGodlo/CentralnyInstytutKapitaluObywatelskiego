package org.example.UI;

import org.example.Model.Account;
import org.example.Model.AccountType;

import java.sql.SQLOutput;


public class AccountInfo {

    public void showAccountType(Account currentAccount) {
        System.out.println("\n=================== KONTO ===================");
        String accountTypeName = currentAccount.getType() == AccountType.STUDENT ? "STUDENT" : currentAccount.getType() == AccountType.BUSINESS ? "BUSINESS" : "NORMAL";
        int tax = currentAccount.getType() == AccountType.STUDENT ? 0 : 5;
        int transactionLimit = currentAccount.getType() == AccountType.STUDENT ? 1000 : currentAccount.getType() == AccountType.BUSINESS ? 5000 : 2500;
        System.out.println("Rodzaj konta: " + accountTypeName);
        System.out.println("Prowizja od przelewów: " + tax + "%");
        System.out.println("Limit transakcji: " + transactionLimit + "zł");
        System.out.println("============================================");
    }
}
