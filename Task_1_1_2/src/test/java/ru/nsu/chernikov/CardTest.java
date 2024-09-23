package ru.nsu.chernikov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void cardTests() {
        Cards card = new Cards(Rank.JACK, Suit.SPADES);
        assertEquals("Валет Пики (10)", card.toString());
    }

    @Test
    void deckTest() {
        Deck deck = new Deck();
        assertEquals(52, Deck.deck.size());
    }

    @Test
    void getRankTest() {
        Cards card = new Cards(Rank.JACK, Suit.DIAMONDS);
        assertEquals(card.getRank(), "Валет");
    }

    @Test
    void getSuitTest() {

        Cards card = new Cards(Rank.JACK, Suit.DIAMONDS);
        assertEquals(card.getSuit(), "Буби");
    }
}
