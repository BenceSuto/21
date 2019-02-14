import java.util.Arrays;
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
        //terminal.moveTo(10, 20); --> before "enter your name"
        System.out.println("Enter your name: ");
        //terminal.moveTo(11, 20); --> after "enter your name"
        player = input.nextLine();
        //terminal.clearScreen(); --> after player = input.nextLine()
        Deck deck = new Deck();

        //game continues while player has money
        while (playerMoney >= 0) {
            //player places a bet
            //terminal.moveTo(10, 20); --> after while (playerMoney > 0)
            System.out.println("You have " + playerMoney + " coins.");
            //terminal.moveTo(11, 20); --> before "How much money would you like to bet."
            System.out.println("How much money would you like to bet?");
            //terminal.moveTo(12, 20); --> after "How much money would you like to bet."
            playerBet = playerMove.nextInt();
            //terminal.clearScreen(); --> after playerBet = playerMove.nextInt()

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
                //terminal.moveTo(60, 20); --> before System.out.print("Player: " + playerScore); + also change println to print
                System.out.println("Player: " + playerCards[0] + ", " + playerCards[1]);
                //terminal.moveTo(60, 170); --> before System.out.print("Dealer: " + dealerScore) + also change println to print
                System.out.println("Dealer: " + dealerCards[0] + ", [hidden]");
                //print out scores
                System.out.println("Player: " + playerScore);

                while (dealerScore <= 21 && playerScore <= 21) {
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
                            System.out.println("Dealer: " + dealerCardsSecond[0] + ", [hidden], [hidden]");
                        } else {
                            Cards[] dealerCardsSecond = dealerCards;
                            //print out cards
                            System.out.println("Dealer: " + dealerCardsSecond[0] + ", [hidden]");
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
                //terminal.moveTo(10, 20); --> before "You can't bet more than you have."
                System.out.println("You can't bet more than you have.");
                continue;
            }

        }
        //player has no more money
        //terminal.moveTo(10, 20); --> before "Sorry, you have no more money."
        System.out.println("Sorry, you have no more money.");
        playerMove.close();
        //end of game

    }
}