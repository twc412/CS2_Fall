/**
 * Snowflake.java
 * 
 * File:
 * 		$Id: Snowflake.java,v 1.8 2013/09/10 22:37:10 twc9438 Exp $
 * 
 * Revisions:
 * 		$Log: Snowflake.java,v $
 * 		Revision 1.8  2013/09/10 22:37:10  twc9438
 * 		Final, working, commented version, ready for submission.
 *
 * 		Revision 1.7  2013/09/10 22:25:57  twc9438
 * 		Fully working. Comments in progress
 *
 * 		Revision 1.6  2013/09/09 01:13:31  twc9438
 * 		Window resize needs work... but other than that I'm pretty sure it works. Still needs commented.
 *
 * 		Revision 1.5  2013/09/08 23:54:01  twc9438
 * 		First test revision.
 *
 * 		Revision 1.4  2013/09/08 23:51:45  twc9438
 * 		First test revision.
 *
 * 		Revision 1.3  2013/09/08 23:26:56  twc9438
 * 		Importing turtle packages
 *
 * 		Revision 1.2  2013/09/08 23:20:13  twc9438
 * 		Second revision.
 *
 * 		Revision 1.1  2013/09/08 23:01:17  twc9438
 * 		Initial revision.
 *
 */
package snowflake;
import java.util.Scanner;
/**
 * Snowflake class generates a snowflake using recursion and Turtle graphics.
 *
 * Example: The program runs with no command line arguments and prompts for two values: first the side length, and second, the number of recursions. The user enters the values 100 and 3 through standard input.
 *
 * $ java Snowflake
 *   Enter S: 100
 *   Enter N: 3
 *   
 * @author Troy W Caro <twc9438@rit.edu>
 *
 */
public class Snowflake {

	/**
	 * Draw a snowflake side.
	 * 
	 * @param t The turtle object to draw with
	 * @param S The length of the snowflake branch.
	 * @param N The depth of recursion.
	 */
	public static void snowflakeSide(int S, int N, Turtle t){
		if (N > 0) {
			t.goForward(S);
			if (N > 1) {
				t.turnLeft(120.0);
				for (int x = 0; x < 5; x++) {
					snowflakeSide(S/3, N-1, t);
					t.turnRight(60.0);
				}
				t.turnRight(180.0);
			}
			t.goForward(-S);
		}
		else {
			return;
		}
	}
	
	/**
	 * Draw a snowflake.
	 * 
	 * @param t The turtle object to draw with.
	 * @param S The length of the snowflake branch.
	 * @param N The depth of recursion.
	 */
	public static void snowflake(int S, int N, Turtle t){
		for (int x = 0; x < 6; x++) {
			snowflakeSide(S, N, t);
			t.turnLeft(60.0);
		}	
	}
	
	/**
	 * Initialize the graphics.
	 * 
	 * @param S The length of the main snowflake branch.
	 * @return A turtle object to draw with.
	 */
	public static Turtle init(int S){
		Turtle turtle = new Turtle(250, 250, 0.0);
		turtle.setWorldCoordinates(0, 0, 500, 500);
		return turtle;
	}
	
	/**
	 * The main method gets user inputs and draws the snowflake part by part.
	 * 
	 * @param args The command line arguments (unused).
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter S: ");
		int S = sc.nextInt();
		System.out.print("Enter N: ");
		int N = sc.nextInt();
		Turtle turtle = init(S);
		snowflake(S, N, turtle);
	} // end main

} // end class
