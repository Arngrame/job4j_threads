package ru.job4j.thread_pools.tasks.thread_safe_singleton;

public class EnergySingletonFinal {

    private static final EnergySingletonFinal INSTANCE = new EnergySingletonFinal();

    private EnergySingletonFinal() {
    }

    public static EnergySingletonFinal getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        EnergySingletonFinal singleton = EnergySingletonFinal.getInstance();
        System.out.println(singleton);
    }
}