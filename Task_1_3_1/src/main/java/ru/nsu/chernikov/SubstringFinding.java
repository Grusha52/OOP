package ru.nsu.chernikov;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;


public class SubstringFinding {
    public static ArrayList<Long> find(String file, String substring) throws IOException {
        ArrayList<Long> indexes = new ArrayList<>();
        byte[] sub = substring.getBytes(StandardCharsets.UTF_8);
        byte[] buf = new byte[sub.length + 1];
        int overlapping = sub.length - 1;
        byte[] overlapbuffer = new byte[overlapping];
        int bytesread;
        byte[] chunk = new byte[sub.length * 2];
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long symbolPointer = -1;
            boolean firstIt = true;

            while ((bytesread = raf.read(buf)) != -1) {

                if (!firstIt) {
                    System.arraycopy(overlapbuffer, 0, chunk, 0, overlapping);
                    System.arraycopy(buf, 0, chunk, overlapping, buf.length);
                } else {
                    Arrays.fill(overlapbuffer, (byte) 0b10000001);
                    System.arraycopy(overlapbuffer, 0, chunk, 0, overlapping);
                    System.arraycopy(buf, 0, chunk, overlapping, buf.length);
                    firstIt = false;
                }

                for (int i = 0; i < bytesread; i++) {

                    if ((chunk[i] & 0b11000000) != 0b10000000) {
                        symbolPointer++;

                        boolean flag = true;

                        for (int j = 0; j < sub.length; j++) {
                            if (chunk[i + j] != sub[j]) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            indexes.add(symbolPointer);
                        }
                    }


                }
                System.arraycopy(chunk, chunk.length - overlapping, overlapbuffer, 0, overlapping);
            }
        }
        return indexes;
    }
}