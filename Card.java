
/**
 * Card class modeling a Skip-Bo! Card with values from 0 - 12, where 0 denotes a skip-bo wild card.
 * 
 * @author SebLocal
 * @version 17-10-2025
 */
public class Card
{
    private int value; // 1-12, 0 = Skip-Bo
    /**
     * Constructor for objects of the Card class.
     * @param value Value of the card.
     */
    public Card(int value)
    {
        if (value < 0 || value > 12){
            throw new IllegalArgumentException("Card value must be 0 (Skip-Bo) or 1–12.");
        }
        this.value = value;
    }
    
    /**
     * Getter method for value.
     */
    public int getValue(){
        return value;
    }
    
    /**
     * boolean which is true if the card is a Skip-bo, and false if the card is not.
     */
    public boolean isWildCard(){
        return value == 0;
    }
    
    /**
     * Converts the values of a card into a readable String format.
     */
    public String toString(){
        return value == 0 ? "Skip-Bo" : String.valueOf(value);
    }
}