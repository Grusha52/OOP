package ru.nsu.chernikov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HeapsortTests {
    @Test
    void testHeapsort() {

        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, Main.heapsort(new int[]{5, 4, 3, 2, 1}));

        assertArrayEquals(
                new int[]{-5, 3, 3, 4, 5, 5, 6, 6, 6, 7, 8, 9, 9, 34},
                Main.heapsort(new int[]{6, 5, 6, 3, 3, 4, 5, 7, 8, 6, 9, 9, 34, -5})
        );

        assertArrayEquals(
                new int[]{-6543, -234, -21, -12, 0, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 12, 12, 12, 12, 21, 23, 23, 32, 34, 35, 37, 123, 234, 452, 534, 646, 4543, 8680},
                Main.heapsort(new int[]{5, 4, 5, 6, 5, 34, 23, 452, 534, 6, 37, -234, 234, 12, 32, 35, 646, 8680, 4543, 23, 123, -12, -6543, 5, 5, 5, 5, 5, 5, 12, 12, 12, 21, -21, 0})
        );

        assertArrayEquals(new int[]{}, Main.heapsort(new int[]{}));

        assertArrayEquals(
                new int[]{1, 1, 1, 1, 1, 1},
                Main.heapsort(new int[]{1, 1, 1, 1, 1, 1})
        );

        assertArrayEquals(
                new int[]{-500, -250, 0, 250, 500},
                Main.heapsort(new int[]{250, 0, -500, -250, 500}));

        assertArrayEquals(new int[]{0}, Main.heapsort(new int[]{0}));

        assertArrayEquals(new int[]{-1000}, Main.heapsort(new int[]{-1000}));

        assertArrayEquals(new int[]{100000}, Main.heapsort(new int[]{100000}));

        assertArrayEquals(new int[]{0}, Main.heapsort(new int[]{0}));

        assertArrayEquals(
                new int[]{0, 1, 2, 3, 9999, 10000},
                Main.heapsort(new int[]{9999, 3, 10000, 2, 1, 0})
        );

        assertArrayEquals(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},
                Main.heapsort(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9})
        );

        assertArrayEquals(
                new int[]{-9, -8, -7, -6, -5, -4, -3, -2, -1, 0},
                Main.heapsort(new int[]{-9, -5, -7, -6, -8, -4, -3, 0, -1, -2})
        );

        assertArrayEquals(
                new int[]{-9, -8, -7, -6, -5, -4, -3, -2, -1, 0},
                Main.heapsort(new int[]{-9, -8, -7, -6, -5, -4, -3, -2, -1, 0})
        );
    }

}
