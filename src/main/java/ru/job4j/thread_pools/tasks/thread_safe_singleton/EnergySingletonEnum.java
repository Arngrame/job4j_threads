package ru.job4j.thread_pools.tasks.thread_safe_singleton;

public enum EnergySingletonEnum {
    INSTANCE;

    public static void main(String[] args) {
        EnergySingletonEnum singleton = EnergySingletonEnum.INSTANCE;
        System.out.println(singleton);
    }
}