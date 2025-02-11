package ru.nsu.chernikov;

import java.util.ArrayList;

public class PrimeNumbersThreads {

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

    public static void hasPrime(int id, int countThreads, ArrayList<Integer> mas) {
        for (int count = 0; (id + count * countThreads) < mas.size(); count++) {
            if (!isPrime(mas.get(id + count * countThreads))) {
                hasNonPrime = true;
            }
        }
    }

    /** Thread search.
     *
     * @param numbers our list of numbers.
     * @param countThreads count of threads.
     * @return true of false.
     */
    public static boolean thread(ArrayList<Integer> numbers, int countThreads) {
        hasNonPrime = false;
        Thread[] threads = new Thread[countThreads];

        for (int i = 0; i < countThreads; i++) {
            int id = i;
            try {
                threads[id] = new Thread(() -> hasPrime(id, countThreads, numbers));
            } finally {
                threads[id].start();
            }
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
