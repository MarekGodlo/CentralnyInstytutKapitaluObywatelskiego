package org.example.UI;

import org.example.Model.Account;

public class AccountView {
    public void displayAccountStatus(Account account) {
        System.out.println();
        System.out.println("=================== Status Konta ===================");
        System.out.println("Nazwa konta: " + account.getUsername());
        System.out.println("Saldo: " + account.getBalance() + "zł");
        System.out.println("Zadłużenie: " + account.getDebt() + "zł");
    }
}
