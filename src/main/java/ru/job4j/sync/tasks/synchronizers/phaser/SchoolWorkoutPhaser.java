package ru.job4j.sync.tasks.synchronizers.phaser;

import java.util.concurrent.Phaser;

public class SchoolWorkoutPhaser {

    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(1); // Главный поток
        String[] phases = {"Подтягивания", "Бег", "Прыжки"};
        String[] names = {"Катя", "Дима", "Слава"};

        for (int i = 0; i < 3; i++) {
            phaser.register();
            new Thread(new Student(names[i], phaser, phases)).start();
        }

        phaser.arriveAndDeregister();

        Thread.sleep(1500);

        System.out.println("\nОпоздавший присоединяется к упражнениям\n");
        phaser.register();
        new Thread(new Student("Федя", phaser, phases)).start();
    }
}
