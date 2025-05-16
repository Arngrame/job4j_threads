package ru.job4j.sync.tasks.synchronizers.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class SchoolWorkoutCDL {
    public static void main(String[] args) throws InterruptedException {
        int goodShots = 5;

        CountDownLatch readyLatch = new CountDownLatch(goodShots);
        CountDownLatch startLatch = new CountDownLatch(1);

        System.out.println("Физрук ждёт, пока все баскетболистки попадут\n");

        String[] girlsNames = new String[]{"Аня", "Катя", "Юля", "Оля", "Лена"};
        String[] boysNames = new String[]{"Паша", "Саша", "Гриша", "Ваня", "Дима"};

        for (int i = 0; i < goodShots; i++) {
            new Thread(new BasketGirl(girlsNames[i], readyLatch)).start();
            Thread.sleep(500);
        }

        System.out.println();

        for (int i = 0; i < 5; i++) {
            new Thread(new RunnerBoy(boysNames[i], readyLatch)).start();
            Thread.sleep(1000);
        }


        readyLatch.await();

        System.out.println("\nВсе готовы! Старт через 3 секунды...");
        Thread.sleep(3000);

        startLatch.countDown();
    }
}