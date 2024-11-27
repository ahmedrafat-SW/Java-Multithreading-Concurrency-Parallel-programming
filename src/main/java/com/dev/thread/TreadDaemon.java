package com.dev.thread;

import java.math.BigInteger;

public class TreadDaemon {

    public static void main(String[] args) {

        Thread thread = new Thread(new LongComputationTask(new BigInteger("200000000000000"), new BigInteger("1000000000000")));
        // Daemon or user Thread not exit if the program exits
        thread.setDaemon(true);
        thread.start();
        thread.interrupt();
    }

    private static class LongComputationTask  implements Runnable{
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power){
            this.base = base;
            this.power = power;
        }


        @Override
        public void run() {
            System.out.println( base + " ^ "+ power + " = " + calculatePow(base, power));
        }

        private BigInteger calculatePow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) !=0 ; i = i.add(BigInteger.ONE)){
//                explicitly handle Thread interruption because this method doesn't handle InterruptedException
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("Prematurely interrupted Computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}
