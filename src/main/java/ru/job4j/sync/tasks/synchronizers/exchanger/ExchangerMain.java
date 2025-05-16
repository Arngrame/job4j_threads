package ru.job4j.sync.tasks.synchronizers.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerMain {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            try {
                String gift = "Баскетбольный мяч";
                System.out.println("Я: приготовил " + gift + " и жду обмена...");
                String receivedGift = exchanger.exchange(gift);
                System.out.println("Я: Ура! Я получил " + receivedGift);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                String gift = "Воллейбольный мяч";
                System.out.println("Друг: Я приготовил " + gift + " и жду обмена...");
                String receivedGift = exchanger.exchange(gift);
                System.out.println("Друг: Ура! Я получил " + receivedGift);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
