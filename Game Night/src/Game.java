import java.util.ArrayList;
import java.util.Random;

/**
 * Game.java
 *
 * $Id: Game.java,v 1.4 2013/09/30 18:02:26 twc9438 Exp $
 *
 * $Log: Game.java,v $
 * Revision 1.4  2013/09/30 18:02:26  twc9438
 * Added all of the functionality. I keep getting a compiler error on my laptop and i'm not sure why...
 * "Exception in thread "main" java.lang.UnsupportedClassVersionError: GameNight : Unsupported major.minor version 51.0"
 *
 * Revision 1.3  2013/09/30 16:33:54  twc9438
 * Updating across all workstations.
 *
 * Revision 1.2  2013/09/26 01:19:03  twc9438
 * Javadoc's are done and method stubs are in. Still need to implement functionality.
 *
 * Revision 1.1  2013/09/25 23:00:21  twc9438
 * Initial commit.
 *
 */

/**
 * Class to represent a generic game. Has instance variables for the name of the game (String), number of players that can play the game (int) and who is playing the game (array of Player).
 * 
 * @author Troy Caro (twc9438@rit.edu)
 *
 */
public abstract class Game {
	
	/**
	 * Instance Variables
	 */
	protected String name;
	protected int totalPlayers;
	protected ArrayList<Player> players = new ArrayList<Player>();

	/**
	 * Constructor, takes two args to set the instance variables
	 * 
	 * @param n Name of the game
	 * @param np Number of players that can play the game at once
	 */
	public Game(String n, int np) {
		this.name = n;
		this.totalPlayers = np;	
	}
	
	/**
	 * Chooses players at random from the array passed in to play the game. 
	 * Will pick as many players as can play the game at once. 
	 * Hint: this can be done in one line by calling another method.
	 * 
	 * @param players List of Players that might play the game
	 */
	public void pickPlayers(ArrayList<Player> players) {
		this.pickPlayers(players, this.totalPlayers);
	}
	
	/**
	 * Chooses players at random from the list passed in to play the game. 
	 * Will pick the number of players given as the second parameter 
	 * (unless the parameter is larger than the number that can play). 
	 * You should first create the list of Players of the correct size. 
	 * Hint: use the isPlaying method to make sure no one plays twice!
	 * 
	 * @param players List of Players that might play the game
	 * @param num Number of players to pick
	 */
	public void pickPlayers(ArrayList<Player> players, int num) {
		while (this.players.size() < num) {
			int rand = new Random().nextInt(players.size());
			if (!(this.isPlaying(players.get(rand)))) {
				this.players.add(players.get(rand));
				
			}
		}
	}
	
	/**
	 * Checks the list of current players to see if a particular player is playing the game
	 * 
	 * @param p A player to check
	 * @return Whether the player passed in is playing this game
	 */
	public boolean isPlaying(Player p) {
		for (Player compare : this.players) {
			if (compare.getName().equals(p.getName())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a String representation of the game, in this case simply the name of the game.
	 * 
	 * @return Name of the game.
	 */
	@Override
	public String toString() {
		return this.name;
	}
	
	/**
	 * Play the game! Since this is a generic game, no default behavior.
	 */
	public abstract void play();

}
