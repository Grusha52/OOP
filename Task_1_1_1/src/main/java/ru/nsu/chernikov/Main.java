package ru.nsu.chernikov;

import java.util.Scanner;

public class Main {


        public static void  heapify(int[] arr, int n, int i){
            int Big = i;
            int L = 2*i + 1;
            int R = 2*i + 2;
            if (L < n && arr[L] > arr[Big]) {
                Big = L;
            }
            if (R < n && arr[R] > arr[Big]) {
                Big = R;
            }
            if (Big != i)
            {
                int tmp = arr[i];
                arr[i] = arr[Big];
                arr[Big] = tmp;
                heapify(arr, n, Big);
            }

        }

        public static void heapsort (int[] arr, int n){
            for (int i = (n / 2 - 1); i >= 0; i--){
                heapify(arr,n,i);
            }
            for (int i = n-1; i>=0; i--){
                int tmp = arr[0];
                arr[0] = arr[i];
                arr[i] = tmp;
                heapify(arr,i,0);
            }

        }
        public  static void printArray (int[] arr, int n){
            for (int i = 0; i < n; i++){
                System.out.print(arr[i] + " ");
            }
        }

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter number of numbers");
            int count = in.nextInt();
            in.nextLine();
            System.out.println("Enter numbers which you want to sort");
            String input = in.nextLine();
            String[] splitNumbers = input.split(" ");
            int[] numbers = new int[count];
            for (int i = 0; i<count; i++) {
                numbers[i] = Integer.parseInt(splitNumbers[i]);
            }
            heapsort(numbers, count);
            printArray(numbers,count);
    }
}