package com.dev.thread;

public class InitialThread extends Thread{

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        System.out.println("Job-Thread- " +  this.getId());

    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    @Override
    public State getState() {
        return super.getState();
    }

    @Override
    public long getId() {
        return super.getId();
    }

}
