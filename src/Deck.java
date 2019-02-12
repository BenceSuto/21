import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
 
public class Deck
{
  private ArrayList deck;
 
  public Deck ()
  {
    this.deck = new ArrayList();
    for (int i=0; i&lt;13; i++)
    {
      CardValue value = CardValue.values()[i];
 
      for (int j=0; j&lt;4; j++)
      {
        Card card = new Card(value, Suit.values()[j]);
        this.deck.add(card);
      }
    }
 
    Collections.shuffle(deck);
 
    Iterator cardIterator = deck.iterator();
    while (cardIterator.hasNext())
    {
      Card aCard = cardIterator.next();
      System.out.println(aCard.getCardValue() + " of " + aCard.getSuit());
    }
  }
}