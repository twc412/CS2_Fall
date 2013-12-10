/**
 * CourseList.java
 * 
 * $Id: CourseList.java,v 1.4 2013/09/16 17:44:19 twc9438 Exp $
 *
 * $Log: CourseList.java,v $
 * Revision 1.4  2013/09/16 17:44:19  twc9438
 * Fully functional and commented. Going to do more testing before I submit.
 *
 * Revision 1.3  2013/09/16 01:33:27  twc9438
 * Generated javadoc's
 *
 * Revision 1.2  2013/09/15 20:27:46  twc9438
 * javadoc's are done. Still have to implement everything.
 *
 * Revision 1.1  2013/09/15 19:54:50  twc9438
 * Initial Commit.
 *
 *
 */
package schedule;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;


/**
 * Represents a list of courses, does little besides reading them in
 * from a data file and storing them in an accessible manner.
 *
 * @author zjb
 */
public class CourseList {

    private ArrayList<Course> courses;

    /**
     * Constructor - takes the name of a file that holds course info.
     * Course file should be in the form:
     * <pre>course_name days start_time end_time</pre>
     * where <tt>days</tt> contains a subset of the letters MTWRF.
     *
     * @param filename Name of data file
     */
    public CourseList(String filename) {
	courses = new ArrayList<Course>();
	try {
	    @SuppressWarnings("resource")
		Scanner s = new Scanner(new File(filename));
	    while (s.hasNext()) {
		String n = s.next();
		String days = s.next();
		ArrayList<Boolean> daylist = new ArrayList<Boolean>(5);
		for (int d = 0; d < 5; d++) {
		    if (days.indexOf(Course.dayString.charAt(d)) == -1)
			daylist.add(false);
		    else
			daylist.add(true);
		}
		int start = s.nextInt();
		int end = s.nextInt();
		courses.add(new Course(n,daylist,start,end));
	    }
	} catch (IOException e) {
	    System.out.println("Can't read course list: " + e);
	}
    }

    /**
     * Returns the course at the given location in the list.
     *
     * @param cnum Course number
     * @return The course
     */
    public Course getCourse(int cnum) {
	return courses.get(cnum);
    }

    /**
     * Returns a string with all of the courses listed.
     *
     * @return String representing the course list
     */
    public String toString() {
	String ret = "";
	for (int c = 0; c < courses.size(); c++) {
	    ret += c + ": " + courses.get(c).toString() + "\n";
	}
	return ret;
    }

}
