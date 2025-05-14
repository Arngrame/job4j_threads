package ru.job4j.concurrent.tasks.thread_local;

public class SecondThread extends Thread {
    @Override
    public void run() {
        System.out.println(ThreadLocalDemo.threadLocal.get());
        ThreadLocalDemo.threadLocal.set("Это поток 2.");
        System.out.println(ThreadLocalDemo.threadLocal.get());
    }
}
