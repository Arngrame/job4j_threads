package ru.job4j.nonblockingalgo.tasks;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class CASCountTest {

    @Test
    public void whenIncrementUsing2Threads() throws InterruptedException {
        CASCount count = new CASCount();

        Thread t1 = new Thread(() -> IntStream.range(0, 100).forEach(i -> count.increment()));
        Thread t2 = new Thread(() -> IntStream.range(0, 200).forEach(i -> count.increment()));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertThat(count.get()).isEqualTo(300);
    }


    @Test
    public void whenIncrementUsing3Threads() throws InterruptedException {
        CASCount count = new CASCount();

        Thread t1 = new Thread(() -> IntStream.range(0, 100).forEach(i -> count.increment()));
        Thread t2 = new Thread(() -> IntStream.range(0, 200).forEach(i -> count.increment()));
        Thread t3 = new Thread(() -> IntStream.range(0, 300).forEach(i -> count.increment()));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        assertThat(count.get()).isEqualTo(600);
    }
}