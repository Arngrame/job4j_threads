package ru.job4j.thread_pools.tasks.thread_safe_singleton;

public class LazyDoubleCheckLocking {

    private static volatile LazyDoubleCheckLocking instance;

    private LazyDoubleCheckLocking() {
    }

    public static LazyDoubleCheckLocking getInstance() {
        if (instance == null) {
            synchronized (LazyDoubleCheckLocking.class) {
                if (instance == null) {
                    instance = new LazyDoubleCheckLocking();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        LazyDoubleCheckLocking singleton = LazyDoubleCheckLocking.getInstance();
        System.out.println(singleton);
    }
}
