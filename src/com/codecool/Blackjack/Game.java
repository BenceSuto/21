import java.util.Arrays;
import java.util.Scanner;

import com.codecool.termlib.*;

public class Game {

    private Terminal terminal = new Terminal();
    private Deck deck = new Deck();

    private String player;
    private int playerScore = 0;
    private int dealerScore = 0;
    private int playerMoney = 100;

    private Cards[] dealerCards = new Cards[2];
    private Cards[] playerCards = new Cards[2];

    /**
     * starts the game
     */
    public void start() {

        Scanner input = new Scanner(System.in);
        int playerBet = 0;
        int prize = 0;
        Scanner playerMove = new Scanner(System.in);
        terminal.moveTo(5, 10);
        System.out.println("Enter your name: ");
        terminal.moveTo(6, 10);
        player = input.nextLine();
        terminal.clearScreen();

        //game continues while player has money
        while (playerMoney > 0) {

            playerBet = placeBet(playerMove);

            if (playerBet <= playerMoney) {
                playerMoney = playerMoney - playerBet;

                playerScore = getScore(playerScore, playerCards);
                dealerScore = getScore(dealerScore, dealerCards);

                printState();

                if (playerScore == 21 && dealerScore != 21) {
                    itIsBlackJack(playerBet);
                } else {
                    while (playerScore < 21) {
                        char nextMove = getNextMove(playerMove);
                        //player moves first
                        if (nextMove == 'h') {
                            playerHits();

                            //print out scores
                            terminal.moveTo(31, 10);
                            System.out.println(player + ": " + playerScore);
                            //print out dealer cards
                            terminal.moveTo(30, 90);
                            System.out.print("Dealer: " + dealerCards[0] + ", [hidden]");

                        } else if (nextMove == 's') {
                            break;
                        }
                    }

                    if (dealerScore <= 16) {
                        dealerHits();
                    } else {
                        dealerStands();
                    }

                    checkingPoints(playerBet);
                }
                terminal.moveTo(21, 10);
                System.out.println("Would you like to play again? (y/n)");
                terminal.moveTo(22, 10);
                char playAgain = playerMove.next().charAt(0);
                if (playAgain == 'y') {
                    playerScore = 0;
                    dealerScore = 0;
                    dealerCards = new Cards[2];
                    playerCards = new Cards[2];
                    terminal.clearScreen();
                } else {
                    terminal.clearScreen();
                    System.exit(0);
                }
            } else {
                terminal.moveTo(20, 10);
                System.out.println("You can't bet more than you have.");
            }
        }
        //player has no more money
        terminal.moveTo(20, 10);
        System.out.println("Sorry, you have no more money.");
        playerMove.close();
    }

    /**
     * checking the points of both player and dealer
     * @param playerBet
     */
    private void checkingPoints(int playerBet) {
        int prize;
        if (dealerScore < 21 && playerScore < 21) {
            if (dealerScore == playerScore) {
                terminal.moveTo(20, 10);
                System.out.println("It's a Push. You get back " + playerBet + " coins.");
                playerMoney += playerBet;
            } else if (21 - dealerScore < 21 - playerScore) {
                terminal.moveTo(20, 10);
                System.out.println("Dealer closer to 21. You lost your bet.");
            } else {
                prize = playerBet * 2;
                terminal.moveTo(20, 10);
                System.out.println("You won " + playerBet + " coins!");
                playerMoney += prize;
            }
        } else {
            //PUSH
            if (dealerScore == playerScore) {
                terminal.moveTo(20, 10);
                System.out.println("It's a Push. You get back " + playerBet + " coins.");
                playerMoney += playerBet;
            }
            //BUST
            else if (playerScore > 21 || dealerScore > 21) {
                //PLAYER BUST
                if (playerScore > 21) {
                    terminal.moveTo(20, 10);
                    System.out.println("You went over 21. Dealer won, You lost your bet.");
                }
                //DEALER BUST
                else {
                    prize = playerBet * 2;
                    terminal.moveTo(20, 10);
                    System.out.println("You won " + playerBet + " coins!");
                    playerMoney += prize;
                }
            }
            //PLAYER WINS
            else {
                prize = playerBet * 2;
                terminal.moveTo(20, 10);
                System.out.println("You won " + playerBet + " coins!");
                playerMoney += prize;
            }
        }
    }

