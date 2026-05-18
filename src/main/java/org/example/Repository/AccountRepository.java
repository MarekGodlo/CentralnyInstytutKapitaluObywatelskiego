package org.example.Repository;

import org.example.Model.Account;
import org.example.Utils.Api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountRepository {
    private List<Account> accounts = new ArrayList<>();
    private final Api api;

    public AccountRepository(Api api) {
        this.api = api;
    }

    public void save(Account account) {
        if (!accounts.contains(account)) {
            accounts.add(account);
        }
        api.saveAccount(account);
    }

    public void syncAccounts() {
        accounts = api.getAllAccounts();
    }


    public Optional<Account> findById(UUID id) {
        return accounts.stream()
                .filter(account -> account.getId().equals(id))
                .findFirst();
    }

    public Optional<Account> findByName(String name) {
        return accounts.stream()
                .filter(account -> account.getUsername().equals(name))
                .findFirst();
    }

    public boolean existsByName(String name) {
        return accounts.stream()
                .anyMatch(account -> account.getUsername().equals(name));
    }
}
