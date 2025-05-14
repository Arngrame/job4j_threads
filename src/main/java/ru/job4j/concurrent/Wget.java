package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        try {
            for (int index = 0; index <= 100; index++) {
                // \r - возврат каретки в начало строки для её обновления через промежутки времени
                System.out.print("\rLoading: " + index + "%");
                // 1000ms = 1s
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }
}
