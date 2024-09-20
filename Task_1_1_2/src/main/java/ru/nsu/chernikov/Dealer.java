package ru.nsu.chernikov;

import java.util.ArrayList;
import java.util.Scanner;

public class Dealer {
    ArrayList<Cards> deck;
    int score = 0;

    public Dealer(){
        deck = new ArrayList<>();
    }

    public void giveCard(Cards card){
        deck.add(card);
        score += card.cardRank.score;

    }

    public void showHand(){
        System.out.println(deck);
    }

    public void aceCheck(Dealer cards) {
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
    public int dealerGameplay(Player player, Dealer dealer) {
        while (dealer.score < 17) {
            dealer.giveCard(Deck.getCard());
            dealer.aceCheck(dealer);
            System.out.printf("\nДилер открывает карту %s\n", dealer.deck.get(dealer.deck.size() - 1));
            Blackjack.showCards(player, dealer, 1);
            if (dealer.score == 21) {
                return 1;
            } else if (dealer.score > 21) {
                return 2;
            }
        }
        return 0;
    }
}