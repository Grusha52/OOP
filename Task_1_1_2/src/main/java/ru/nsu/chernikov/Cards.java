package ru.nsu.chernikov;


public class Cards {

    Rank cardRank;
    Suit cardSuit;
    int value;

    public Cards(Rank rank, Suit suit){
        this.cardRank = rank;
        this.cardSuit = suit;
        this.value = cardRank.score;
    }


    String getSuit(){
        return switch (cardSuit) {
            case DIAMONDS -> "Буби";
            case HEARTS -> "Черви";
            case SPADES -> "Пики";
            case CLUBS -> "Крести";

        };
    }

    String getRank(){
        return switch (cardRank){
            case ACE -> "Туз";
            case JACK -> "Валет";
            case QUEEN -> "Дама";
            case KING -> "Король";
            case TWO -> "Два";
            case THREE -> "Три";
            case FOUR -> "Четыре";
            case FIVE -> "Пять";
            case SIX -> "Шесть";
            case SEVEN -> "Семь";
            case EIGHT -> "Восемь";
            case NINE -> "Девять";
            case TEN -> "Десять";

        };
    }
    @Override
    public String toString() {
        return getRank() + " " + getSuit() + " " + "(" + value + ")";
    }
}