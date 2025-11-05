import java.util.*;
/**
 * Models one hand in a Skip-Bo game.
 * Each player has a hand that holds up to 5 cards.
 * 
 * @author SebLocal
 * @version 03-11-2025
 */
public class Hand
{
    // instansvariabler - erstat eksemplet herunder med dine egne variabler
    private ArrayList<Card> hand;

    /**
     * Constructor: Constructs a hand with 5 cards from the game's deck.
     * @param d Current deck
     */
    public Hand(Deck d)
    {
        hand = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            hand.add(d.drawCard());
        }
    }

    /**
     * Plays a card at a given index in the hand
     * @param index Index for card that should be played.
     * @return Returns card that is played (and removes it)
     */
    public Card playCard(int index)
    {
        if(hand.isEmpty()){
            throw new IllegalStateException("Cannot play from an empty hand!"); //checks if hand is empty
        }
        if(index < 0 || index >= hand.size()){
            throw new IndexOutOfBoundsException("Invalid ard index: " + index); //checks if index is valid
        }
        return hand.remove(index);
    }
    
    /**
     * Adds a card to the hand
     * @param c Card to add
     */
    public void addCard(Card c){
        if(isFull()){
            throw new IllegalStateException("Hand is already full!");
        }
        hand.add(c);
    }
    
    /**
     * Checks how many cards are in hand
     * @return Returns size of hand
     */
    public int getSize(){
        return hand.size();
    }

    /**
     * Checks if hand is full
     * @return Returns boolean value true if the hand is full
     */
    public boolean isFull(){
        return hand.size() == 5;
    }
    
    /**
     * Getter method for a specific card
     * @param index Index for card to return
     * @return Returns card at given index
     */
    public Card getCard(int index){
        return hand.get(index);
    }
    /**
     * Getter method for all cards
     * @return Returns the hand
     */
    public ArrayList<Card> getCards(){
        return hand;
    }
    /**
     * toString method for hand objects
     * @return Returns all cards in hand
     */
    @Override
    public String toString(){
        if(hand.isEmpty()) return "Hand: [empty]";
        String result = "Hand: ";
        for(Card c : hand){
            result += c + " ";
        }
        return result;
    }
}