package ru.job4j.concurrent.tasks.thread_local;

public class FirstThread extends Thread {
    @Override
    public void run() {
        System.out.println(ThreadLocalDemo.threadLocal.get());
        ThreadLocalDemo.threadLocal.set("Это поток 1.");
        System.out.println(ThreadLocalDemo.threadLocal.get());
    }
}
