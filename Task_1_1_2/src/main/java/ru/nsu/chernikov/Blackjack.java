package ru.nsu.chernikov;

import java.util.Scanner;


/** THE BLACKJACK.
 *
 */
public class Blackjack {

    /** THE GAME.
     *
     * @param limiter limit of rounds number
     */
    public static void game(int limiter){
        int round = 0;
        Scanner in = new Scanner(System.in);
        int dealer_score = 0;
        int player_score = 0;
        Deck playdeck = new Deck();

        System.out.println("Добро пожаловать в Блэкджек!");

        while (round != limiter) {

            boolean endFlag = false;
            round++;

            Player player_hand = new Player();
            Dealer dealer_hand = new Dealer();

            System.out.println("\nВведите WW чтобы продолжить");
            in.next();

            System.out.printf("\nРаунд %d\n", round);

            player_hand.giveCard(Deck.getCard());
            player_hand.giveCard(Deck.getCard());
            player_hand.aceCheck(player_hand);

            dealer_hand.giveCard(Deck.getCard());
            dealer_hand.giveCard(Deck.getCard());
            dealer_hand.aceCheck(dealer_hand);

            System.out.println("Дилер раздал карты");

            if ((player_hand.score == dealer_hand.score) && (player_hand.score == 21)){
                player_score++;
                dealer_score++;
                showCards(player_hand, dealer_hand,0);
                System.out.printf("АХАХААХАХАХАХАХ НИЧЬЯ! Счет %d:%d\n", player_score, dealer_score);
                continue;
            }
            else if (player_hand.score == 21){
                player_score++;

                showCards(player_hand, dealer_hand,0);

                System.out.println("Ты кто такой чтоб это сделать?");
                System.out.printf("Вы выиграли раунд! Счет %d:%d\n", player_score, dealer_score);
                continue;
            }

            Blackjack.showCards(player_hand, dealer_hand,0);

            System.out.println("\nВаш ход");
            System.out.println("--------");
            int choose = 1;

            while(choose != 0){
                System.out.println("\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
                choose = in.nextInt();
                if(choose == 1){
                    player_hand.giveCard(playdeck.getCard());
                    player_hand.aceCheck(player_hand);
                    if(player_hand.score > 21){
                        endFlag = true;
                        Blackjack.showCards(player_hand, dealer_hand,1);
                        dealer_score++;
                        System.out.printf("КУДАААААА Счет %d:%d\n", player_score, dealer_score);
                        break;
                    }else{
                        System.out.printf("Вы открыли карту %s\n", player_hand.hand.get(dealer_hand.hand.size() - 1));
                        showCards(player_hand, dealer_hand,0);
                    }
                }
            }
            if (endFlag){
                continue;
            }
            System.out.println("\nХод дилера");
            System.out.println("--------");
            System.out.printf("Дилер открывает закрытую карту %s\n", dealer_hand.hand.get(dealer_hand.hand.size() - 1));
            showCards(player_hand, dealer_hand, 1);

            if(dealer_hand.score == 21){
                dealer_score++;
                System.out.printf("ПРОИГРАЛ Счет %d:%d\n", player_score, dealer_score);
                continue;
            }

            if(dealer_hand.dealerGameplay(player_hand, dealer_hand) == 0){
                if(player_hand.score > dealer_hand.score){
                    player_score++;
                    System.out.printf("\nУРАААААА ПОБЕДА ПОБЕДА!!!!!! Счет %d:%d\n", player_score, dealer_score);
                }else if (player_hand.score == dealer_hand.score){
                    player_score++;
                    dealer_score++;
                    System.out.printf("АХАХААХАХАХАХАХ НИЧЬЯ! Счет %d:%d\n", player_score, dealer_score);
                } else{
                    dealer_score++;
                    System.out.printf("ПРОИГРАЛ Счет %d:%d\n", player_score, dealer_score);
                }
            } else if(dealer_hand.dealerGameplay(player_hand, dealer_hand) == 1){
                dealer_score++;
                System.out.printf("ПРОИГРАЛ Счет %d:%d\n", player_score, dealer_score);
            } else{
                player_score++;
                System.out.printf("\nУРАААААА ПОБЕДА ПОБЕДА!!!!!! Счет %d:%d\n", player_score, dealer_score);
            }
        }

    }

    /** It shows cards and score of each player
     *
     * @param player player's hand
     * @param dealer dealer's hand
     * @param flag for closed dealer's hand
     */
    public static void showCards(Player player, Dealer dealer, int flag){
        if(flag == 0) {

            System.out.print("Ваши карты: ");
            player.showHand();
            System.out.printf(" => %d\n", player.score);
            System.out.print("Карты дилера: ");
            System.out.printf("[%s, <закрытая карта>]\n", dealer.hand.get(0));
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

