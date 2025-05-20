package ru.job4j.thread_pools.tasks.tp_impl;

import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadPoolTest {

    @Test
    public void whenIncrement() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        AtomicInteger counter = new AtomicInteger(0);

        for (int i = 0; i < 1000; i++) {
            threadPool.work(counter::incrementAndGet);
        }

        Thread.sleep(3000);
        threadPool.shutdown();

        assertEquals(1000, counter.get());
    }

    @Test
    public void whenInsertRandomUUIDs() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        Queue<UUID> queue = new ConcurrentLinkedDeque();
        for (int i = 0; i < 1000; i++) {
            threadPool.work(() -> queue.add(UUID.randomUUID()));
        }

        Thread.sleep(3000);
        threadPool.shutdown();

        assertEquals(1000, queue.size());
    }

}