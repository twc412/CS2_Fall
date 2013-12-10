/**
 * ConsoleGame.java
 *
 * $Id: ConsoleGame.java,v 1.4 2013/09/30 18:15:38 twc9438 Exp $
 *
 * $Log: ConsoleGame.java,v $
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
 * Represents a console game. Success at console games always depends on dexterity, and may optionally depend on intelligence as well.
 * 
 * @author Troy Caro (twc9438@rit.edu)
 *
 */
public class ConsoleGame extends Game {
	
	/**
	 * Whether or not the game uses intelligence
	 */
	private boolean usesBrains;

	/**
	 * Constructor, takes three args to set the instance variables
	 * 
	 * @param name Name of the game
	 * @param np Number of players that can play the game at once
	 * @param usesBrains Whether or not the game uses intelligence
	 */
	public ConsoleGame(String name, int np, boolean usesBrains) {
        super(name,np);
        this.usesBrains = usesBrains;
	}
	
	/**
	 * Plays the game and chooses a winner. 
	 * Winner is chosen either as the player with highest dexterity (if the game does not use intelligence) or highest (dexterity + intelligence) otherwise.
	 */
	@Override
	public void play() {
		System.out.println("Playing " + this.name + "...");
		Player highest = null;
		
		if (this.usesBrains) {
			for (Player p : this.players) {
				if (highest == null) {
					highest = p;
				}
				else if (this.isPlaying(p)) {
					if ( (p.getDexterity() + p.getIntelligence()) > (highest.getDexterity() + highest.getIntelligence())) {
						highest = p;
					}
				}
			}	
		}
		else {
			for (Player p : this.players) {
				if (highest == null) {
					highest = p;
				}
				else if (this.isPlaying(p)) {
					if (p.getDexterity() > highest.getDexterity()) {
						highest = p;
					}
				} 
			}
		}
		System.out.println("Winner is " + highest.getName());
		highest.youWin();
	}

}
