public class Game {
	public static void main (String[] args) {
		public enum deckOfCards = 
		Player player1 = new Player;
		Player player2 = new Player;
		int currentScorePlayer1 = 0;
		int currentScorePlayer2 = 0;
		int remainingDeck = 52;

		public String nextCard(int remainingDeck) {
			/*
			get random card from remaining deck
			return remainingDeck-1
			*/
		}

		public int countScore(String player) {
			/*
			get value of cards in hand
			add values of new card
			calculate score
			*/
		}

		public String checkWinner() {
			/*
			compare scores			
			if active player is winner false --> continue
			else return winner + message			
			*/
		}

		public void game(player1, player2) {
			/*
		DO 
			player1 moves --> active player
			get move
			count score
			check winner
			end turn --> reset display
			player2 moves --> active player
			get move
			count score
			check winner
			end turn --> reset display
		WHILE checkWinner IS FALSE 
			*/		
		}
	}
}

/*
reset display: name1, name2, remaining deck, number of cards 1, number of cards 2
*/

/*import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String player1, player2;
        System.out.println("Enter player1 name: ");
        player1 = input.nextLine();
        System.out.println("Enter player2 name: ");
        player2 = input.nextLine();

        System.out.println(player1);
        System.out.println(player2);
    }
}*/