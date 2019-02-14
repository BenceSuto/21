import java.util.Arrays;
import java.util.Scanner;

import com.codecool.termlib.*;

import javax.smartcardio.Card;

public class Game {

    private  Terminal terminal = new Terminal();
    private Deck deck = new Deck();

    private String player;
    private int playerScore = 0;
    private int dealerScore = 0;
    private int playerMoney = 100;

    private Cards[] dealerCards = new Cards[2];
    private Cards[] playerCards = new Cards[2];

    public void main() {

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

    private char getNextMove(Scanner playerMove) {
        terminal.moveTo(20, 10);
        System.out.println("What's your next move? ('h' for hit, 's' for stand)");
        terminal.moveTo(21, 10);
        char nextMove = playerMove.next().charAt(0);
        terminal.clearScreen();
        return nextMove;
    }

    private void dealerStands() {
        Cards[] dealerCardsSecond = dealerCards;
        //print out dealer cards
        terminal.moveTo(30, 90);
        System.out.println("Dealer: " + dealerCardsSecond[0] + ", " + dealerCardsSecond[1]);
        //print out scores
        terminal.moveTo(31, 90);
        System.out.println("Dealer: " + dealerScore);
    }

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
        terminal.moveTo(30, 10);
        System.out.println(player + ": " + Arrays.toString(playerCards).replace("[", "").replace("]", ""));
        terminal.moveTo(31, 10);
        System.out.println(player + ": " + playerScore);
    }

    private void playerHits() {
        Cards[] playerCardsNext = Arrays.copyOf(playerCards, playerCards.length + 1);
        Cards newCard = deck.getCard();
        playerCardsNext[playerCards.length] = newCard;
        playerScore += newCard.value;
        //print out cards
        playerCards = playerCardsNext;
        terminal.moveTo(30, 10);
        System.out.println(player + ": " + Arrays.toString(playerCards).replace("[", "").replace("]", ""));
        terminal.moveTo(31, 10);
        System.out.println(player + ": " + playerScore);
    }

    private void itIsBlackJack(int playerBet) {
        int prize = playerBet + (playerBet / 2);
        terminal.moveTo(10, 10);
        System.out.println("It's BlackJack! You won " + playerBet/2 + " coins!");
        playerMoney += prize;
    }

    private void printState() {
        terminal.moveTo(30, 10);
        System.out.print(player + ": " + Arrays.toString(playerCards).replace("[", "").replace("]", ""));
        terminal.moveTo(30, 90);
        System.out.print("Dealer: " + dealerCards[0] + ", [hidden]");
        //print out scores
        terminal.moveTo(31, 10);
        System.out.println(player + ": " + playerScore);
    }

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

    private int getScore(int score, Cards[] cards) {
        for (int i = 0; i < cards.length; i++) {
            cards[i] = deck.getCard();
            score += cards[i].value;
        }
        return score;
    }
}
