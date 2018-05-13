import java.util.Scanner;
import java.util.Random;

public class GameOfCraps {


    public static void main(String[] args) {

        int wager;
        int human_winnings = 0;
        int computer_winnings = 0;
        int num_games_to_play;

        final int MAX_WAGER = 100;
        Dice dice;

        num_games_to_play = promptForNumGames();
        for(int i = 1; i <= num_games_to_play; i++){

            // HUMAN'S ROUND
            wager = getHumansWager(MAX_WAGER);
            dice = new Dice();
            System.out.println("\nPlayer is now rolling.....\n");
            playRound(dice);

            if(dice.getState() instanceof Win)
                human_winnings = human_winnings + wager;
            else
                human_winnings = human_winnings - wager;

            // COMPUTER'S ROUND
            wager = randomly_generate(MAX_WAGER);
            System.out.println("\nComputer wages $" + wager);
            dice = new Dice();
            System.out.println("Computer is now rolling......\n");
            playRound(dice);

            if(dice.getState() instanceof Win)
                computer_winnings = computer_winnings + wager;
            else
                computer_winnings = computer_winnings - wager;

            System.out.println("You have $" + human_winnings);
            System.out.println("Computer has $" + computer_winnings);

        }
        endGame(human_winnings,computer_winnings);
    }

    public static int promptForNumGames(){
        Scanner console = new Scanner(System.in);
        System.out.println("How many games would you like to play?");
        System.out.println(">>");
        return console.nextInt();

    }

    public static int getHumansWager(double max_wager){
        Scanner console = new Scanner(System.in);
        System.out.println("Place a wager between $1 and $" + max_wager);
        System.out.println(">>");
        return console.nextInt();
    }
    // prompts for and returns wager between 1 and max_wager dollars

    public static int randomly_generate(int max){
        Random r = new Random();
        return r.nextInt(max - 1) + 1;
    }
    // generates random integer between 1 and max

    public static void playRound(Dice dice){
        // play until win or loss occurs
        while(!(dice.getState() instanceof Win) &&
                !(dice.getState() instanceof Loss))
            dice.rollDice();
    }

    public static void endGame(int humanWinnings, int compWinnings){
        if(humanWinnings < 0 && compWinnings < 0)
            System.out.println("Were both broke...good thing computers don't need money");
        else if(humanWinnings > compWinnings)
            System.out.println("You have beaten the machine");
        else
            System.out.println("SkyNet activated....");

    }


}