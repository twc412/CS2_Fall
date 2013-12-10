/**
 * SIS.java
 *
 * Version:
 * $Id: SIS.java,v 1.1 2013/10/13 17:37:46 twc9438 Exp $
 *
 * Revisions:
 * $Log: SIS.java,v $
 * Revision 1.1  2013/10/13 17:37:46  twc9438
 * Initial commit.
 *
 */

import java.util.Scanner;
import java.io.*;

/**
 * Main method using different ChainedHashMaps.
 * 
 * @author zjb
 * @author Troy Caro (twc9438@rit.edu)
 */
public class SIS {

    /**
     * Main method.
     * @param args First argument should be a filename containing student data
     */
    public static void main(String[] args) {

        ChainedMap<String,Student> byName = new ChainedMap<String,Student>();
        ChainedMap<Integer,Student> byId = new ChainedMap<Integer,Student>();

        try {
            Scanner filein = new Scanner(new File(args[0]));
            while (filein.hasNextLine()) {
                Student s = new Student(filein.nextLine());
                System.out.println(s);
                byName.put(s.getName(), s);
                byId.put(s.getId(), s);
            }
        } catch (IOException e) {
            System.err.println("Sorry, error opening data file.");
            System.exit(0);
        }

        Scanner in = new Scanner(System.in);
        int response = -1;
        while (response != 5) {
            System.out.println("\nWhat do you want to do?");
            System.out.println("1. Query by name");
            System.out.println("2. Query by ID");
            System.out.println("3. Add a student");
            System.out.println("4. Remove by ID");
            System.out.println("5. Quit");
            System.out.print("Enter 1-5: ");
            response = in.nextInt();            
            Student found;
            switch(response) {
            case 1:
                in.nextLine();
                //
                // ADD CODE HERE:
                // Should query user for a name, look it up in the
                // appropriate map and print the student record or
                // an error message if not present.  Note that since
                // a name may contain spaces, use in.nextLine() to
                // read the entire input.
                //
                break;

            case 2:
                //
                // ADD CODE HERE:
                // Should query user for an ID, look it up in the
                // appropriate map and print the student record or
                // an error message if not present.
                //
                break;

            case 3:
                in.nextLine();
                System.out.print("Enter name: ");
                String newname = in.nextLine();
                System.out.print("Enter major: ");
                String newmajor = in.nextLine();
                System.out.print("Enter year: ");
                int newyear = in.nextInt();
                System.out.print("Enter ID: ");
                int newid = in.nextInt();
                // 
                // ADD CODE HERE:
                // Create student object and add to both hash maps
                //
                break;
            case 4:
                //
                // ADD CODE HERE:
                // Query user for ID number, look it up in the appropriate
                // table.  If it exists, remove the student from BOTH tables.
                // Otherwise print an error message.
                break;
            case 5:
                break;
            default:
                System.out.println("Sorry, not a valid option.");
            }
        }

    }
    
}
