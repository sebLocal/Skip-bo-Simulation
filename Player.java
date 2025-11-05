
/**
 * Models a player in a game of Skip-bo!
 * 
 * @author SebLocal
 * @version 3-11-2025
 */
public class Player
{
    private StockPile sp;
    private Hand h;
    private DiscardPile[] dp;
    private int id;
    /**
     * Constructor: Constructs a player object.
     * Gives them their StockPile, hand and four discardpiles, as well as an id
     * @param id Identifier for this player
     * @param deck The current deck
     * @param stockPileSize Size of stock piles in the current game
     */
    public Player(int id, Deck deck, int stockPileSize)
    {
        this.id = id;
        sp = new StockPile(deck, stockPileSize);
        h = new Hand(deck);
        dp = new DiscardPile[4];
        for(int i = 0; i < 4; i++){
            dp[i] = new DiscardPile();
        }
    }

    /**
     * Checks if a player has won i.e if their StockPile is empty.
     * @returns true if stockpile is empty, false if else.
     */
    public boolean hasWon()
    {
        return sp.isEmpty();
    }
    
    /**
     * Getter for hand
     * @return Returns hand
     */
    public Hand getHand(){
        return h;
    }
    
     /**
     * Getter for stock pile
     * @return Returns stock pile
     */
    public StockPile getStockPile(){
        return sp;
    }
    
     /**
     * Getter for discard piles
     * @return Returns discard piles
     */
    public DiscardPile[] getDiscardPiles(){
        return dp;
    }
    
    /**
     * Getter for player id
     * @return Returns id
     */
    public int getId(){
        return id;
    }
    
    /**
     * Refills hand to 5 to start new turn
     * @param deck Current deck
     */
    public void drawCards(Deck deck){
        while(h.getSize() < 5 && !deck.isEmpty()){
            h.addCard(deck.drawCard());
        }
    }
    
    /**
     * Plays a card from the StockPile
     * @param buildPile Build Pile to play card to
     */
    public void playFromStock(BuildPile buildPile){
        buildPile.play(sp.playCard());
    }
    
    /**
     * Plays a card from the hand
     * @param cardIndex Index of card to play
     * @param buildPile Build Pile to play card to
     */
    public void playFromHand(int cardIndex, BuildPile buildPile){
        buildPile.play(h.playCard(cardIndex));
    }
    
    /**
     * Plays a card from one of the four discard piles
     * @param discardIndex Index of discard pile to play from
     * @param buildPile Build Pile to play card to
     */
    public void playFromDiscard(int discardIndex, BuildPile buildPile){
        if(discardIndex < 0 || discardIndex > 3){
            throw new IndexOutOfBoundsException("Invalid index! discardIndex must be 0-3!");
        }
        buildPile.play(dp[discardIndex].playCard());
    }
    
    /**
     * Discards a card from hand to a discard pile (end of turn)
     * @param handIndex Index of card in hand to discard
     * @param discardIndex Which discard pile (0-3) to discard to
     */
    public void discardToPile(int handIndex, int discardIndex){
        Card c = h.playCard(handIndex);
        dp[discardIndex].discardCard(c);
    }
}