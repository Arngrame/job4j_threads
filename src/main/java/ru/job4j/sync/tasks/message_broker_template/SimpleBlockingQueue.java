package ru.job4j.sync.tasks.message_broker_template;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() >= limit) {
            System.out.println("Очередь переполнена... жду пока сообщения считают потребители");
            wait();
        }

        queue.offer(value);
        System.out.println("Сообщение опубликовано. Текущий размер очереди = " + queue.size());
        notify();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Очередь пустая... жду сообщений от производителей");
            wait();
        }
        T element = queue.poll();
        System.out.println("Прочитал сообщение. Текущий размер очереди = " + queue.size());
        notify();
        return element;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                try {
                    queue.offer(i);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        }, "producer");

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                try {
                    queue.poll();
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        }, "consumer");


        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}