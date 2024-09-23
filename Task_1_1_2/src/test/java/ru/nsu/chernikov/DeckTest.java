package ru.nsu.chernikov;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeckTest {

    @Test
    void deckTests(){

        Deck deck = new Deck();
        assertEquals(52, Deck.deck.size());
        assertEquals(Deck.deck.get(0), Deck.getCard());
    }
}
