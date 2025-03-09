package ru.nsu.chernikov;

import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void mainTest() throws InterruptedException {
        Main.main(new String[]{});
        Thread.sleep(15000);
    }
}
