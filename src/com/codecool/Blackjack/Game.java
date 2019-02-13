
import java.util.Scanner;

public class Game {
    public void main() {

        Scanner input = new Scanner(System.in);
        int playerScore = 0;
        int dealerScore = 0;
        int playerMoney = 100;
        int playerBet = 0;
        String player;
        Scanner playerMove = new Scanner(System.in);
        System.out.println("Enter your name: ");
        player = input.nextLine();


        while (playerMoney > 0) {

            System.out.println("How much money would you like to bet.");
            playerBet = playerMove.nextInt();
            if (playerBet <= playerMoney) {
                /*
                dealer get cards
                player get cards
                 */
                System.out.println("Dealer: " + dealerScore);
                System.out.println("Player: " + playerScore);
                while (dealerScore <= 21 && playerScore <= 21) {

                }
            } else {
                System.out.println("You can't bet more than you have.");
            }

            /*
            GAME
             */
        }
        System.out.println("Sorry, you have no more money.");
        playerMove.close();


    }
}