import java.util.*;

/**
 * Models the big deck of all 162 Skip-Bo cards.
 * Includes 18 wild cards and 12 sets of regular cards 1-12.
 * 
 * @author SebLocal
 * @version 03-11-2025
 */
public class Deck
{
    // instansvariabler - erstat eksemplet herunder med dine egne variabler
    private ArrayList<Card> deck;

    /**
     * Constructor: Constructs the deck with all the cards.
     */
    public Deck()
    {
        deck = new ArrayList<>();
        
        //Adds 18 wild cards
        for(int i = 0; i < 18; i++){
            deck.add(new Card(0));
        }
        //Add 12 cards of each value 1-12
        for(int j = 1; j <= 12; j++){
            for(int k = 1; k <= 12; k++){
                deck.add(new Card(j));
            }
        }
    }
    
    /**
     * Shuffles the current deck.
     */
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }
    
    /**
     * Draws the top card from the Deck.
     */
    public Card drawCard(){
        if(deck.isEmpty()){
            throw new IllegalStateException("Cannot draw from an empty deck!");
        }
        return deck.remove(deck.size() - 1);
    }
    
    /**
     * Adds cards to the deck.
     * @param cards Collection of cards to add to the deck.
     */
    public void addCards(Collection<Card> cards){
        deck.addAll(cards);
    }
    
    /**
     * Checks if deck is empty.
     */
    public boolean isEmpty(){
        return deck.isEmpty();
    }
    
    /**
     * Returns the size of the deck
     */
    public int getSize(){
        return deck.size();
    }
}