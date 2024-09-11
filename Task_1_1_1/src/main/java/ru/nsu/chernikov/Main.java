package ru.nsu.chernikov;

import java.sql.SQLOutput;
import java.util.Scanner;

/** THE Main.
 *
 */
public class Main {

    /**
     * heapify helps keep max-heap in array.
     *
     * @param arr the array whose elements are to be sorted
     * @param n   number of elements in array
     * @param i   index of element you need to heapify
     */
    public static void heapify(int[] arr, int n, int i) {
        int big = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && arr[l] > arr[big]) {
            big = l;
        }
        if (r < n && arr[r] > arr[big]) {
            big = r;
        }
        if (big != i) {
            int tmp = arr[i];
            arr[i] = arr[big];
            arr[big] = tmp;
            heapify(arr, n, big);
        }

    }

    /**
     * sorts the elements of the array with heapsort.
     *
     * @param arr the array whose elements are sorting
     */
    public static int[] heapsort(int[] arr) {

        int n = arr.length;

        for (int i = (n / 2 - 1); i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapify(arr, i, 0);
        }

        return (arr);

    }

    /**
     * communication with user.
     */
    public static void main(String[] args) {
        int[] arr;
        arr = new int[1000];
        for (int i = 999; i>=0;i--){
            arr[i] = i;
        }
        long sTime = System.nanoTime();
        heapsort(arr);
        long endTime = System.nanoTime();
        long duration = endTime - sTime;
        System.out.println(duration + " time");
    }
}