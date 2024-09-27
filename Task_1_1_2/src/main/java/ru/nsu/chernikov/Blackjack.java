package ru.nsu.chernikov;

import java.util.Scanner;


/**
 * THE BLACKJACK.
 */
public class Blackjack {

    /**
     * THE GAME.
     *
     * @param limiter limit of rounds number
     */
    public static void game(int limiter) {
        int round = 0;
        Scanner in = new Scanner(System.in);
        int dealerScore = 0;
        int playerScore = 0;
        Deck playdeck = new Deck();

        System.out.println("Добро пожаловать в Блэкджек!");

        while (round != limiter) {

            boolean endFlag = false;
            round++;

            System.out.println("\nВведите WW чтобы продолжить");
            in.next();

            System.out.printf("\nРаунд %d\n", round);

            Player playerHand = new Player();

            playerHand.giveCard(playdeck.getCard());
            playerHand.giveCard(playdeck.getCard());
            playerHand.aceCheck(playerHand);

            Dealer dealerHand = new Dealer();

            dealerHand.giveCard(playdeck.getCard());
            dealerHand.giveCard(playdeck.getCard());
            dealerHand.aceCheck(dealerHand);

            System.out.println("Дилер раздал карты");

            if ((playerHand.score == dealerHand.score) && (playerHand.score == 21)) {
                playerScore++;
                dealerScore++;
                showCards(playerHand, dealerHand, 0);
                System.out.printf("АХАХААХАХАХАХАХ НИЧЬЯ! Счет %d:%d\n", playerScore, dealerScore);
                continue;
            } else if (playerHand.score == 21) {
                playerScore++;

                showCards(playerHand, dealerHand, 0);

                System.out.println("Ты кто такой чтоб это сделать?");
                System.out.printf("Вы выиграли раунд! Счет %d:%d\n", playerScore, dealerScore);
                continue;
            }

            Blackjack.showCards(playerHand, dealerHand, 0);

            System.out.println("\nВаш ход");
            System.out.println("--------");
            int choose = 1;

            while (choose != 0) {
                System.out.println("\nВведите “1”, чтобы взять карту, и “0”, чтобы остановиться..");
                choose = in.nextInt();
                if (choose == 1) {
                    playerHand.giveCard(playdeck.getCard());
                    playerHand.aceCheck(playerHand);
                    if (playerHand.score > 21) {
                        endFlag = true;
                        Blackjack.showCards(playerHand, dealerHand, 1);
                        dealerScore++;
                        System.out.printf("КУДАААААА Счет %d:%d\n",
                                playerScore, dealerScore);
                        break;
                    } else {
                        System.out.printf("Вы открыли карту %s\n",
                                playerHand.hand.get(dealerHand.hand.size() - 1));
                        showCards(playerHand, dealerHand, 0);
                    }
                }
            }
            if (endFlag) {
                continue;
            }
            System.out.println("\nХод дилера");
            System.out.println("--------");
            System.out.printf("Дилер открывает закрытую карту %s\n",
                    dealerHand.hand.get(dealerHand.hand.size() - 1));
            showCards(playerHand, dealerHand, 1);

            if (dealerHand.score == 21) {
                dealerScore++;
                System.out.printf("ПРОИГРАЛ Счет %d:%d\n", playerScore, dealerScore);
                continue;
            }

            if (dealerHand.dealerGameplay(playerHand, dealerHand, playdeck) == 0) {
                if (playerHand.score > dealerHand.score) {
                    playerScore++;
                    System.out.printf("\nУРАААААА ПОБЕДА ПОБЕДА!!!!!! Счет %d:%d\n",
                            playerScore, dealerScore);
                } else if (playerHand.score == dealerHand.score) {
                    playerScore++;
                    dealerScore++;
                    System.out.printf("АХАХААХАХАХАХАХ НИЧЬЯ! Счет %d:%d\n", playerScore, dealerScore);
                } else {
                    dealerScore++;
                    System.out.printf("ПРОИГРАЛ Счет %d:%d\n", playerScore, dealerScore);
                }
            } else if (dealerHand.dealerGameplay(playerHand, dealerHand, playdeck) == 1) {
                dealerScore++;
                System.out.printf("ПРОИГРАЛ Счет %d:%d\n", playerScore, dealerScore);
            } else {
                playerScore++;
                System.out.printf("\nУРАААААА ПОБЕДА ПОБЕДА!!!!!! Счет %d:%d\n",
                        playerScore, dealerScore);
            }
        }

    }

    /**
     * It shows cards and score of each player.
     *
     * @param player player's hand
     * @param dealer dealer's hand
     * @param flag   for closed dealer's hand
     */
    public static void showCards(Player player, Dealer dealer, int flag) {
        if (flag == 0) {

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

