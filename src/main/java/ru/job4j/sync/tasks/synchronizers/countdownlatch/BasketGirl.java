package ru.job4j.sync.tasks.synchronizers.countdownlatch;

import java.util.concurrent.CountDownLatch;

class BasketGirl implements Runnable {
    private final String name;
    private final CountDownLatch readyLatch;

    public BasketGirl(String name, CountDownLatch readyLatch) {
        this.name = name;
        this.readyLatch = readyLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("Баскетболистка " + name + " бросает в кольцо");

            Thread.sleep(1000);

            readyLatch.countDown();
            System.out.println(name + " успешно выполнила бросок");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}