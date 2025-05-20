package ru.nsu.chernikov;

import java.io.*;
import java.net.*;

public class Worker {
    private static final String MASTER_HOST = "localhost";
    private static final int MASTER_PORT = 6006;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(MASTER_HOST, MASTER_PORT);
        System.out.println("Connected to Master");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        try {
            while (true) {
                String line = in.readLine();
                if (line == null) break;

                String[] numberStrings = line.split(",");
                int[] numbers = new int[numberStrings.length];
                for (int i = 0; i < numberStrings.length; i++) {
                    numbers[i] = Integer.parseInt(numberStrings[i]);
                }

                boolean hasNonPrime = false;
                for (int num : numbers) {
                    if (!isPrime(num)) {
                        hasNonPrime = true;
                        break;
                    }
                }

                out.println(hasNonPrime);
                out.flush();
            }
        } finally {
            socket.close();
        }
    }

    private static boolean isPrime(int n) {
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
}