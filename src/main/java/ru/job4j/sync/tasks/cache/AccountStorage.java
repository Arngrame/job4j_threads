package ru.job4j.sync.tasks.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountStorage {
    private final Map<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Account from = getById(fromId)
                .orElseThrow(() -> new IllegalStateException("Not found source account"));
        Account to = getById(toId)
                .orElseThrow(() -> new IllegalStateException("Not found destination account"));

        if (from.amount() < amount) {
            throw new IllegalStateException("Not enough money to proceed the transfer request");
        }

        Account newFrom = new Account(from.id(), from.amount() - amount);
        Account newTo = new Account(to.id(), to.amount() + amount);

        return update(newFrom) && update(newTo);
    }
}

