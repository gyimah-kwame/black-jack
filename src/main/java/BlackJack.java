import constants.Rank;
import constants.Suit;
import models.Card;
import models.Deck;
import models.Player;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        List<Card> cards = playerCards.get(player) == null ? new ArrayList<>() :  playerCards.get(player);

        for (int i=1; i<= numberOfCards; i++) {
            cards.add(deck.getShuffledCards().pop());
        }

        playerCards.put(player, cards);
    }

    public int getValueOfCards(Player player) {
       return playerCards.get(player).stream().map(x -> x.getRank().getValue()).reduce(0, (a,b) -> a+b);
    }


    public void hit(Player player) {
        assignCardsToPlayer(player, 1);
    }

    public void displayPlayerCards() {
        for (Map.Entry<Player,List<Card>> items: playerCards.entrySet()) {
            System.out.println(items.getKey().getName() + " cards => "+items.getValue().stream().map(x -> format(x)).collect(Collectors.toList()));
        }
    }

    private String format(Card card) {
        return card.getRank() + " of " + card.getSuit();
    }

    public boolean simulate() {

        boolean ended = false;

        Scanner scanner = new Scanner(System.in);

        while (!ended) {

            for (Player player : players) {

                System.out.println(player.getName() + "'s turn: ");
                System.out.println("\nChoose either 1 or 2 to continue.\n1. hit\n2. stick");
            }

        }

        return true;
    }


}



