package ru.job4j.sync.tasks.synchronizers.countdownlatch;

import java.util.concurrent.CountDownLatch;

class RunnerBoy implements Runnable {
    private final String name;
    private final CountDownLatch startLatch;

    public RunnerBoy(String name, CountDownLatch startLatch) {
        this.name = name;
        this.startLatch = startLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + ": Жду свистка для старта...");

            startLatch.await();

            System.out.println(name + " стартовал");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}