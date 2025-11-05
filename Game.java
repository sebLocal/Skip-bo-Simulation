import java.util.*;
/**
 * Lav en beskrivelse af klassen Game her.
 * 
 * @author SebLocal
 * @version 03-11-2025
 */
public class Game
{
    private Player[] players;
    private Deck deck;
    private BuildPile[] buildPiles;
    private int currentPlayerIndex;
    private int noOfPlayers;
    /**
     * Constructor: Constructs Game objects.
     * @param stockSize Size of stock piles in this game.
     * @param noOfPlayers Number of players in this game.
     */
    public Game(int stockSize, int noOfPlayers)
    {
        deck = new Deck();
        deck.shuffleDeck();
        currentPlayerIndex = 1;
        this.noOfPlayers = noOfPlayers;

        players = new Player[noOfPlayers];
        for(int i = 1; i <= noOfPlayers; i++){
            players[i-1] = new Player(i, deck, stockSize);
        }

        buildPiles = new BuildPile[4];
        for(int i = 0; i < 4; i++){
            buildPiles[i] = new BuildPile();
        }
    }

    /**
     * Checks if any player has won
     * @return Returns true if a player has won, false if else.
     */
    public boolean checkForWinner()
    {
        for(Player p : players){
            if(p.hasWon()){
                return true;
            }
        }
        return false;
    }

