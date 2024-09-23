package ru.nsu.chernikov;

/**
 * Cards class.
 */
public class Cards {

    Rank cardRank;
    Suit cardSuit;
    int value;

    /**
     * Cards constructor.
     *
     * @param rank the value of card
     * @param suit the suit of card
     */
    public Cards(Rank rank, Suit suit) {
        this.cardRank = rank;
        this.cardSuit = suit;
        this.value = cardRank.score;
    }

    /**
     * returns suit of card.
     *
     * @return suit
     */
    String getSuit() {
        switch (cardSuit) {
            case DIAMONDS:
                return "Буби";
            case HEARTS:
                return "Черви";
            case SPADES:
                return "Пики";
            case CLUBS:
                return "Крести";
            default:
                return "Just nothing";

        }
    }

    /**
     * returns rank of card.
     *
     * @return rank
     */
    String getRank() {
        switch (cardRank) {
            case ACE:
                return "Туз";
            case JACK:
                return "Валет";
            case QUEEN:
                return "Дама";
            case KING:
                return "Король";
            case TWO:
                return "Два";
            case THREE:
                return "Три";
            case FOUR:
                return "Четыре";
            case FIVE:
                return "Пять";
            case SIX:
                return "Шесть";
            case SEVEN:
                return "Семь";
            case EIGHT:
                return "Восемь";
            case NINE:
                return "Девять";
            case TEN:
                return "Десять";
            default:
                return "Just nothing";
        }
    }

    /**
     * Override for printing.
     *
     * @return string format of card with the value
     */
    @Override
    public String toString() {
        return getRank() + " " + getSuit() + " " + "(" + value + ")";
    }
}