package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    public void save(Account account) {
        accounts.add(account);
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
