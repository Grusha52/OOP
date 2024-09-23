package ru.nsu.chernikov;

/** Rank of the card.
 *
 */
public enum Rank{
    ACE(11),
    JACK(10),
    QUEEN(10),
    KING(10),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10);

    final int score;

    /** SCORE.
     *
     * @param score the value of the card
     */
    Rank(int score) {
        this.score = score;
    }

}