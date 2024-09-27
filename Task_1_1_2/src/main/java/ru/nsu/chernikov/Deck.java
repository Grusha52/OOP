package ru.nsu.chernikov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * THE DECK.
 */
public class Deck {
    List<Cards> deck;

    /**
     * constructor of the deck.
     */
    public Deck() {
        createDeck();
    }
    public Deck(Cards card1, Cards card2) {
        deck = new ArrayList<>();
        deck.add(card1);
        deck.add(card2);
    }

    /**
     * creating shuffled deck.
     */
    public void createDeck() {
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
    Cards getCard() {
        if (deck.isEmpty()) {
            System.out.println("\nНовая колода!\n");
            createDeck();
            return getCard();
        } else {
            return deck.remove(0);
        }
    }
}
