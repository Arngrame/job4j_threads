package ru.job4j.sync.tasks.synchronizers.semaphore;

import java.util.concurrent.Semaphore;

public class ParkingSemaphoreMain {
    public static void main(String[] args) {
        int availableSlots = 5;
        int carsToPark = 7;
        Semaphore semaphore = new Semaphore(availableSlots);

        System.out.println("Машины приезжают каждую секунду");
        for (int i = 1; i < carsToPark + 1; i++) {
            new Thread(new Car(i, semaphore)).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
