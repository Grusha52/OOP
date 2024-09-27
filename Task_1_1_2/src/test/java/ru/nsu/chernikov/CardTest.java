package ru.nsu.chernikov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void cardTests() {
        Cards card = new Cards(Rank.JACK, Suit.SPADES);
        assertEquals("Валет Пики (10)", card.toString());
        card = new Cards(Rank.ACE, Suit.HEARTS);
        assertEquals("Туз Черви (11)", card.toString());
        card = new Cards(Rank.FIVE, Suit.DIAMONDS);
        assertEquals("Пять Буби (5)", card.toString());
        card = new Cards(Rank.EIGHT, Suit.SPADES);
        assertEquals("Восемь Пики (8)", card.toString());

    }

    @Test
    void deckTest() {
        Deck deck = new Deck();
        assertEquals(52, deck.deck.size());
    }

    @Test
    void getRankTest() {
        Cards card = new Cards(Rank.JACK, Suit.DIAMONDS);
        assertEquals(card.getRank(), "Валет");
        card = new Cards(Rank.ACE, Suit.DIAMONDS);
        assertEquals(card.getRank(), "Туз");
        card = new Cards(Rank.FIVE, Suit.DIAMONDS);
        assertEquals(card.getRank(), "Пять");
        card = new Cards(Rank.SEVEN, Suit.DIAMONDS);
        assertEquals(card.getRank(), "Семь");
        card = new Cards(Rank.QUEEN, Suit.DIAMONDS);
        assertEquals(card.getRank(), "Дама");

    }

    @Test
    void getSuitTest() {

        Cards card = new Cards(Rank.JACK, Suit.DIAMONDS);
        assertEquals(card.getSuit(), "Буби");
        card = new Cards(Rank.JACK, Suit.CLUBS);
        assertEquals(card.getSuit(), "Крести");
        card = new Cards(Rank.JACK, Suit.HEARTS);
        assertEquals(card.getSuit(), "Черви");
        card = new Cards(Rank.JACK, Suit.SPADES);
        assertEquals(card.getSuit(), "Пики");


    }
}
