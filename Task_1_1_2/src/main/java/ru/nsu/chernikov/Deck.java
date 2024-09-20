package ru.nsu.chernikov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    static ArrayList<Cards> deck;

    public Deck(){
        createDeck();
    }

    public static void createDeck(){
            deck = new ArrayList<>();
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    deck.add(new Cards(rank, suit));
                }
            }
            Collections.shuffle(deck);

    }
    public ArrayList<Cards> getDeck(){
        return deck;
    }

    public void showDeck(){
        System.out.println(deck);
    }

    static Cards getCard(){
        if (deck.isEmpty()){
            System.out.println("\nНовая колода!\n");
            createDeck();
            return getCard();
        } else {
            return deck.remove(0);
        }
    }
}
