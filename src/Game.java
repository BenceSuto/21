public class Game {
	public static void main (String[] args) {
		public enum deckOfCards = 
		Player player1 = new Player
		Player player2 = new Player

		public String nextCard() {
			/*
			get random card from remaining deck
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
			player1 moves --> active player
			get move
			count score
			check winner
			player2 moves --> active player
			get move
			count score
			check winner
			*/		
		}
	}
}

//import java.util.Scanner;
//
//public class Game {
//    public static void main(String[] args) {
//
//        Scanner input = new Scanner(System.in);
//
//        String player1, player2;
//        System.out.println("Enter player1 name: ");
//        player1 = input.nextLine();
//        System.out.println("Enter player2 name: ");
//        player2 = input.nextLine();
//
//        System.out.println(player1);
//        System.out.println(player2);
//    }
//}