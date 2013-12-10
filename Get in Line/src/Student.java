/**
 * Student.java
 *
 * $Id: Student.java,v 1.3 2013/09/23 23:53:52 twc9438 Exp $
 *
 * $Log: Student.java,v $
 * Revision 1.3  2013/09/23 23:53:52  twc9438
 * All done. About to submit.
 *
 * Revision 1.2  2013/09/21 20:23:33  twc9438
 * Started to implement LinkedQueue. Javadoc's need done.
 *
 * Revision 1.1  2013/09/21 20:05:32  twc9438
 * Initial Commit. Lab parts 1A and 1B are done.
 *
 *
 */

/**
 * @author Troy Caro (twc9438@rit.edu)
 *
 */
public class Student implements Prioritizable {
	
	/**
	 * Instance variables.
	 */
	private String name;
	private int year;
	private double gpa;
	
	/**
	 * Constructor call.
	 * 
	 * @param n Name of the student
	 * @param y The students year
	 * @param g The students GPA
	 */
	public Student(String n, int y, double g) {
		name = n;
		year = y;
		gpa = g;
	}
	
	/**
	 * A string representation of the student object.
	 * 
	 * @return Name in Year with GPA
	 */
	@Override
	public String toString() {
		return name + " in year " + year + " with GPA " + gpa;
	}
	
	/**
	 * The priority of the object, represented as a number
	 * 
	 * @return priority
	 */
	public double getPriority() {
		Integer yr = new Integer(year);
		Double g = new Double(gpa);
		return new Double(yr.toString() + g.toString()).doubleValue() ;
	}
}
