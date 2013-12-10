/**
 * TrollsBridge.java
 *
 * $Id: TrollsBridge.java,v 1.4 2013/11/22 05:20:22 twc9438 Exp $
 *
 * $Log: TrollsBridge.java,v $
 * Revision 1.4  2013/11/22 05:20:22  twc9438
 * Commit to all workstations
 *
 * Revision 1.2  2013/11/21 21:11:52  twc9438
 * Second commit. Starting to add some first drafts and ideas. I haven't tried compiling it yet.
 *
 * Revision 1.1  2013/11/21 19:51:39  twc9438
 * Initial commit.
 *
 */

// Java util imports
import java.util.LinkedList;

/**
 * monitor class. This monitor represents the bridge and its troll,
 * who controls and coordinates access so that (1) no more than the proper amount of Woolies is on the bridge at the same time,
 * and (2) Woolies get on the bridge in the order they arrive.
 *
 * @author Troy Caro (twc9438@rit.edu)
 */
public class TrollsBridge {

	private LinkedList<Woolie> queue = new LinkedList<Woolie>();
	private boolean bridgeFull = false;
	private int totalWeight = 0;
	private int max;

	/**
	 * Create a TrollsBridge with a given capacity. The municipal authority creates a TrollsBridge for each bridge that needs management.
	 *
	 * @param max the maximum capacity of the TrollsBridge.
	 */
	public TrollsBridge(int max) {
		this.max = max;
	}// Constructor

	/**
	 * Request permission to go onto the troll's bridge. Woolies call this method to ask the troll to put them on the queue of woolies trying to get on the bridge.
	 * The Woolie (thread) waits until it becomes the head of the queue and there is room on the troll's bridge.
	 *
	 * Note: Since this class is a monitor, this method needs to ensure mutual exclusive access by calling threads. That means synchronized methods are needed.
	 *
	 * The troll of a TrollsBridge guards its bridge to make sure that woolies get on the bridge in the order of their arrival.
	 *
	 * The troll of a TrollsBridge prints the following message when the Woolie shows up to get in line to cross the bridge:
	 *
	 * The troll scowls "Get in line!" when woolies_name_here shows up at the bridge.
	 *
	 * Precondition:
	 *
	 * The calling thread is the Woolie instance itself.
	 *
	 * Postcondition:
	 *
	 * The woolie got permission and has climbed onto the bridge.
	 * At some future time, the woolie must call leave() to get off.
	 *
	 * @param thisWoolie the Woolie trying to get on the bridge (the same object as Thread calling this method).
	 */
	public synchronized void enterBridgePlease(Woolie thisWoolie) {
		queue.add(thisWoolie);
		System.out.println("The troll scowls \"Get in line!\" when " + thisWoolie.name + " shows up at the bridge.");
		while (bridgeFull == true) {
			if ( (totalWeight + queue.getFirst().getWeight()) > max ) {
				try {
					wait();
				}
				catch (InterruptedException e) { }
			}
			else {
				totalWeight = totalWeight + queue.getFirst().getWeight();
			}
		}
	}// enterBridgePlease method

	/**
	 * Tell the troll of a TrollsBridge that a woolie has left the bridge so that the troll can let other woolies get on if there is room.
	 *
	 * A well-behaved Woolie always informs the troll of a TrollsBridge that it (the caller) is getting off the bridge.
	 *
	 * Note: Since this class is a monitor, this method needs to ensure mutual exclusive access by calling threads. That means synchronized methods are needed.
	 *
	 * Precondition:
	 *
	 * The calling thread is a Woolie instance that has already called enterBridgePlease().
	 *
	 * Postcondition:
	 *
	 * The given Woolie is no longer on this TrollsBridge.
	 *
	 * @param thisWoolie Woolie leaving the bridge
	 */
	public synchronized void leave(Woolie thisWoolie) {
		queue.remove();
		totalWeight = totalWeight - thisWoolie.getWeight();
		bridgeFull = false;
		notifyAll();
	}// leave method
}// TrollsBridge class
