import java.util.*;
/**
 * Models one of the four DiscardPiles that a player has in a game of Skip-bo!
 * 
 * @author SebLocal
 * @version 3-11-2025
 */
public class DiscardPile
{
    private Stack<Card> pile;

    /**
     * Constructor: Constructs an empty DiscardPile
     */
    public DiscardPile()
    {
        pile = new Stack<>();
    }

    /**
     * Discards a given card
     * @param  c Card to discard
     */
    public void discardCard(Card c)
    {
        pile.push(c);
    }

    /**
     * Views the top card
     * @return Returns the top card
     */
    public Card viewTop(){
        if(pile.isEmpty()){
            throw new IllegalStateException("Cannot view top of empty DiscardPile!");
        }
        return pile.peek();
    }

    /**
     * Plays the top card
     * @return Returns the played card
     */
    public Card playCard(){
        if(pile.isEmpty()){
            throw new IllegalStateException("Cannot play from empty DiscardPile!");
        }
        return pile.pop();
    }

    /**
     * Checks if pile is empty
     * return Returns true if pile is empty, returns false otherwise
     */
    public boolean isEmpty(){
        return pile.isEmpty();
    }
    
    /**
     * Getter method for DiscardPile size
     * @return Returns pile size
     */
    public int getSize(){
        return pile.size();
    }

    /**
     * toString method for DiscardPile objects
     * @return Returns converted string
     */
    public String toString(){
        if(pile.isEmpty()) return "DiscardPile: [empty]";
        return "DiscardPile top: " + pile.peek() + " (" + pile.size() + " cards)";
    }
}