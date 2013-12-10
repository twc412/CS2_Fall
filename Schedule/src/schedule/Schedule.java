/**
 * Schedule.java
 * 
 * $Id: Schedule.java,v 1.6 2013/09/16 17:44:19 twc9438 Exp $
 * 
 * $Log: Schedule.java,v $
 * Revision 1.6  2013/09/16 17:44:19  twc9438
 * Fully functional and commented. Going to do more testing before I submit.
 *
 * Revision 1.5  2013/09/16 01:33:27  twc9438
 * Generated javadoc's
 *
 * Revision 1.4  2013/09/16 01:15:10  twc9438
 * Most functionality is there. Still need to do testing.
 *
 * Revision 1.3  2013/09/15 20:27:46  twc9438
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
 * Class that holds a number of courses that do not conflict and can print out a simple day-by-day schedule.
 * 
 * @author Troy Caro (twc9438@rit.edu)
 */
public class Schedule {
	
	public ArrayList<Course> courses = null;
	
	/**
	 * Constructor - creates/initializes any necessary data structures.
	 */
	public Schedule() {
		courses = new ArrayList<Course>();
	}

	/**
	 * Tests whether a given Course is currently on the schedule.
	 * 
	 * @param course The course to test.
	 * @return True if the course is on the schedule.
	 */
	public boolean contains(Course course) {
		for (Course c : this.courses) {
			if (c.equals(course)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds the given course to the schedule only if it is not in conflict with courses currently on the schedule.
	 * 
	 * @param course Course to attempt to add.
	 * @return Whether the course was successfully added.
	 */
	public boolean add(Course course) {
		for (Course c : this.courses) {
			if (c.inConflict(course)) {
				return false;
			}
		}
		this.courses.add(course);
		return true;
	}

	/**
	 * Prints a day-by-day schedule (using relevant functions from the Course class), in the following format.
	 * ----Monday----
	 * 8-10: CS3
	 * 13-14: WaterPolo
	 * ----Tuesday----
	 * 13-14: WaterPolo
	 * ----Wednesday----
	 * 8-10: CS3
	 * 13-14: WaterPolo
	 * ----Thursday----
	 * ----Friday----
	 * 8-10: CS3
	 * 13-14: WaterPolo
	 * 
	 * Note that sorting the classes on each day is not required.
	 */
	public void prettyPrint() {
		ArrayList<Course> mondayClasses = new ArrayList<Course>();
		ArrayList<Course> tuesdayClasses = new ArrayList<Course>();
		ArrayList<Course> wednesdayClasses = new ArrayList<Course>();
		ArrayList<Course> thursdayClasses = new ArrayList<Course>();
		ArrayList<Course> fridayClasses = new ArrayList<Course>();

		for (Course c : this.courses) {
			if (c.days.get(0)) {
				mondayClasses.add(c);
			}
			if (c.days.get(1)) {
				tuesdayClasses.add(c);
			}
			if (c.days.get(2)) {
				wednesdayClasses.add(c);
			}
			if (c.days.get(3)) {
				thursdayClasses.add(c);
			}
			if (c.days.get(4)) {
				fridayClasses.add(c);
			}
		}

		System.out.println("----Monday----");
		for (Course c : mondayClasses) {
			System.out.println(c.inDay(0));
		}
		System.out.println("----Tuesday----");
		for (Course c : tuesdayClasses) {
			System.out.println(c.inDay(1));
		}
		System.out.println("----Wednesday----");
		for (Course c : wednesdayClasses) {
			System.out.println(c.inDay(2));
		}
		System.out.println("----Thursday----");
		for (Course c : thursdayClasses) {
			System.out.println(c.inDay(3));
		}
		System.out.println("----Friday----");
		for (Course c : fridayClasses) {
			System.out.println(c.inDay(4));
		}
		System.out.println();
		System.out.println(this.toString());
	}
	
	/**
	 * Simple string representation: "Schedule with n courses" where n is the number of courses on the schedule.
	 * 
	 * @return String as above.
	 */
	@Override
	public String toString() {
		return "Schedule with " + this.courses.size() + " courses.";
	}

}