    /**
     * Makes it the next player's turn.
     */
    public void nextPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
    }

    /**
     * Plays the game until somebody wins
     */
    public void playGame(){
        int turnCount = 0;
        while(!checkForWinner()){
            Player currentPlayer = players[currentPlayerIndex];
            System.out.println("Turn " + turnCount + " - Player " + currentPlayer.getId() + 
                " Stock: " + currentPlayer.getStockPile().getSize());

            playTurn(currentPlayer);
            nextPlayer();
            turnCount++;

            if(turnCount > 1000){
                System.out.println("Game taking too long - stopping");
                break;
            }
        }
        System.out.println("Game ended after " + turnCount + " turns");
    }

    public void playTurn(Player p){
        System.out.println("  Starting turn - drawing cards");
        p.drawCards(deck);
        System.out.println("  Hand size after draw: " + p.getHand().getSize());

        while(canMakeAnyMove(p)){
            System.out.println("  Making a move...");
            if(canPlayFromStock(p)){
                System.out.println("    Playing from stock");
                playStockCard(p);
            }
            else if(canPlayFromHand(p)){
                System.out.println("    Playing from hand");
                playHandCard(p);
            }
            else if(canPlayFromDiscard(p)){
                System.out.println("    Playing from discard");
                playDiscardCard(p);
            }

            if(p.getHand().getSize() == 0){
                System.out.println("  Hand empty - refilling");
                p.drawCards(deck);
            }
        }

        System.out.println("  Ending turn - discarding");
        if(p.getHand().getSize() > 0){
            int cardToDiscard = findHighestCardInHand(p);
            int bestPile = findBestDiscardPile(p);
            p.discardToPile(cardToDiscard, bestPile);
        }
    }

    /**
     * Finds a valid build pile to play a card
     * @param c Card to be played
     * @return Returns a build pile where the card can be played
     */
    public BuildPile findValidBuildPile(Card c){
        for(BuildPile bp : buildPiles){
            if(bp.canPlay(c)){
                return bp;
            }
        }
        return null;
    }

    /**
     * Checks if a player can play the top card from their StockPile
     * @param p Player who is checking
     * @return Returns true if a card can be played, false if else
     */
    public boolean canPlayFromStock(Player p){
        if(p.getStockPile().isEmpty()){
            return false;
        }
        return findValidBuildPile(p.getStockPile().viewTop()) != null;
    }

    /**
     * Checks if a player can play any card from their hand onto any BuildPile
     * @param p Player who is checking
     * @return Returns true if a card can be played, false if else
     */
    public boolean canPlayFromHand(Player p){
        for(int i = 0; i < p.getHand().getSize(); i++){
            if(findValidBuildPile(p.getHand().getCard(i)) != null){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a player can play any card from their discard piles
     * @param p Player who is checking
     * @return Returns true if a card can be played, false if else
     */
    public boolean canPlayFromDiscard(Player p){
        for(int i = 0; i < 4; i++){
            if(!p.getDiscardPiles()[i].isEmpty()){
                if(findValidBuildPile(p.getDiscardPiles()[i].viewTop()) != null && !p.getDiscardPiles()[i].isEmpty()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if a player can make any move
     * @param p Player who is checking
     * @return Returns true if a move can be made, false if else
     */
    public boolean canMakeAnyMove(Player p){
        return canPlayFromStock(p) || canPlayFromHand(p) || canPlayFromDiscard(p);
    }

    /**
     * Plays a card from Stock Pile.
     * @param p Player who is playing
     */
    public void playStockCard(Player p){
        Card c = p.getStockPile().playCard();
        BuildPile bp = findValidBuildPile(c);
        List<Card> completed = bp.play(c);

        if(completed != null){
            deck.addCards(completed);
            deck.shuffleDeck();
        }
    }

    /**
     * Plays a card from Hand
     * @param p Player who is playing
     */
    public void playHandCard(Player p){
        for(int i = 0; i < p.getHand().getSize(); i++){
            Card c = p.getHand().getCard(i);
            BuildPile bp = findValidBuildPile(c);

            if(bp != null){
                p.getHand().playCard(i);
                List<Card> completed = bp.play(c);
                if(completed != null){
                    deck.addCards(completed);
                    deck.shuffleDeck();
                }
                return;
            }
        }
    }

    /**
     * Plays a card from Discard Pile
     * @param p Player who is playing
     */
    public void playDiscardCard(Player p){
        for(int i = 0; i < 4; i++){
            if(!p.getDiscardPiles()[i].isEmpty()){
                Card c = p.getDiscardPiles()[i].viewTop();
                BuildPile bp = findValidBuildPile(c);
                if(bp != null){
                    p.getDiscardPiles()[i].playCard();
                    List<Card> completed = bp.play(c);
                    if(completed != null){
                        deck.addCards(completed);
                        deck.shuffleDeck();
                    }
                    return;
                }
            }
        }    
    }

    /**
     * Finds the best discard pile for a card
     * Prioritizes empty piles, otherwise picks pile with highest top card
     * @param p Player
     * @return index of best discard pile (0-3)
     */
    public int findBestDiscardPile(Player p){
        for(int i = 0; i < 4; i++){
            if(p.getDiscardPiles()[i].isEmpty()){
                return i;
            }
        }

        //If no empty pile, find the one with highest top card
        int bestIndex = 0;
        int highestValue = p.getDiscardPiles()[0].viewTop().getValue();

        for(int i = 1; i < 4; i++){
            int currentValue = p.getDiscardPiles()[i].viewTop().getValue();
            if(currentValue > highestValue){
                highestValue = currentValue;
                bestIndex = i;
            }
        }
        return bestIndex;
    }

    /**
     * Finds the index of highest value card in hand
     * @param p Player
     * @return index of highest card
     */
    public int findHighestCardInHand(Player p){
        int highestIndex = 0;
        int highestValue = p.getHand().getCard(0).getValue();

        for(int i = 1; i < p.getHand().getSize(); i++){
            int currentValue = p.getHand().getCard(i).getValue();
            if(currentValue > highestValue){
                highestValue = currentValue;
                highestIndex = i;
            }
        }

        return highestIndex;
    }

    /**
     * Checks if any player still has a full stock pile
     * @param stockPileSize the starting size (20 or 30)
     * @return true if any player hasn't played from their stock yet
     */
    public boolean anyPlayerHasFullStock(int stockPileSize){
        for(Player p : players){
            if(p.getStockPile().getSize() == stockPileSize){
                return true;
            }
        }
        return false;
    }
}