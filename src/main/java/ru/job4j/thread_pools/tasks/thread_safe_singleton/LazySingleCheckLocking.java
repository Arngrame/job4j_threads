package ru.job4j.thread_pools.tasks.thread_safe_singleton;

public class LazySingleCheckLocking {

    private static LazySingleCheckLocking instance;

    private LazySingleCheckLocking() {
    }

    public static synchronized LazySingleCheckLocking getInstance() {
        if (instance == null) {
            instance = new LazySingleCheckLocking();
        }
        return instance;
    }

    public static void main(String[] args) {
        LazySingleCheckLocking singleton = LazySingleCheckLocking.getInstance();
        System.out.println(singleton);
    }
}
