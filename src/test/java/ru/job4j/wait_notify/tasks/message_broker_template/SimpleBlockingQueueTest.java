package ru.job4j.wait_notify.tasks.message_broker_template;

import org.junit.jupiter.api.Test;
import ru.job4j.wait_notify.tasks.message_broker_template.SimpleBlockingQueue;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleBlockingQueueTest {

    @Test
    public void whenOneProducerAndOneConsumerLimit5() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        final int messageCount = 15;

        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < messageCount; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "producer");

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < messageCount; i++) {
                try {
                    queue.poll();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "consumer");

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    public void whenOneProducerAndOneConsumerLimit5AndSleep() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        final int messageCount = 15;

        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < messageCount; i++) {
                try {
                    queue.offer(i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "producer");

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < messageCount; i++) {
                try {
                    queue.poll();
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "consumer");

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        assertThat(queue.isEmpty()).isTrue();
    }
}