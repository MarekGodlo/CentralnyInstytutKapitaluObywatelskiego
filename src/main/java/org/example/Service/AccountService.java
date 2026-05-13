package org.example.Service;

import org.example.Exception.AuthenticationException;
import org.example.Exception.RegistrationException;
import org.example.Repository.AccountRepository;
import org.example.Exception.AccountNotFoundException;
import org.example.Model.Account;

import java.util.UUID;

public class AccountService {
    private Account currentAccount;
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account findAccountById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id.toString()));
    }

    public void login(String username, String password) {
        Account acc = repository.findByName(username)
                .orElseThrow(() -> new AuthenticationException("Niepoprawna nazwa użytkownika lub hasło"));

        if (!acc.getPassword().equals(password)) throw new AuthenticationException("Niepoprawna nazwa użytkownika lub hasło");

        currentAccount = acc;
    }

    public void register(String username, String password) {
        validateRegistrationData(username, password);

        Account acc = new Account(username, password, 0);
        currentAccount = acc;

        repository.save(acc);
    }

    private void validateRegistrationData(String username, String password) {
        if (username.isBlank()) throw new RegistrationException("Niepoprawna nazwa użytkownika");

        if (password.isBlank() || password.length() <= 6) throw new RegistrationException("Niepoprawne hasło (min. długosc 6 znaków");

        if (repository.existsByName(username)) throw new RegistrationException("Konto już istnieje");
    }
}
