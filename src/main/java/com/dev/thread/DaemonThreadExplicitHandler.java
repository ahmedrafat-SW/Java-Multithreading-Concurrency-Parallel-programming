package com.dev.thread;

public class DaemonThreadExplicitHandler {

    public static void main(String [] args) {
        Thread thread = new Thread(new SleepingThread());
        thread.start();
        thread.interrupt();
    }

    private static class SleepingThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
}