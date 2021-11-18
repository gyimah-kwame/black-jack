import constants.Suit;

public class Main {

    public static void main(String[] args) {

       try {

           int numberOfPlayers = args.length == 1 ? Integer.parseInt(args[0]): 3;

           if (numberOfPlayers <= 1 || numberOfPlayers > 6) {
               System.out.println("sorry you need a minimum of 2 players or maximum of 6 players to play this game");
               return;
           }

           BlackJack blackJack = new BlackJack();
           blackJack.shuffle();

           blackJack.createPlayers(numberOfPlayers);

           System.out.println(blackJack.getPlayers());

       }catch (NumberFormatException e) {

           System.out.println("Invalid argument passed, please provide a number.");
       }

    }
}
