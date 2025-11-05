import java.util.*;
/**
 * Models the Stock Pile in a game of Skip-Bo.
 * This is the pile that every player wants to empty.
 * 
 * @author SebLocal
 * @version 3-11-2025
 */
public class StockPile
{
    private Stack<Card> pile;
    /**
     * Constructor: Constructs a StockPile with a given number of cards drawn from a given deck.
     * @param deck Current deck in play
     * @param numberOfCards Number of cards for stockpiles in this given game.
     */
    public StockPile(Deck deck, int numberOfCards)
    {
        pile = new Stack<>();
        for(int i = 0; i < numberOfCards; i++){
            pile.push(deck.drawCard());
        }
        
    }

    /**
     * Returns the top card in the StockPile
     */
    public Card viewTop()
    {
        return pile.peek(); //views the top card
    }
    
    /**
     * Returns the top card in the StockPile and removes it
     */
    public Card playCard(){
        if(pile.isEmpty()){
            throw new IllegalStateException("Cannot play from an empty StockPile!");
        }
        return pile.pop();
    }
    
    /**
     * Checks if the StockPile is empty
     */
    public boolean isEmpty(){
        return pile.isEmpty();
    }
    
    /**
     * Returns the size of the StockPile
     */
    public int getSize(){
        return pile.size();
    }
}