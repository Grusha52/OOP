package ru.nsu.chernikov;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PrimeNumbersThreads {
    private static ConcurrentLinkedQueue<Integer> queue;
    public static volatile boolean hasNonPrime = false;

    /** Is it prime?
     *
     * @param n number.
     * @return true or false.
     */
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        for (int d = 2; d < n; d++) {
            if (n % d == 0) {
                return false;
            }
        }
        return true;
    }

    public static void hasPrime(boolean result) {
        if (!result) {
            hasNonPrime = true;
        }
    }

    /** Thread search.
     *
     * @param numbers our list of numbers.
     * @param countThreads count of threads.
     * @return true of false.
     */
    public static boolean thread(ArrayList<Integer> numbers, int countThreads) {
        queue = new ConcurrentLinkedQueue<>(numbers);

        Thread[] threads = new Thread[countThreads];
        for (int i = 0; i < countThreads; i++) {
            threads[i] = new Thread(() -> {
                while (!queue.isEmpty())
                    hasPrime(isPrime(queue.poll()));
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Thread was interrupted" + e.getMessage());
            }
        }
        return hasNonPrime;
    }
}
