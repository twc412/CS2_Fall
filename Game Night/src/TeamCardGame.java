import java.util.ArrayList;

/**
 * TeamCardGame.java
 *
 * $Id: TeamCardGame.java,v 1.4 2013/09/30 18:15:38 twc9438 Exp $
 *
 * $Log: TeamCardGame.java,v $
 * Revision 1.4  2013/09/30 18:15:38  twc9438
 * Compile error was due to wrong java VM build path on my laptop. Still running tests. Almost ready to submit.
 *
 * Revision 1.3  2013/09/30 18:02:26  twc9438
 * Added all of the functionality. I keep getting a compiler error on my laptop and i'm not sure why...
 * "Exception in thread "main" java.lang.UnsupportedClassVersionError: GameNight : Unsupported major.minor version 51.0"
 *
 * Revision 1.2  2013/09/29 19:41:56  twc9438
 * Still need to finish functionality in  Game, GameNight, and GermanBoardGame. Everything else is done.
 *
 * Revision 1.1  2013/09/26 01:19:03  twc9438
 * Javadoc's are done and method stubs are in. Still need to implement functionality.
 *
 *
 */

/**
 * Represents a card game played with two teams of two (such as bridge, spades, etc).
 * 
 * @author Troy Caro (twc9438@rit.edu)
 *
 */
public class TeamCardGame extends Game {

	/**
	 * Constructor. Assumes all such games are played by four players.
	 * 
	 * @param n
	 * @param np
	 */
	public TeamCardGame(String n) {
		super(n, 4);
	}
	
	/**
	 * Plays the game, selecting a winning team according to total team intelligence. 
	 * This is computed for each team as the team's higher intelligence value plus twice the team's lower intelligence value.
	 */
	@Override
	public void play() {
		System.out.println("Playing " + this.name + "...");
		ArrayList<Player> playing = new ArrayList<Player>();
		
		for (Player p : this.players) {
			if (this.isPlaying(p)) {
				playing.add(p);
			}
		}
		
		Team<Player> teamOne = new Team<Player>(playing.get(0), playing.get(1));
		Team<Player> teamTwo = new Team<Player>(playing.get(2), playing.get(3));
		
		if (teamOne.getIntelligence() > teamTwo.getIntelligence()) {
			teamOne.getObject(1).youWin(); teamOne.getObject(2).youWin();
			System.out.println("Winning team is " + teamOne.getObject(1).getName() + " and " + teamOne.getObject(2).getName());
		}
		else {
			teamTwo.getObject(1).youWin(); teamTwo.getObject(2).youWin();
			System.out.println("Winning team is " + teamTwo.getObject(1).getName() + " and " + teamTwo.getObject(2).getName());
		}

	}

}
