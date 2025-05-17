package ru.job4j.nonblockingalgo.tasks;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger();

    public void increment() {
        int tmp;
        int ref;
        do {
            ref = count.get();
            tmp = ref + 1;
        } while (!count.compareAndSet(ref, tmp));
    }

    public int get() {
        return count.get();
    }
}