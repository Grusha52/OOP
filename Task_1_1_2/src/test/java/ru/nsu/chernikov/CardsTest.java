package ru.nsu.chernikov;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardsTest {
    @Test
    void cardsTests(){
        ArrayList<Cards> deck = new ArrayList<>();
        Deck.createDeck();
        assertEquals(52, deck.size());
    }
}
