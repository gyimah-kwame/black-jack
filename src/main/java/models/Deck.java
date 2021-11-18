package models;

import constants.Rank;
import constants.Suit;

import java.util.*;

public class Deck {

    private List<Card> unShuffledCards;
    private Stack<Card> shuffledCards;
    private Map<Card, Integer> selectedCards;

    public Deck() {
        unShuffledCards = new ArrayList<>();
        shuffledCards = new Stack<>();
        selectedCards = new HashMap<>();

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
            unShuffledCards.add(card);
        }
    }

    /**
     * shuffle cards
     */
    public void shuffle() {
        int counter = 0;

        Random random = new Random();

        while (counter < 52) {
            int index = random.nextInt(52);
            Card card = unShuffledCards.get(index);

            if (!selectedCards.containsKey(card)) {
                shuffledCards.push(card);
                selectedCards.put(card, index);
                counter++;
            }

        }
    }

    public List<Card> getUnShuffledCards() {
        return unShuffledCards;
    }

    public Stack<Card> getShuffledCards() {
        return shuffledCards;
    }
}
