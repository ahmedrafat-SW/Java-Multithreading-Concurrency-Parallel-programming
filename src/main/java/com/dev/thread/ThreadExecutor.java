package com.dev.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadExecutor {

    public static void main(String[] args) {
        InitialThread thread = new InitialThread();
        System.out.println("before thread execution");
        thread.start();
        System.out.println("after thread execution");

        List<Runnable> threads = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            threads.add(new Thread(() ->{
                System.out.println(Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
            }));
        }

        MultiExecutor multiExecutor = new MultiExecutor(threads);
        multiExecutor.executeAll();
    }
}
