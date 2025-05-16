package ru.job4j.wait_notify.tasks.stop_consumer;


import ru.job4j.wait_notify.tasks.message_broker_template.SimpleBlockingQueue;

public class ParallelSearch {

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

        Thread consumer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println(queue.poll());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread producer = new Thread(() -> {
            try {
                for (int index = 0; index != 3; index++) {
                    queue.offer(index);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                consumer.interrupt();
            }
        });

        consumer.start();
        producer.start();

        consumer.join();
        producer.join();
    }
}