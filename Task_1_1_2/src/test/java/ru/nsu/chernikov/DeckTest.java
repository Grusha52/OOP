package ru.nsu.chernikov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void deckTests() {

        Deck deck = new Deck();
        assertEquals(52, deck.deck.size());
        assertEquals(deck.deck.get(0), deck.getCard());
    }
}
