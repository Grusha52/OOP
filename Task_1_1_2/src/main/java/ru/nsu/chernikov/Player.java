package ru.nsu.chernikov;

import java.util.List;
import java.util.ArrayList;

public class Player {
    List<Cards> hand;
    int score = 0;

    public Player(){
        hand = new ArrayList<>();
    }

    public void giveCard(Cards card){
        hand.add(card);
        score += card.cardRank.score;
    }

    public void showHand(){
        System.out.print(hand);
    }

    public void aceCheck(Player cards) {
        if(cards.score <= 21){
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