
/**
 * Test class for the Skip-bo simulation
 *
 * @author SebLocal
 * @version 05-11-2025
 */
public class TestDriver
{
    /**
     * Runs one game of Skip-bo with 20 stock pile size and 4 players.
     */
    public static void runSingleGame()
    {
        Game game = new Game(20, 4);
        game.playGame();
    }
    
    /**
     * Plays a given amount of games with a given number of players and a given stock pile size.
     */
    public static void runSimulations(int numGames, int numPlayers, int stockPileSize){
        int gamesWithFullStock = 0;
        
        for(int i = 0; i < numGames; i++){
            Game game = new Game(stockPileSize, numPlayers);
            game.playGame();
            
            // Check if any player still has full stock when game ended
            if(game.anyPlayerHasFullStock(stockPileSize)){
                gamesWithFullStock++;
            }
            
            if((i + 1) % 100 == 0){  // Progress update every 100 games
                System.out.println("Completed " + (i + 1) + " games...");
            }
        }
        
        System.out.println("\n=== RESULTS ===");
        System.out.println("Total games: " + numGames);
        System.out.println("Games where winner won with opponent(s) having full stock: " + gamesWithFullStock);
        System.out.println("Percentage: " + (100.0 * gamesWithFullStock / numGames) + "%");
    }
}