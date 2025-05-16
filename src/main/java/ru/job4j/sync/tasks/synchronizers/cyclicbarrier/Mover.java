package ru.job4j.sync.tasks.synchronizers.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

class Mover implements Runnable {
    private final int id;
    private final CyclicBarrier barrier;

    public Mover(int id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Грузчик " + id + " идёт поднимать ящик...");
            Thread.sleep(1000 + (int) (Math.random() * 2000));
            System.out.println("Грузчик " + id + " готов поднимать и ждёт остальных.");

            barrier.await();

            System.out.println("Грузчик " + id + " погрузил ящик и готов вернуться к дальнейшей работе.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}