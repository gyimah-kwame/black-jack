package models;

public class Player {

    private static int numberOfPlayers = 1;

    private int id;
    private String name;

    public Player(String name) {
        id =  numberOfPlayers++;
        this.name = name;
    }

    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public String getName() {
        return name;
    }
}
