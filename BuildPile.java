import java.util.*;
/**
 * Models one of the central build piles in a Skip-Bo game.
 * 
 * @author SebLocal
 * @version 03-11-2025
 */
public class BuildPile
{
    private Stack<Card> pile;

    /**
     * Constructor: Creates an empty build pile.
     */
    public BuildPile()
    {
        pile = new Stack<>();
    }

    /**
     *Returns the next number needed on this pile.
     *If the pile is empty, it needs a 1
     */
    public int getNextValue()
    {
        if(pile.isEmpty()) return 1;
        return pile.size() + 1;
    }

    /**
     * Checks wheter the given card can legally be played here.
     * Skip-Bo is always allowed.
     * @param c Card to check if playable
     */
    public boolean canPlay(Card c){
        int next = getNextValue();
        return c.getValue() == 0 || c.getValue() == next;
    }

    /**
     * Plays a card onto the pile if it fits.
     * Automatically resets the pile when it reaches 12 cards.
     * @param c Card to play
     */
    public List<Card> play(Card c){
        if(!canPlay(c)){
            throw new IllegalArgumentException("Cannot play card " + c + " on this pile. Needs " + getNextValue());
        }

        pile.push(c);//If a skip-bo was used, it is just counted as the next number.

        if(pile.size() == 12){
            List<Card> completed = new ArrayList<>(pile);
            pile.clear();//resets pile when it reaches 12 cards.
            return completed;
        }
        return null;
    }

    /**
     * Text representation for debugging or display.
     */
    public String toString(){
        if(pile.isEmpty()) return "[empty]";
        return "Top: " + pile.peek() + " (needs " + getNextValue() + ", " + pile.size() + " cards)";
    }

    /**
     * Checks if pile is full.
     */
    public boolean isFull(){
        return pile.size() == 12;
    }

    /**
     * Checks if pile is empty
     */
    public boolean isEmpty(){
        return pile.isEmpty();
    }

    /**
     * Returns size of pile.
     */
    public int size(){
        return pile.size();
    }
}