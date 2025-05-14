package ru.job4j.concurrent.tasks.thread_sleep;

public class Wget {
    public static void main(String[] args) {
        try {
            for (int index = 0; index <= 100; index++) {
                System.out.print("\rLoading: " + index + "%");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }
}
