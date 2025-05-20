package ru.job4j.thread_pools.tasks.tp_impl;

import ru.job4j.wait_notify.tasks.message_broker_template.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class ThreadPool {

    private final int threadPoolSize = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(threadPoolSize);

    public ThreadPool() {
        Runnable task = () -> {
            while (!tasks.isEmpty() || !Thread.currentThread().isInterrupted()) {
                try {
                    tasks.poll().run();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        IntStream.range(0, threadPoolSize)
                .mapToObj(i -> new Thread(task))
                .forEach(threads::add);

        threads.forEach(Thread::start);
    }

    public void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void shutdown() {
        threads.stream()
                .filter(Thread::isAlive)
                .forEach(Thread::interrupt);
    }

    public static void main(String[] args) {

    }
}