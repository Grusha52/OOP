package ru.nsu.chernikov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Heaptests {
    @Test
    void heaptest() {
        assertEquals("142 1234 1235 14213 313312", Main.heapsort((new int[] {142,1234,313312,14213,1235},5),(new int[] {142,1234,1235,14213,313312},5));
    }



}
