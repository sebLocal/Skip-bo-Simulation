# Skip-Bo Simulation

Simulates games of Skip-bo to analyze how often one player wins,
while at least one other player has not yet played any card from their stock.

## About

Skip-bo! is a popular family card game. Each game starts with every player having a stock pile. According to the rules, this
pile should have 20 or 30 cards. The goal of the game is to use all of these cards. The first player with an empty stock pile
wins the game. You empty the stock pile by playing a card onto one of the four 'Build Piles'. These piles start empty, and
need to be built up. Starting at 1, and once a 12 is played on a build pile, it is reset back to empty (The cards from this
emptied build pile are shuffled and re-enter the deck).

Each player is also equipped with a hand of five cards. These cards are meant to be used to build up the build piles, so
you can play a card from your stock. At the start of each turn, you refill your hand up to five cards from the deck. If you
empty your hand during a turn, you draw five new cards and continue the turn. To end your turn, you must discard one card 
from your hand to one of four DiscardPiles that each player has. One can also play cards from their Discard Piles, but only
ever the top card. The play loop continues until one player has emptied their stock pile, and wins.

## Features

- Complete Skip-Bo card game implementation with all standard rules
- Intelligent AI strategy that prioritizes stock pile plays and manages discard piles effectively
- Handles edge cases including deck depletion and build pile recycling
- Simulation framework for running thousands of games to collect statistical data
- Tracks specific game outcomes (e.g., games where opponents haven't touched their stock pile)

## How to Run

Running a single game

```bash
javac *.java
java TestDriver
```
Then in the java environment, run:
```
TestDriver td = new TestDriver();
td.runSingleGame(); //Runs one game with 4 players and a stock pile size 20.
```

Run multiple games and collect statistics:
```
TestDriver td = new TestDriver();
td.runSimulations(10000, 2, 20);  // 10,000 games, 2 players, 20-card stock piles
```
## Project Structure

- `Card.java` - Represents a single Skip-Bo card (values 0-12, where 0 is a wild card)
- `Deck.java` - Manages the 162-card deck with shuffling and drawing capabilities
- `BuildPile.java` - Represents one of the four central build piles (builds 1-12 sequentially)
- `StockPile.java` - Each player's personal stock pile they must empty to win
- `Hand.java` - Manages a player's hand of up to 5 cards
- `DiscardPile.java` - Represents one of a player's four personal discard piles
- `Player.java` - Combines stock pile, hand, and discard piles; represents one player
- `Game.java` - Orchestrates the entire game including turn management and AI strategy
- `TestDriver.java` - Runs simulations and collects statistical data

## AI Strategy

The simple AI implemented in this program prioritizes emptying their stock pile. On any given turn, it first checks if it
can play any move, then checks if it can play from StockPile, if no it checks if it can play from Hand, if no it checks
if it can play from DiscardPiles. To end it's turn, it discards its highest value card onto an empty discard pile.
If there is no empty discard pile, it discards onto the discard pile which has the highest value top card.

## Simulation Results
Result of following code:
```
TestDriver td = new TestDriver();
td.runSimulations(100000, 4, 20);  // 100,000 games, 4 players, 20-card stock piles
```
=== RESULTS ===
Total games: 100000
Games where winner won with opponent(s) having full stock: 474
Percentage: 0.474%

## Author

SebLocal
