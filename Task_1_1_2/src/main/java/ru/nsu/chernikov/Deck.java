package ru.nsu.chernikov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    static List<Cards> deck;

    public Deck(){
        createDeck();
    }

    public static void createDeck(){
        deck = new ArrayList<>();
        for (Suit suit : Suit.values()){
            for (Rank rank : Rank.values()){
                deck.add(new Cards(rank, suit));
            }
        }
        Collections.shuffle(deck);

    }
    public static List<Cards> getDeck(){
        return deck;
    }

    public void showDeck(){
        System.out.println(deck);
    }

    static Cards getCard(){
        if (deck.isEmpty()){
            createDeck();
            return getCard();
        } else {
            return deck.remove(0);
        }
    }

}
