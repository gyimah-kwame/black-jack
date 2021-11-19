package models;

import constants.Rank;
import constants.Suit;

import java.util.*;

public class Deck {

    private final Stack<Card> shuffledCards;

    public Deck() {
        shuffledCards = new Stack<>();

        storeCard(Suit.CLUBS);
        storeCard(Suit.SPADES);
        storeCard(Suit.DIAMONDS);
        storeCard(Suit.HEARTS);
    }


    /**
     * add a card to unshuffled list
     * @param suit suit
     */
    private void storeCard(Suit suit) {
        for (Rank rank : Rank.values()) {
            Card card = new Card(suit, rank);
            shuffledCards.push(card);
        }
    }

    /**
     * shuffle cards
     */
    public void shuffle() {
        Collections.shuffle(shuffledCards);
    }

    public Stack<Card> getShuffledCards() {
        return shuffledCards;
    }

}
