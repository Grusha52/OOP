package ru.nsu.chernikov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeNumbersParallels {

    public static volatile boolean hasNonPrime = false;

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

    public static boolean parallel(ArrayList<Integer> numbers) throws InterruptedException {
        return numbers.parallelStream().anyMatch(PrimeNumbersParallels::notPrime);
    }

}
