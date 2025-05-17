package ru.job4j.nonblockingalgo.tasks.cache;

public class OptimisticException extends RuntimeException {

    public OptimisticException(String message) {
        super(message);
    }
}