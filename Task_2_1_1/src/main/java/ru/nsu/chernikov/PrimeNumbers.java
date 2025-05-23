package ru.nsu.chernikov;

import java.util.ArrayList;

/** Simple PrimeNumbers finder.
 *
 */
public class PrimeNumbers {

    /** Is it prime?.
     *
     * @param n our number.
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

    /** Sequential search.
     *
     * @param mas our list of numbers.
     * @return true false.
     */
    public static boolean sequential(ArrayList<Integer> mas) {
        for (int num : mas) {
            if (!isPrime(num)) {
                return true;
            }
        }
        return false;
    }
}