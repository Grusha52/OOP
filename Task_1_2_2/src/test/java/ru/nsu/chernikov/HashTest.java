package ru.nsu.chernikov;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class HashTest {
    @Test
    void mainTest() {
        HashTable<String, Integer> ages = new HashTable<>();
        ages.put("Grisha", 23);
        ages.put("Ildar", 19);
        ages.put("Bogdan", 15);
        ages.put("Kolya", 145);
        ages.put("Kirill", 1234567890);

        System.out.println(ages.toString());
        assertEquals("145", ages.get("Kolya").toString());
        ages.remove("Ildar");
        assertEquals("{Kolya=145, Bogdan=15, Kirill=1234567890, Grisha=23}", ages.toString());

        assertTrue(ages.containsKey("Bogdan"));
        assertFalse(ages.containsKey("Ildar"));

        ages.update("Grisha", 322);

        assertEquals("{Kolya=145, Bogdan=15, Kirill=1234567890, Grisha=322}", ages.toString());

        HashTable<String, Integer> ages2 = new HashTable<>();

        ages2.put("Bogdan", 15);
        ages2.put("Kolya", 145);
        ages2.put("Kirill", 1234567890);
        ages2.put("Grisha", 322);

        assertEquals(ages, ages2);

        try {

            Iterator<Entry<String, Integer>> tableIterator = ages.iterator();
            while (tableIterator.hasNext()) {
                tableIterator.next();
                ages.put("Andrew", 26);
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Concurrent modification");
        }
    }
}
