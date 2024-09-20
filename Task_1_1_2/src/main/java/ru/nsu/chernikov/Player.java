package ru.nsu.chernikov;

import java.util.List;
import java.util.ArrayList;

public class Player {
    ArrayList<Cards> deck;
    int score = 0;

    public Player(){
        deck = new ArrayList<>();
    }

    public void giveCard(Cards card){
        deck.add(card);
        score += card.cardRank.score;
    }

    public void showHand(){
        System.out.print(deck);
    }

    public void aceCheck(Player cards) {
        if(cards.score <= 21){
            return;
        }
        for (Cards card : cards.deck) {
            if (card.value == 11) {
                card.value = 1;
                cards.score -= 10;
            }
        }
    }
}
