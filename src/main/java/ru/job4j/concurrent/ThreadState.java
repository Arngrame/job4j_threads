package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Thread name is " + Thread.currentThread().getName());
        Thread first = new Thread(runnable);
        Thread second = new Thread(runnable);

        System.out.println(first.getName() + " has state = " + first.getState());
        System.out.println(second.getName() + " has state = " + second.getState());

        first.start();
        second.start();

        while (first.getState() != Thread.State.TERMINATED && second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getName() + " has state = " + first.getState());
            System.out.println(second.getName() + " has state = " + second.getState());
        }

        System.out.println(first.getName() + " has state = " + first.getState());
        System.out.println(second.getName() + " has state = " + second.getState());

        System.out.println("The job has finished");
    }
}