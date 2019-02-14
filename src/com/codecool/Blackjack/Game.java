import java.util.Arrays;
import java.util.Scanner;
import com.codecool.termlib.*;

public class Game {
    public void main() {

        Terminal terminal = new Terminal();

        Scanner input = new Scanner(System.in);
        int playerScore = 0;
        int dealerScore = 0;
        int playerMoney = 100;
        int playerBet = 0;
        String player;
        Scanner playerMove = new Scanner(System.in);
        terminal.moveTo(5, 10);
        System.out.println("Enter your name: ");
        terminal.moveTo(6, 10);
        player = input.nextLine();
        terminal.clearScreen();
        Deck deck = new Deck();

        //game continues while player has money
        while (playerMoney >= 0) {
            //player places a bet
            terminal.moveTo(10, 20);
            System.out.println("You have " + playerMoney + " coins.");
            terminal.moveTo(11, 20);
            System.out.println("How much money would you like to bet?");
            terminal.moveTo(12, 20);
            playerBet = playerMove.nextInt();
            terminal.clearScreen();

            if (playerBet <= playerMoney) {
                playerMoney = playerMoney - playerBet;
               	/*
                player get cards
		dealer get cards
                 */
                Cards[] dealerCards = new Cards[2];
                Cards[] playerCards = new Cards[2];
                for (int i = 0; i < playerCards.length; i++) {
                    playerCards[i] = deck.getCard();
                    playerScore += playerCards[i].value;

                }
                for (int i = 0; i < dealerCards.length; i++) {
                    dealerCards[i] = deck.getCard();
                    dealerScore += dealerCards[i].value;
                }
                //print out cards
                terminal.moveTo(30, 10);
                System.out.print("Player: " + playerCards[0] + ", " + playerCards[1]);
                terminal.moveTo(30, 100);
                System.out.print("Dealer: " + dealerCards[0] + ", [hidden]");
                //print out scores
                terminal.moveTo(31, 10);
                System.out.println("Player: " + playerScore);

                while (dealerScore <= 21 && playerScore <= 21) {
                    terminal.moveTo(20, 10);
                    System.out.println("What's your next move? ('h' for hit, 's' for stand)");
                    char nextMove = playerMove.next().charAt(0);
                    if (nextMove == 'h') {
                        Cards[] playerCardsSecond = Arrays.copyOf(playerCards, 3);
                        playerCardsSecond[2] = deck.getCard();
                        playerScore += playerCardsSecond[2].value;
                        //print out cards
                        System.out.println("Player: " + playerCardsSecond[0] + ", " + playerCardsSecond[1] + ", " + playerCardsSecond[2]);
                        if (dealerScore <= 16) {
                            Cards[] dealerCardsSecond = Arrays.copyOf(dealerCards, 3);
                            dealerCardsSecond[2] = deck.getCard();
                            dealerScore += dealerCardsSecond[2].value;
                            //print out cards
                            System.out.println("Dealer: " + dealerCardsSecond[0] + ", " + dealerCardsSecond[1] + ", " + dealerCardsSecond[2]);
                        } else {
                            Cards[] dealerCardsSecond = dealerCards;
                            //print out cards
                            System.out.println("Dealer: " + dealerCardsSecond[0] + ", " + dealerCardsSecond[1]);
                        }
                        //print out scores
                        System.out.println("Player: " + playerScore);
                    } else if (nextMove == 's') {
                        break;
                    }
                    if (dealerScore >= 21 || playerScore >= 21) {
                        if (dealerScore - 21 < playerScore - 21) {
                            System.out.println("Game over");
                        } else {
                            System.out.println("Congratulations!");
                        }
                        System.out.println("Would you like to play again? (y/n)");
                        char playAgain = playerMove.next().charAt(0);
                        if (playAgain == 'y') {
			    playerScore = 0;
        		    dealerScore = 0;
                            break;
                        } else {
                            System.exit(0);
                        }
                    }
                }

            } else {
                terminal.moveTo(10, 20);
                System.out.println("You can't bet more than you have.");
                continue;
            }

        }
        //player has no more money
        terminal.moveTo(10, 20);
        System.out.println("Sorry, you have no more money.");
        playerMove.close();


    }
}