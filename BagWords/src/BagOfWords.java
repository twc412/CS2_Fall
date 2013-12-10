// Last Edit:
// Wed Nov 20 20:06:05 EST 2013

// Java IO import statements
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

// Java util imports for internal data structure
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * BagOfWords.java
 *
 * $Id: BagOfWords.java,v 1.6 2013/11/21 01:06:56 twc9438 Exp $
 *
 * $Log: BagOfWords.java,v $
 * Revision 1.6  2013/11/21 01:06:56  twc9438
 * Last edit
 *
 * Revision 1.5  2013/11/20 22:51:02  twc9438
 * Final working version
 *
 * Revision 1.4  2013/11/20 16:12:41  twc9438
 * First few rounds of testing done. Going to continue testing and making minor changes before I submit.
 *
 * Revision 1.3  2013/11/20 04:11:38  twc9438
 * Streams are working properly. Need to make some adjustments on makeBag() and run some more testing. Will be ready to submit tomorrow.
 *
 * Revision 1.2  2013/11/19 22:35:48  twc9438
 * First draft. A lot of the functionality is there but I still haven't done any testing.
 * Going to be finished by tonight...
 *
 * Revision 1.1  2013/11/18 15:51:57  twc9438
 * Initial commit.
 *
 */

/**
 * Computes and outputs the contents of a bag of words based on a given text file and file of stop words.
 * 
 * @author Troy Caro (twc9438@rit.edu)
 */
public class BagOfWords {
	
	// Private instance variables.
	// Used a hashmap for the 'bag of words' structure
	// ArrayList for storing the list of 'stop words'
	private HashMap<String, Integer> bag = null;
	private ArrayList<String> stopWords = null;

	/**
	 * Constructor loads and stores the stop words from the given file.
	 * 
	 * @param stopfile Name of file containing stop words
	 * 
	 * @throws IOException If any exception occurs opening or handling the file of stop words
	 * @throws BagException If any stop word ends in punctuation, throws this exception with the message "Bad stop word " and the offending stop word
	 */
	public BagOfWords(String stopfile) throws IOException, BagException {
		// Keep getting 'Resource leak: inputStream is never closed', 
		// but it's clearly closed..?
		//
		// Creates new BufferedReader stream from a new FileReader stream
		BufferedReader inputStream = new BufferedReader( new FileReader(stopfile) );
		// Initialize data structure
		stopWords = new ArrayList<String>();
		
		String l;
		while ( (l = inputStream.readLine() ) != null) {
			// Testing to make sure the trailing character is a letter
			if(Character.isLetter(l.charAt(l.length() - 1)) == false) {
				throw new BagException("Bad stop word" + l + " ");
			}
			else {
				stopWords.add(l);
			}
		}
		// Close input stream
		inputStream.close();
	}
	
	/**
	 * Creates the sorted bag of words based on the given file
	 * 
	 * @param fileName Name of file to parse
	 * 
	 * @throws IOException Thrown if any exceptions dealing with the file of text
	 * @throws BagException Thrown if there are no non stop words in the file, with the message "No non stop words given"
	 */
	public void makeBag(String fileName) throws IOException, BagException {
		// Creates new BufferedReader stream from a new FileReader stream
		BufferedReader inputStream = new BufferedReader( new FileReader(fileName) );
		// Initialize data structure
		bag = new HashMap<String, Integer>();
		
		String l;
		while ( (l = inputStream.readLine() ) != null) {
			// Replaces all punctuation and splits based on spaces
			//
			// replaceAll() takes a regex expression to parse the words in the string.
			// The regex pattern '[^a-zA-Z0-9\\-\\'\\s]' keeps all alphanumeric characters,
			// anything separated by a hyphen, and spaces. split() is then used to return an
			// array of the individual words.
			String[] tokens = l.replaceAll("[^a-zA-Z0-9\\-\\'\\s]", "").toLowerCase().split("\\s+");
			// Iterate over each word parsed from the line
			for (String t : tokens) {
				// Test to make sure it's not an empty String
				if ( t.equals("") == false ){
					// Check to see if the word is in the 'stop words' list
					if ( stopWords.contains(t) == false ){
						// Check if word is already in the map
						if (bag.containsKey(t)) {
							// Update hash-map value
							bag.put(t, (bag.get(t) + 1) );
						}
						else {
							// Create a new entry in hash-map
							bag.put(t, 1);
						}
					}
				}
			}
		}
		// Close the input stream
		inputStream.close();
		
		if ( bag.isEmpty() ) { // There are no non 'stop' words in the file
			throw new BagException("No non stop words given");
		}
	}
	
	/**
	 * Prints a representation of the bag to the given output stream. Each word in the bag should be printed along with the number of times it appeared in the text file (such as apple: 2)
	 * 
	 * @param out The output stream to print the bag contents to
	 * 
	 * @throws IOException Thrown if any exceptions occur during printing
	 */
	public void outputBag(OutputStream out) throws IOException {
		// Creates new output stream for writing readable data
		DataOutputStream output = new DataOutputStream(out);
		// Make new ArrayList for easy sorting of keys in hashmap
		ArrayList<String> sortedKeys = new ArrayList<String>(bag.keySet());
		Collections.sort(sortedKeys);
		// Iterate over sorted keys and print them out along with their value
		for (String key : sortedKeys ) {
			output.writeChars( key + ": " + this.bag.get(key) + "\n" );
		}
	}
	
	/**
	 * Main method, creates a bag and fills it based on files named in the command-line arguments. 
	 * Prints to a file if a file name is given as the third command-line argument, otherwise prints to standard output.
	 * 
	 * @param args args[0]: name of file containing stop words, args[1]: name of file containing text to parse, args[2] (optional): name of file to print bag contents to.
	 */
	public static void main(String[] args) {
		// Check for incorrect number of arguments
		if (args.length < 2 || args.length > 3) {
			System.err.println("Usage: java BagOfWords STOP_WORD_FILE INPUT_TEXT [OUTPUT_FILE]");
			System.exit(0);
		}
		// Initialize OutputStream	
		OutputStream outputStream = null;
		
		// Check to see if user wants to write output to a file
		if (args.length == 3) {
			try {
				// Create new file output stream
				outputStream = new FileOutputStream( args[2] );
			}
			catch (FileNotFoundException fnfe) {
				System.err.println( "File for writing cannot be opened for some reason! " + fnfe.getMessage() );
			}
		}
		// Direct output to standerd terminal output
		else {
			outputStream = System.out;
		}
		
		try {
			BagOfWords bag = new BagOfWords( args[0] );
			bag.makeBag( args[1] );
			bag.outputBag( outputStream );
		}
		catch (FileNotFoundException fnfe) {
			System.err.println("File not found! " + fnfe.getMessage() );
		}
		catch (IOException ioe) {
			System.err.println( ioe.getMessage() );
		}
		catch (BagException be) {
			System.err.println( be.getMessage() );
		}
		finally {
			try {
				// Check to make sure that the output stream was created
				if (outputStream != null) outputStream.close(); // Close stream
			}
			catch(IOException ioe) {
				System.err.println( "Problem closing the output stream! " + ioe.getMessage() );
			}
		}
	}
}
