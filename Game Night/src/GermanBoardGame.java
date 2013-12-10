import java.util.ArrayList;
import java.util.Random;

/**
 * GermanBoardGame.java
 *
 * $Id: GermanBoardGame.java,v 1.2 2013/09/30 18:02:26 twc9438 Exp $
 *
 * $Log: GermanBoardGame.java,v $
 * Revision 1.2  2013/09/30 18:02:26  twc9438
 * Added all of the functionality. I keep getting a compiler error on my laptop and i'm not sure why...
 * "Exception in thread "main" java.lang.UnsupportedClassVersionError: GameNight : Unsupported major.minor version 51.0"
 *
 * Revision 1.1  2013/09/26 01:19:03  twc9438
 * Javadoc's are done and method stubs are in. Still need to implement functionality.
 *
 *
 */

/**
 * Class to represent German board games. These games are like regular board games with two exceptions. 
 * They never rely on any luck, and only people over 10 years of age play them.
 * 
 * @author Troy Caro (twc9438@rit.edu)
 *
 */
public class GermanBoardGame extends BoardGame {

	/**
	 * Constructor, takes a name and number of players
	 * 
	 * @param n Name of the game
	 * @param np Number of players of the game
	 */
	public GermanBoardGame(String n, int np) {
		super(n, np, 0);
	}
	
	/**
	 * Chooses players to play the game at random, but will not choose any player age 10 or under.
	 * 
	 * @param players List of players to choose from
	 * @param The number of players to pick
	 */
	@Override
	public void pickPlayers(ArrayList<Player> players, int num) {
		while (this.players.size() < num) {
			int rand = new Random().nextInt(players.size());
			if (players.get(rand).getAge() > 10) {
				if (!(this.isPlaying(players.get(rand)))) {
					this.players.add(players.get(rand));
					
				}
			}
		}
	}
}
