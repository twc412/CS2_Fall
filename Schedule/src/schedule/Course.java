/**
 * Course.java
 * 
 * $Id: Course.java,v 1.6 2013/09/16 17:44:18 twc9438 Exp $
 * 
 * $Log: Course.java,v $
 * Revision 1.6  2013/09/16 17:44:18  twc9438
 * Fully functional and commented. Going to do more testing before I submit.
 *
 * Revision 1.5  2013/09/16 01:33:27  twc9438
 * Generated javadoc's
 *
 * Revision 1.4  2013/09/16 01:15:09  twc9438
 * Most functionality is there. Still need to do testing.
 *
 * Revision 1.3  2013/09/15 20:27:45  twc9438
 * javadoc's are done. Still have to implement everything.
 *
 * Revision 1.2  2013/09/15 20:26:12  twc9438
 * javadoc's are done. Still have to implement everything.
 *
 *
 */
package schedule;

import java.util.ArrayList;

/**
 * Represents a single course, including name, days and time scheduled. Assumes that all courses start and end on the hour.
 * 
 * @author Troy Caro (twc9438@rit.edu)
 */
public class Course {

	/**
	 * A String containing all possible days as characters, in order ("MTWRF").
	 */
	public static final String dayString = "MTWRF";

	public String name;
	public ArrayList<Boolean> days;
	public int startTime;
	public int endTime;
	
	/**
	 * Constructor.
	 * 
	 * @param n Name of the course.
	 * @param daylist List of days that the course is held on.
	 * @param start Starting time (in hours, 24-hour clock).
	 * @param end Ending time (in hours, 24-hour clock).
	 */
	public Course(String n, ArrayList<Boolean> daylist, int start, int end) {
		this.name = n;
		this.days = daylist;
		this.startTime = start;
		this.endTime = end;
	}
	
	/**
	 * Test for equality.
	 * 
	 * @param other Object to be tested against.
	 * @return True if the object passed in is a Course with the same name, days, start and end time as this Course.
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Course)) {
			return false;
		}
		
		Course otherc = (Course)other;
		if (this.name.equals(otherc.name) && this.days.equals(otherc.days) && this.startTime == otherc.startTime && this.endTime == otherc.endTime) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Test for scheduling conflict.
	 * 
	 * @param other Course to test against.
	 * @return True if the passed-in Course overlaps in time (on any day) with this Course.
	 */
	public boolean inConflict(Course other) {
		ArrayList<Integer> courseA = new ArrayList<Integer>();
		ArrayList<Integer> courseB = new ArrayList<Integer>();
		int conflicts = 0;
		
		for (int x = this.startTime; x <= this.endTime; x++) {
			courseA.add(new Integer(x));
		}
		for (int y = other.startTime; y <= other.endTime; y++) {
			courseB.add(new Integer(y));
		}
		
		for (int a = 0; a < courseA.size(); a++) {
			for (int b = 0; b < courseB.size(); b++) {
				if (courseA.get(a).equals(courseB.get(b))) {
					conflicts++;
				}
			}
		}
		
		if (conflicts > 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns a string representing the time this course meets on the given day, if any. If it does meet, the String should be in the form Start-end: Name (for example: 8-10: Calculus).
	 * 
	 * @param day  Day of the week, where 0 = Monday ... 4 = Friday.
	 * @return String as above, or the empty String if the course does not meet on the given day.
	 */
	public String inDay(int day) {
		return this.startTime + "-" + this.endTime + ": " + this.name;
	}
	
	/**
	 * String representation of the course, in the form Name: days at start-end (for example: Calculus: MWF at 8-10).
	 * 
	 * @return String representation.
	 */
	@Override
	public String toString() {
		String days = "";
		if (this.days.get(0)) {
			days += "M";
		}
		if (this.days.get(1)) {
			days += "T";
		}
		if (this.days.get(2)) {
			days += "W";
		}
		if (this.days.get(3)) {
			days += "R";
		}
		if (this.days.get(4)) {
			days += "F";
		}

		return this.name + ": " + days + " at " + this.startTime + "-" + this.endTime;
	}

}
