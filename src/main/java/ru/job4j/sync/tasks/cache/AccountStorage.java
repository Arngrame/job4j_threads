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
        Account updated = accounts.getOrDefault(account.id(), null);
        if (updated != null) {
            accounts.put(account.id(), new Account(account.id(), account.amount()));
            return true;
        }

        return false;
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        if (accounts.containsKey(id)) {
            return Optional.of(accounts.get(id));
        }

        return Optional.empty();
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Optional<Account> from = getById(fromId);
        Optional<Account> to = getById(toId);

        if (from.isEmpty()) {
            throw new IllegalStateException("Not found source account");
        }

        if (to.isEmpty()) {
            throw new IllegalStateException("Not found destination account");
        }

        if (from.get().amount() < amount) {
            throw new IllegalStateException("Not enough money to proceed the transfer request");
        }

        Account newFrom = new Account(from.get().id(), from.get().amount() - amount);
        Account newTo = new Account(to.get().id(), to.get().amount() + amount);

        return update(newFrom) && update(newTo);
    }
}

