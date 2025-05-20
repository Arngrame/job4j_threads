package ru.job4j.thread_pools.tasks.executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotificationService {

    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final String SUBJECT_TEMPLATE = "Notification %s to email %s.";
    private static final String BODY_TEMPLATE = "Add a new event to %s";


    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format(SUBJECT_TEMPLATE, user.username(), user.email());
            String body = String.format(BODY_TEMPLATE, user.username());

            send(subject, body, user.email());
        });
    }

    public void close() {
        pool.shutdown();
    }

    public void send(String subject, String body, String email) {

    }

}
