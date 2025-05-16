package ru.job4j.wait_notify.tasks.wait;

public class CountBarrier {

    private final Object monitor = this;
    private final int total;
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            System.out.println(Thread.currentThread().getName() + " увеличил счётчик, текущее значение = " + count);

            if (count >= total) {
                System.out.println("Выполняется пробуждение остальных потоков...");
                notifyAll();
            }
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count < total) {
                try {
                    System.out.println("Сплю пока счётчик не достиг нужного значения.");
                    System.out.println("Текущее значение = " + count + ", необходимо: " + total);
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(5);

        new Thread(() -> {
            System.out.println("Жду обновление счётчика...");
            countBarrier.await();
            System.out.println("Счётчик обновлён до нужного значения");
        }, "t1").start();

        new Thread(() -> {
            countBarrier.count();
            countBarrier.count();
            countBarrier.count();
            countBarrier.count();
            countBarrier.count();
        }, "t2").start();
    }
}
