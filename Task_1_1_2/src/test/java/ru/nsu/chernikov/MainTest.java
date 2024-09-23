package ru.nsu.chernikov;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;

/**
 * Testing game.
 */
class MainTest {

    @Test
    void cardTests() {
        String input = "0\n".repeat(200);
        ;
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        Blackjack.game(100);
    }
}
