package com.dev.thread;

import java.util.List;
import java.util.ArrayList;

public class MultiExecutor {

    private List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        List<Thread> threads = new ArrayList<>(tasks.size());

        for(Runnable task : tasks){
            Thread thread = new Thread(task);
            threads.add(thread);
        }

        for(Thread thr : threads){
            thr.start();
        }
    }
}