package ru.nsu.chernikov;

import java.util.List;
import java.util.ArrayList;

/**
 * player plays
 */
public class Player {
    List<Cards> hand;
    int score = 0;

    /**
     * konstruktor
     */
    public Player() {
        hand = new ArrayList<>();
    }

    /**
     * giving card to smb
     *
     * @param card which we are about to give
     */
    public void giveCard(Cards card) {
        hand.add(card);
        score += card.cardRank.score;
    }

    /**
     * show a player hand
     */
    public void showHand() {
        System.out.print(hand);
    }

    /**
     * checking is there any aces?
     *
     * @param cards player cards
     */
    public void aceCheck(Player cards) {
        if (cards.score <= 21) {
            return;
        }
        for (Cards card : cards.hand) {
            if (card.value == 11) {
                card.value = 1;
                cards.score -= 10;
            }
        }
    }
}