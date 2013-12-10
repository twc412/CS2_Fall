/**
 * Woolie.java
 *
 * $Id: Woolie.java,v 1.4 2013/11/21 21:56:10 twc9438 Exp $
 *
 * $Log: Woolie.java,v $
 * Revision 1.4  2013/11/21 21:56:10  twc9438
 * First draft of Woolie.java TrollsBridge.java. Everything compiles fine, first round of testing shows everything is working correctly. Still needs more tests and to finish RunWoolie.java
 *
 * Revision 1.3  2013/11/21 21:51:15  twc9438
 * First draft of Woolie.java TrollsBridge.java. Everything compiles fine, haven't done any testing yet.
 *
 * Revision 1.2  2013/11/21 21:11:53  twc9438
 * Second commit. Starting to add some first drafts and ideas. I haven't tried compiling it yet.
 *
 * Revision 1.1  2013/11/21 19:51:43  twc9438
 * Initial commit.
 *
 */

/**
 * The Woolie simulates a Woolie crossing a TrollsBridge.
 *
 * Each woolie object is constructed with a name, length of time it takes the woolie to cross a bridge,
 * a destination city, and a reference to a TrollsBridge whose troll coordinates how woolies get on and off their bridge.
 *
 * A Woolie extends the Thread class and executes as an active object.
 *
 * Before crossing, a woolie must ask the troll guarding the bridge for permission to cross. 
 * After the troll grants permission, the woolie begins crossing the bridge. After reaching the other side,
 * the woolie must leave and notify the troll and everyone that it is no longer on the bridge.
 *
 * @author Troy Caro (twc9438@rit.edu)
 */
public class Woolie extends Thread {

	public String name, destination;
	public int crossTime, weight;
	public TrollsBridge bridgeGuard;
	
	/**
	 * Construct a new Woolie object that can run as a thread. The constructor simply initializes all of the instance's fields.
	 *
	 * Preconditions:
	 *
	 * destination = "Sicstine" or "Merctran"
	 * crossTime >= 0
	 * name != null
	 * bridgeGuard != null
	 *
	 * @param name the name of this Woolie
	 * @param crossTime the number of seconds it takes the Woolie to cross after it has climbed onto the bridge
	 * @param weight the weight of this Woolie
	 * @param destination the Woolie's destination city
	 * @param bridgeGuard the TrollsBridge that the Woolie is crossing
	 */
	Woolie(String name, int crossTime, int weight, String destination, TrollsBridge bridgeGuard) {
		this.name = name;
		this.destination = destination;
		this.crossTime = crossTime;
		this.weight = weight;
		this.bridgeGuard = bridgeGuard;
	}// Constructor

	/**
	 * Accessor for the weight of the Woolie.
	 *
	 * @return Weight
	 */
	public int getWeight() {
		return this.weight;
	}// getWeight method

	/**
	 * The run method handles a Woolie's behavior as it crosses the bridge. The well-behaved Woolie asks the troll at the bridge to cross. While it is crossing the bridge,
	 * it reports its progress each second as it works its way across, and the woolie lastly tells the troll that it has gotten off the bridge.
	 * There are several messages that a Woolie thread must display to describe their progress crossing the bridge.
	 *
	 * Note: In all the following messages "name" is the representation of the Woolie returned by its getName() method.
	 * The Woolie prints the first message immediately after telling the troll it wants to enter.
	 *
	 * When the Woolie starts crossing the bridge, at time 0, display the message 
	 * name is starting to cross.
	 *
	 * For every one second interval, beyond time 0, that the Woolie is on the bridge, display the message
	 * name xyz seconds.
	 * where "xyz" is the number of seconds that the Woolie has been on the bridge. 
	 *  
	 *  When the Woolie reaches its destination, display the message
	 *  name leaves at city.
	 *  where "city" is the Woolie's destination. After printing this final message, the woolie tells the troll that it is leaving the bridge.
	 */
	public void run() {
		bridgeGuard.enterBridgePlease(this);
		System.out.println(this.name + " is starting to cross");
		for (int i = 1; i <= this.crossTime; i++) {
			try {
				this.sleep(1000);
				System.out.println(this.name + " " + i + " seconds");
			}
			catch (InterruptedException e) { }
		}
		System.out.println(this.name + " leaves at city " + this.destination + ".");
		bridgeGuard.leave(this);
	}// run method
}// Woolie class
