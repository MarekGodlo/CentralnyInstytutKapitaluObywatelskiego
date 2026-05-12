package org.example;

import java.util.UUID;

public class AccountService {
    private Account currentAccount;
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public boolean login(String username, String password) {
        return repository.findByName(username)
                .filter(acc -> acc.getPassword().equals(password))
                .map(acc -> {
                    currentAccount = acc;
                    return true;
                }).orElse(false);
    }

    public boolean register(String username, String password) {
        if (!canCreateAccount(username, password)) return false;

        Account acc = new Account(username, password, 0);
        currentAccount = acc;

        repository.save(acc);
        return true;
    }


    public Account findAccountById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id.toString()));
    }

    private boolean canCreateAccount(String username, String password) {
        if (username.isBlank()) return false;

        if (password.isBlank() || password.length() <= 6) return false;

        return !repository.existsByName(username);
    }
}
