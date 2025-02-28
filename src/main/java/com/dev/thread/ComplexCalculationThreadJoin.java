package com.dev.thread;

import java.math.BigInteger;

public class ComplexCalculationThreadJoin {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);

        thread1.start();
        thread2.start();

        try {
            thread1.join(); // Wait for thread1 to complete
            thread2.join(); // Wait for thread2 to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return BigInteger.ZERO; // Or handle error appropriately
        }

        result = thread1.getResult().add(thread2.getResult());

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ZERO; // Changed default from ONE to ZERO
        private final BigInteger base;    // Made final
        private final BigInteger power;   // Made final

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            /*
            Implement the calculation of result = base ^ power
            */
            result = base.pow(power.intValueExact()); // Changed from Math.pow to BigInteger.pow
        }

        public BigInteger getResult() {
            return result;
        }
    }

    // For testing
    public static void main(String[] args) {
        ComplexCalculationThreadJoin calc = new ComplexCalculationThreadJoin();
        BigInteger result = calc.calculateResult(
                BigInteger.valueOf(100),
                BigInteger.valueOf(30),
                BigInteger.valueOf(30),
                BigInteger.valueOf(209)
        );
        System.out.println(result); // Should print 17 (2³ + 3² = 8 + 9 = 17)
    }
}