package ru.job4j.sync.tasks.synchronizers.semaphore;

import java.util.concurrent.Semaphore;

class Car implements Runnable {
    private final int id;
    private final Semaphore semaphore;

    public Car(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            System.out.println("Машина " + id + " подъехала к парковке.");
            semaphore.acquire();

            System.out.println("Машина " + id + " припарковалась. Осталось мест: " + semaphore.availablePermits());
            System.out.println("Машина " + id + " стоит 2 секунды");
            Thread.sleep(2000);

            semaphore.release();
            System.out.println("Машина " + id + " уехала. Свободных мест: " + semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}