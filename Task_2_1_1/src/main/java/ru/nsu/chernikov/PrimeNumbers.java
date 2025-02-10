package ru.nsu.chernikov;

import java.util.ArrayList;

public class PrimeNumbers{

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        for (int d = 2; d < n; d++){
            if (n % d == 0) {
                return false;
            }
        }
        return true;
    }


    public static boolean parallel(ArrayList<Integer> mas) {
        for (int num : mas) {
            if (!isPrime(num)) {
                return true;
            }
        }
        return false;
    }
}