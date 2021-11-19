
import models.Card;
import models.Deck;
import models.Player;

import java.util.*;
import java.util.stream.Collectors;

public class BlackJack {

    private final Map<Player, Integer> scores;

    private final List<Player> players;
    private final Map<Player, List<Card>> playerCards;
    private final Deck deck;
    private final List<Player> eliminatedPlayers;
    private final Map<Player, Integer> options;
    private  boolean ended = false;


    public BlackJack(Deck deck) {
        this.deck = deck;
        scores = new HashMap<>();
        players = new ArrayList<>();
        playerCards = new HashMap<>();
        eliminatedPlayers = new ArrayList<>();
        options = new HashMap<>();

    }


    public void createPlayers(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player("Player " + (i + 1)));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void assignCardsToAllPlayers() {
        for (Player player : players) {
            assignCardsToPlayer(player, 2);
        }
    }

    private void assignCardsToPlayer(Player player, int numberOfCards) {

        List<Card> cards = playerCards.get(player) == null ? new ArrayList<>() : playerCards.get(player);

        for (int i = 1; i <= numberOfCards; i++) {
            cards.add(deck.getShuffledCards().pop());
        }

        playerCards.put(player, cards);
    }

    public int getValueOfCards(Player player) {
        return playerCards.get(player).stream().map(x -> x.getRank().getValue()).reduce(0, (a, b) -> a + b);
    }



    public void displayPlayerCards(Player player) {
        System.out.println(player.getName() + " cards => " + playerCards.get(player).stream().map(this::format).collect(Collectors.toList()));
    }

    private String format(Card card) {
        return card.getRank() + " of " + card.getSuit();
    }


    private void decide() {

        int highest = 0;

        long stickCount = options.values().stream().filter(x -> x == 2).count();

        if (stickCount == players.size()) {
            Player player = null;

            for (Map.Entry<Player, Integer> item: scores.entrySet()) {
                int score = getValueOfCards(item.getKey());

                if (score > highest && score < 22) {
                    highest = score;
                    player = item.getKey();
                }
            }

            if (player != null) {
                System.out.println(player.getName() + " won");
            }
            ended = true;

            return;
        }

        // check if there is a winner

        Optional<Player> winner = playerCards.keySet().stream().filter(player -> getValueOfCards(player) == 21).findFirst();

        if (winner.isPresent()) {
            System.out.println(winner.get().getName() + " won");

            ended = true;
            return;
        }


        //eliminate players that do not qualify for the next round
        for (Map.Entry<Player, Integer> item: scores.entrySet()) {
            int score = getValueOfCards(item.getKey());

            if (score > 21) {
                eliminatedPlayers.add(item.getKey());
                System.out.println(item.getKey().getName() + " is out of the game\n");
            }

        }

    }

    private void ejectPlayers() {

        for (Player player : eliminatedPlayers) {
            scores.remove(player);
            playerCards.remove(player);
        }

        players.removeAll(eliminatedPlayers);

        //end game if number of remaining players is one
        if (players.size() == 1) {
            System.out.println(players.get(0).getName()+ " won");
            ended = true;

        }

        if (players.size() == 0) {
            System.out.println("No winner thank you");
            ended = true;
        }
    }


    public void simulate() {
        while (!ended) {

            int playersSize = players.size();

            System.out.println("simulating...");

            for (int i=0; i<playersSize; i++) {

                Player player = players.get(i);

                displayPlayerCards(player);

                long value = getValueOfCards(player);

                if (value < 17) {
                    options.put(player, 1);
                    assignCardsToPlayer(player, 1);
                }else {
                    options.put(player, 2);
                }

                displayPlayerCards(player);
                System.out.println();

                scores.put(player, getValueOfCards(player));

                if (i == playersSize-1) {

                    decide();

                    scores.clear();
                }

            }

            ejectPlayers();

        }
    }


}



