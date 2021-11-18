package models;

import constants.Rank;
import constants.Suit;

import java.util.*;

public class Deck {

    private Stack<Card> shuffledCards;

    public Deck() {
        shuffledCards = new Stack<>();

        storeCard(Suit.CLUBS);
        storeCard(Suit.SPADES);
        storeCard(Suit.DIAMONDS);
        storeCard(Suit.HEARTS);
    }


    /**
     * create card
     *
     * @param rank Rank of card
     * @param suit suit of card
     * @return Card
     */
    private Card createCard(Rank rank, Suit suit){
        return new Card(suit, rank);
    }

    /**
     * add a card to unshuffled list
     * @param suit suit
     */
    private void storeCard(Suit suit) {
        for (Rank rank : Rank.values()) {
            Card card = createCard(rank, suit);
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
