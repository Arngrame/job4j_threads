package ru.job4j.sync.tasks.mmcs;

public class CountV2 {
    private int value;

    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    public int get() {
        synchronized (this) {
            return value;
        }
    }
}