    /**
     * getting the next move of the player (hit or stand)
     * @param playerMove
     * @return
     */
    private char getNextMove(Scanner playerMove) {
        terminal.moveTo(20, 10);
        System.out.println("What's your next move? ('h' for hit, 's' for stand)");
        terminal.moveTo(21, 10);
        char nextMove = playerMove.next().charAt(0);
        terminal.clearScreen();
        return nextMove;
    }

    /**
     * display the cards in hand and the scores in case the dealer stands
     */
    private void dealerStands() {
        Cards[] dealerCardsSecond = dealerCards;
        //print out dealer cards
        terminal.clearScreen();
        terminal.moveTo(30, 90);
        System.out.println("Dealer: " + dealerCardsSecond[0] + ", " + dealerCardsSecond[1]);
        //print out scores
        terminal.moveTo(31, 90);
        System.out.println("Dealer: " + dealerScore);
        diplayPlayerCardsAndScores();
    }

    /**
     * displays the cards in hand and the scores in case the dealer hits
     */
    private void dealerHits() {
        Cards[] dealerCardsSecond = Arrays.copyOf(dealerCards, 3);
        dealerCardsSecond[2] = deck.getCard();
        dealerScore += dealerCardsSecond[2].value;
        //print out dealer cards
        terminal.moveTo(30, 90);
        System.out.println("Dealer: " + dealerCardsSecond[0] + ", " + dealerCardsSecond[1] + ", " + dealerCardsSecond[2]);
        //print out scores
        terminal.moveTo(31, 90);
        System.out.println("Dealer: " + dealerScore);
        diplayPlayerCardsAndScores();
    }

    /**
     * counts score and display cards of player in case the player hits
     */
    private void playerHits() {
        Cards[] playerCardsNext = Arrays.copyOf(playerCards, playerCards.length + 1);
        Cards newCard = deck.getCard();
        playerCardsNext[playerCards.length] = newCard;
        playerScore += newCard.value;
        //print out cards
        playerCards = playerCardsNext;
        diplayPlayerCardsAndScores();
    }

    /**
     * displays the player's current cards and score
     */
    private void diplayPlayerCardsAndScores() {
        terminal.moveTo(30, 10);
        System.out.println(player + ": " + Arrays.toString(playerCards).replace("[", "").replace("]", ""));
        terminal.moveTo(31, 10);
        System.out.println(player + ": " + playerScore);
    }

    /**
     * handles the instant blackjack situation with calculating the player's prize
     * and adding the prize to the player's money
     * @param playerBet
     */
    private void itIsBlackJack(int playerBet) {
        int prize = playerBet + (playerBet / 2);
        terminal.moveTo(10, 10);
        System.out.println("It's BlackJack! You won " + playerBet / 2 + " coins!");
        playerMoney += prize;
    }

    /**
     * displays current cards and scores of the game
     */
    private void printState() {
        terminal.moveTo(30, 90);
        System.out.print("Dealer: " + dealerCards[0] + ", [hidden]");
        diplayPlayerCardsAndScores();
    }

    /**
     * handles the new bets of the player
     * @param playerMove
     * @return
     */
    private int placeBet(Scanner playerMove) {
        int playerBet;
        terminal.moveTo(10, 20);
        System.out.println("You have " + playerMoney + " coins.");
        terminal.moveTo(11, 20);
        System.out.println("How much money would you like to bet?");
        terminal.moveTo(12, 20);
        playerBet = playerMove.nextInt();
        terminal.clearScreen();
        return playerBet;
    }

    /**
     * calculates the score of the drown cards
     * @param score
     * @param cards
     * @return
     */
    private int getScore(int score, Cards[] cards) {
        for (int i = 0; i < cards.length; i++) {
            cards[i] = deck.getCard();
            score += cards[i].value;
        }
        return score;
    }
}
