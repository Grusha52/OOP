package ru.nsu.chernikov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * THE DECK.
 */
public class Deck {
    static List<Cards> deck;

    /**
     * constructor of the deck.
     */
    public Deck() {
        createDeck();
    }

    /**
     * creating shuffled deck.
     */
    public static void createDeck() {
        deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Cards(rank, suit));
            }
        }
        Collections.shuffle(deck);

    }

    /**
     * Returns the card.
     *
     * @return returns the card
     */
    static Cards getCard() {
        if (deck.isEmpty()) {
            System.out.println("\nНовая колода!\n");
            createDeck();
            return getCard();
        } else {
            return deck.remove(0);
        }
    }
}
