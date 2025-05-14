package ru.job4j.concurrent.tasks.thread_create_and_run;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        Thread another = new Thread(runnable);
        Thread second = new Thread(runnable);

        another.start();
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}
