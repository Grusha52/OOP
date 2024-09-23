package ru.nsu.chernikov;


public class Dealer extends Player{

    public Dealer(){
        super();

    }

    public int dealerGameplay(Player player, Dealer dealer) {
        while (dealer.score < 17) {
            dealer.giveCard(Deck.getCard());
            dealer.aceCheck(dealer);
            System.out.printf("\nДилер открывает карту %s\n", dealer.hand.get(dealer.hand.size() - 1));
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