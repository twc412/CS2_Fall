import java.io.Serializable;

/**
 * BagException.java
 *
 * $Id: BagException.java,v 1.2 2013/11/19 22:35:48 twc9438 Exp $
 *
 * $Log: BagException.java,v $
 * Revision 1.2  2013/11/19 22:35:48  twc9438
 * First draft. A lot of the functionality is there but I still haven't done any testing.
 * Going to be finished by tonight...
 *
 * Revision 1.1  2013/11/18 15:51:57  twc9438
 * Initial commit.
 *
 */

/**
 * Simple class to represent issues with bags of words.
 * 
 * @author Troy Caro (twc9438@rit.edu)
 */
public class BagException extends Exception implements Serializable {

	/**
	 * Constructor, simply calls appropriate superclass constructor.
	 * 
	 * @param msg message
	 */
	public BagException(String msg) {
		super(msg);
	}
}
