package ru.job4j.common.tasks.atomicity;

public class Count {
    private int value;

    public void increment() {
        value++;
    }

    public int get() {
        return value;
    }
}
