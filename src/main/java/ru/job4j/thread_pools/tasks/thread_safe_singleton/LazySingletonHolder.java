package ru.job4j.thread_pools.tasks.thread_safe_singleton;

public class LazySingletonHolder {

    private LazySingletonHolder() {
    }

    public static LazySingletonHolder getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final LazySingletonHolder INSTANCE = new LazySingletonHolder();
    }

    public static void main(String[] args) {
        LazySingletonHolder singleton = LazySingletonHolder.getInstance();
        System.out.println(singleton);
    }
}
