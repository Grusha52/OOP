package ru.nsu.chernikov;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SubstringFindingTest {

    @Test
    void creatfile() throws IOException {
        File file = new File("example.txt");
        if (file.createNewFile()) {
            System.out.println("file example.txt created");
        } else {
            System.out.println("file already exists");
        }
        FileWriter fileWriter = new FileWriter("example.txt");
        fileWriter.write("🚀🚀🚀🚀🚀🚀🚀🚀🚀");
        fileWriter.close();
//        System.out.println(SubstringFinding.find("example.txt", "🚀🚀"));
        if (file.delete()) {
            System.out.println("file deleted ☺️");
        } else {
            System.out.println("file disposal error");
        }
    }

    @Test
    void creatfile2() throws IOException {
        ArrayList<Long> result = new ArrayList<>();

        File file = new File("example.txt");
        if (file.createNewFile()) {
            System.out.println("file example.txt created");
        } else {
            System.out.println("file already exists");
        }
        FileWriter fileWriter = new FileWriter("example.txt");
        long a = 0L;

        for (int i = 0; i < 500000000; i++) {
            fileWriter.write("😇😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁😁");
            result.add(a);
            a += 18L;
        }
        fileWriter.close();
//        System.out.println(SubstringFinding.find("example.txt","😇"));
        assertEquals(result, SubstringFinding.find("example.txt", "😇"));
        if (file.delete()) {
            System.out.println("file deleted ☺️");
        } else {
            System.out.println("file disposal error");
        }
    }

}