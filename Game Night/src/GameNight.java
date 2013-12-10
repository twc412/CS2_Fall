import java.util.ArrayList;

/**
 * GameNight.java
 *
 * $Id: GameNight.java,v 1.4 2013/10/02 01:30:21 twc9438 Exp $
 *
 * $Log: GameNight.java,v $
 * Revision 1.4  2013/10/02 01:30:21  twc9438
 * Final Version
 *
 * Revision 1.3  2013/09/30 18:15:38  twc9438
 * Compile error was due to wrong java VM build path on my laptop. Still running tests. Almost ready to submit.
 *
 * Revision 1.2  2013/09/30 18:02:26  twc9438
 * Added all of the functionality. I keep getting a compiler error on my laptop and i'm not sure why...
 * "Exception in thread "main" java.lang.UnsupportedClassVersionError: GameNight : Unsupported major.minor version 51.0"
 *
 * Revision 1.1  2013/09/26 01:19:02  twc9438
 * Javadoc's are done and method stubs are in. Still need to implement functionality.
 *
 *
 */

/**
 * Uses the Player class and Game and its subclasses to simulate a night of game-playing.
 * 
 * @author Troy Caro (twc9438@rit.edu)
 *
 */
public class GameNight {

	/**
	 * Main method should:
	 * Create and populate a list of at least 5 players.
	 * Create and populate a list of at least 5 games, using each subclass of Game at least once, and pick players for each game.
	 * Use a loop to play each game exactly once.
	 * Use a loop to print out each Player's ending stats and determine the big winner of the night
	 * Print out the big winner
	 * 
	 * @param args  Command-line args (unused)
	 */
	public static void main(String[] args) {
		/* Create new players and add them to a list */
		ArrayList<Player> availablePlayers = new ArrayList<Player>();
		availablePlayers.add(new Player("Troy", 10, 10, 10, 20));
		availablePlayers.add(new Player("Josh", 5, 5, 5, 20));
		availablePlayers.add(new Player("Kid", 2, 5, 6, 10));
		availablePlayers.add(new Player("Gio", 9, 9, 9, 21));
		availablePlayers.add(new Player("Phil", 5, 6, 9, 21));
		availablePlayers.add(new Player("Jeff", 5, 8, 4, 21));
		availablePlayers.add(new Player("Nate", 8, 6, 3, 25));
		availablePlayers.add(new Player("Sophia", 4, 4, 4, 8));
		
		/* Create new games and add them to a list */
		ArrayList<Game> games = new ArrayList<Game>();
		games.add(new TeamCardGame("Spades"));
		games.add(new TeamCardGame("Black Jack"));
		games.add(new ConsoleGame("Mario Party", 4, false));
		games.add(new ConsoleGame("Call of Duty", 6, true));
		games.add(new BoardGame("Monopoly", 5, 5));
		games.add(new GermanBoardGame("German Game", 3));
		games.add(new BoardGame("Candy Land", 5, 5));
		
		/* Pick players for each game from availablePlayers */
		for (Game g : games) {
			System.out.println("Picking players for " + g.toString() + "...");
			g.pickPlayers(availablePlayers, g.totalPlayers);
			for (Player p : g.players) {
				p.play();
				System.out.println(p.getName());
			}
		}
		
		/* Play each game in the games list */
		for (Game g : games) {
			g.play();
		}
		
		/* Print out each players ending stats and determines the big winner */
		Player winner = null;
		for (Player p : availablePlayers) {
			if (winner == null) {
				winner = p;
			}
			else if (p.getWins() > winner.getWins()) {
				winner = p;
			}
			System.out.println(p.toString());
		}
		
		/* Print out the big winner! */
		System.out.println("Winner is " + winner.getName());
	}
}
