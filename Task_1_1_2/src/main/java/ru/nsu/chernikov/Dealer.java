package ru.nsu.chernikov;

/** THE DEALER.
 *
 */
public class Dealer extends Player{

    /** just subclass of player.
     *
     */
    public Dealer(){
        super();

    }

    /** DEALER BOT.
     *
     * @param player just player's hand
     * @param dealer just dealer's hand
     * @return result of dealer's turn
     */
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