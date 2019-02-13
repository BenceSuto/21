import java.util.Random;

enum Cards {Ace(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10), Jack(10), Queen(10), King(10);

    public final int value;

    Cards (int value){
        this.value = value;
    }
    public Cards chooseRank() {
        Cards[] ranks = values();
        return ranks[(int) (Math.random() * ranks.length)];
    }

    public int getValue() {
        return value;
    }
}

public class Deck {

    private static Cards [] cards = null;

    static {
        cards = new Cards[13];
        int i = 0;
        for (Cards card: Cards.values())
            cards[i++] = card;
    }

    public static void main (String [] args){

        int result = 0;
        Random generator = new Random();
        Cards card = cards[generator.nextInt(cards.length)];

        if (card.equals(Cards.Ace)){}
        else result = card.value;
        System.out.println(card + " = " + result);
    }
}