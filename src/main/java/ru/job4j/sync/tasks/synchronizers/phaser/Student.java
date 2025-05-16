package ru.job4j.sync.tasks.synchronizers.phaser;

import java.util.concurrent.Phaser;

public class Student implements Runnable {
    private final String name;
    private final Phaser phaser;
    private final String[] phases;

    public Student(String name, Phaser phaser, String[] phases) {
        this.name = name;
        this.phaser = phaser;
        this.phases = phases;
    }

    @Override
    public void run() {
        for (int i = phaser.getPhase(); i < phases.length; i++) {
            System.out.println("Ученик " + name + " начал " + phases[i]);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Ученик " + name + " завершил " + phases[i]);
            phaser.arriveAndAwaitAdvance();
        }
    }
}
