package ru.nsu.chernikov;

import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CardsTest {
    @Test
    void cardTests(){
        String input = "0\n".repeat(200);;
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        Game.game(100);


    }
}
