package ru.nsu.chernikov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Testing player class.
 */
class PlayerTest {

    @Test
    void playerTests() {
        Player player = new Player();
        Deck deck = new Deck(new Cards(Rank.JACK, Suit.HEARTS),
                new Cards(Rank.FIVE, Suit.DIAMONDS));
        player.giveCard(deck.getCard());
        player.showHand();
        player.aceCheck(player);
        player.hand.add(new Cards(Rank.ACE, Suit.DIAMONDS));
        player.aceCheck(player);
        player.score = 12;
    }

    @Test
    void dealerTests() {
        Dealer dealer = new Dealer();
        dealer.hand.add(new Cards(Rank.ACE, Suit.DIAMONDS));
        dealer.hand.add(new Cards(Rank.SEVEN, Suit.DIAMONDS));
        dealer.score = 17;
        Player player = new Player();
        Deck deck = new Deck(new Cards(Rank.FIVE, Suit.DIAMONDS), new Cards(Rank.TEN, Suit.CLUBS));
        assertEquals(0, dealer.dealerGameplay(player, dealer, deck));

        dealer.hand.clear();
        dealer.hand.add(new Cards(Rank.TEN, Suit.DIAMONDS));
        dealer.hand.add(new Cards(Rank.SIX, Suit.DIAMONDS));
        dealer.score = 16;
        assertEquals(1, dealer.dealerGameplay(player, dealer, deck));

        dealer.hand.clear();
        dealer.hand.add(new Cards(Rank.TEN, Suit.DIAMONDS));
        dealer.hand.add(new Cards(Rank.SIX, Suit.DIAMONDS));
        dealer.score = 16;
        assertEquals(2, dealer.dealerGameplay(player, dealer, deck));
    }
}