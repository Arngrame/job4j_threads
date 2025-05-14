package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    private final char[] process = new char[]{'-', '\\', '|', '/'};

    @Override
    public void run() {
        try {
            int index = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\rLoading... " + process[index++]);

                if (index > process.length - 1) {
                    index = 0;
                }

                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("\n" + Thread.currentThread().getName() + " has been interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConsoleProgress consoleProgress = new ConsoleProgress();
        Thread progress = new Thread(consoleProgress);

        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}
