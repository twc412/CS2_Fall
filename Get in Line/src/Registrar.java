import java.util.Scanner;

/**
 * Registrar.java
 *
 * $Id: Registrar.java,v 1.4 2013/09/23 23:53:52 twc9438 Exp $
 *
 * $Log: Registrar.java,v $
 * Revision 1.4  2013/09/23 23:53:52  twc9438
 * All done. About to submit.
 *
 * Revision 1.3  2013/09/23 23:37:55  twc9438
 * Fully functional working code. Just need to finish up my javadoc's then submit.
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
public class Registrar {

	/**
	 * Main program execution.
	 * 
	 * @param args System arguments are not used in this program.
	 */
	public static void main(String[] args) {
		boolean done = false;
		//HeapQueue<Student> queue = new HeapQueue<Student>();
		LinkedQueue<Student> queue = new LinkedQueue<Student>();
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		while (!done) {
			System.out.println("\nEnter an option");
			System.out.println("1 to Add a student to the queue");
			System.out.println("2 to Remove and print the first student");
			System.out.println("3 to quit");
			int choice = in.nextInt();
			System.out.println();
			System.out.println("Your choice: " + choice);
			if (choice == 1) {
				String name;
				int year;
				double gpa;
				System.out.print("Name: ");
				name = in.next();
				System.out.print("Year: ");
				year = in.nextInt();
				System.out.print("GPA: ");
				gpa = in.nextDouble();
				queue.insert(new Student(name, year, gpa));
			}
			else if (choice == 2) {
				if (queue.isEmpty()) {
					System.out.println("The queue is empty");
				}
				else {
					System.out.println("Registering " + queue.dequeue().toString());
				}
			}
			else if (choice == 3) {
				done = true;
			}
		}
		System.out.println("Thank you for using the registration queue!");
		System.exit(0);
	}

}
