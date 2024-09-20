package ru.nsu.chernikov;

public class Blackjack {

    public static void showCards(Player player, Dealer dealer, int flag){
        if(flag == 0) {

            System.out.print("Ваши карты: ");
            player.showHand();
            System.out.printf(" => %d\n", player.score);
            System.out.print("Карты дилера: ");
            System.out.printf("[%s, <закрытая карта>]\n", dealer.deck.get(0));
        } else {

            System.out.print("Ваши карты: ");
            player.showHand();
            System.out.printf(" => %d\n", player.score);
            System.out.print("Карты дилера: ");
            dealer.showHand();
            System.out.printf(" => %d\n", dealer.score);
        }
    }
}

