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
        switch (cardSuit) {
            case DIAMONDS: return "Буби";
            case HEARTS: return "Черви";
            case SPADES: return "Пики";
            case CLUBS: return "Крести";
            default: return "Just nothing";

        }
    }

    String getRank(){
        switch (cardRank){
            case ACE: return  "Туз";
            case JACK: return "Валет";
            case QUEEN: return "Дама";
            case KING: return "Король";
            case TWO: return "Два";
            case THREE: return "Три";
            case FOUR: return "Четыре";
            case FIVE: return "Пять";
            case SIX: return "Шесть";
            case SEVEN: return "Семь";
            case EIGHT: return "Восемь";
            case NINE: return "Девять";
            case TEN: return "Десять";
            default: return "Just nothing";
        }
    }
    @Override
    public String toString() {
        return getRank() + " " + getSuit() + " " + "(" + value + ")";
    }
}