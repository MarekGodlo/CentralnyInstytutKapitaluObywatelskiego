package org.example.Service;

import org.example.Exception.AuthenticationException;
import org.example.Exception.RegistrationException;
import org.example.Repository.AccountRepository;
import org.example.Exception.AccountNotFoundException;
import org.example.Model.Account;
import org.example.Utils.Cryptography;

import java.util.UUID;

public class AccountService {
    private Account currentAccount;
    private final Cryptography crypto;
    private final AccountRepository repository;

    public AccountService(Cryptography crypto, AccountRepository repository) {
        this.crypto = crypto;
        this.repository = repository;
    }

    public AccountRepository getAccountRepository() {
        return repository;
    }

    public Account findAccountById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Konto nie istnieje"));
    }

    public Account findAccountByUsername(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new AccountNotFoundException("Konto nie istnieje"));
    }

    public void save(Account account) {
        repository.save(account);
    }

    public void login(String username, String password) {
        Account acc = repository.findByName(username)
                .orElseThrow(() -> new AuthenticationException("Niepoprawna nazwa użytkownika lub hasło"));

        if (!crypto.verify(password, acc.getPassword())) {
            throw new AuthenticationException("Niepoprawna nazwa użytkownika lub hasło");
        }

        currentAccount = acc;
    }

    public void register(String username, String password) {
        validateRegistrationData(username, password);
        Account acc = new Account(username, crypto.hash(password), 0,0);
        currentAccount = acc;

        repository.save(acc);
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void logout() {
        currentAccount = null;
    }

    private void validateRegistrationData(String username, String password) {
        if (username.isBlank()) {
            throw new RegistrationException("Niepoprawna nazwa użytkownika");
        }

        if (password.isBlank() || password.length() < 6) {
            throw new RegistrationException("Niepoprawne hasło (min. 6 znaków");
        }

        if (repository.existsByName(username))  {
            throw new RegistrationException("Konto już istnieje");
        }
    }
}
