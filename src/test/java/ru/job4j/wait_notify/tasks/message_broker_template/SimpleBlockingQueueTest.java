package ru.job4j.wait_notify.tasks.message_broker_template;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;

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

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        producer.start();

        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        consumer.start();
        producer.join();

        consumer.interrupt();
        consumer.join();

        assertThat(buffer).containsExactly(0, 1, 2, 3, 4);
    }

    @Test
    public void whenFetch1024ThenGet1024() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(256);

        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 1024; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        producer.start();

        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        consumer.start();
        producer.join();

        consumer.interrupt();
        consumer.join();

        assertThat(buffer).hasSize(1024);
    }

    @Test
    public void whenFetch15ThenGet15() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 15; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        producer.start();

        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        consumer.start();
        producer.join();

        consumer.interrupt();
        consumer.join();

        assertThat(buffer).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    }
}