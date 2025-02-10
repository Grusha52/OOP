package ru.nsu.chernikov;

import java.util.ArrayList;

/**
 * PrimeFinding using parallelStream.
 */
public class PrimeNumbersParallels {

    /** Is it prime?
     *
     * @param n number.
     * @return true or false.
     */
    public static boolean notPrime(int n) {
        if (n <= 1) {
            return true;
        }

        for (int d = 2; d < n; d++) {
            if (n % d == 0) {
                return true;
            }
        }
        return false;
    }

    /** parallel search.
     *
     * @param numbers our list of numbers.
     * @return true or false.
     */
    public static boolean parallel(ArrayList<Integer> numbers) {
        return numbers.parallelStream().anyMatch(PrimeNumbersParallels::notPrime);
    }

}
