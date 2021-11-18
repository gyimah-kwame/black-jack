import constants.Rank;
import constants.Suit;
import models.Card;
import models.Deck;
import models.Player;

import java.util.*;
import java.util.stream.Collectors;

public class BlackJack {

    private Map<Player, Integer> scores;

    private List<Player> players;
    private Map<Player, List<Card>> playerCards;
    private Deck deck;


    public BlackJack(Deck deck) {
        this.deck = deck;
        scores = new HashMap<>();
        players = new ArrayList<>();
        playerCards = new HashMap<>();

    }


    public void createPlayers(int numberOfPlayers) {
        for (int i=0; i<numberOfPlayers; i++) {
            players.add(new Player("Player "+(i+1)));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void assignCardsToAllPlayers() {
        for (Player player: players) {
            assignCardsToPlayer(player, 2);
        }
    }

    private void assignCardsToPlayer(Player player, int numberOfCards) {
        List<Card> cards = new ArrayList<>();

        for (int i=1; i<= numberOfCards; i++) {
            cards.add(deck.getShuffledCards().pop());
        }

        playerCards.put(player, cards);
    }


}



