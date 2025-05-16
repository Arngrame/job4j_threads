package ru.job4j.sync.tasks.synchronizers.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class MoverMain {

    public static void main(String[] args) {
        int movers = 4;

        CyclicBarrier meetingBarrier = new CyclicBarrier(movers, () ->
                System.out.println("\nЯщик погружен...")
        );

        for (int i = 0; i < movers; i++) {
            new Thread(new Mover(i, meetingBarrier)).start();
        }
    }

}
