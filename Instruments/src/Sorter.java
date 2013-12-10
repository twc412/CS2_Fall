/**
 * Sorter.java
 * 
 * $Id: Sorter.java,v 1.3 2013/10/09 02:05:41 twc9438 Exp $
 * 
 * $Log: Sorter.java,v $
 * Revision 1.3  2013/10/09 02:05:41  twc9438
 * Updating across all workstations.
 *
 * Revision 1.2  2013/10/02 22:46:10  twc9438
 * Still need to test everything. Done working on this for the night.
 *
 * Revision 1.1  2013/10/02 22:23:49  twc9438
 * Initial commit.
 *
 */

import java.util.*;

/**
 *  Implementation of merge sort and quick sort for integers.
 *
 *  @author zjb
 *  @author Troy Caro (twc9438@rit.edu)
 */
public class Sorter {
	
	private int compares = 0, adds = 0;
	
	/**
	 * Resets the statistics to zero.
	 */
	public void reset() {
		this.compares = 0;
		this.adds = 0;
	}
	
	/**
	 * Prints the current value of the stats, in the form "Compares: compares Adds: adds"
	 */
	public void printStats() {
		System.out.println("Compares: " + this.compares + " Adds: " + this.adds);
	}

    /**
     *  Merges two lists, assumes that they are sorted.
     *
     *  @param list1 One sorted list
     *  @param list2 Another sorted list
     *  @return Merged list
     */
    private List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<Integer>();
        int index1 = 0, index2 = 0;
        int len1 = list1.size();
        int len2 = list2.size();
        while (index1 < len1 && index2 < len2 ) {
            if (list1.get(index1) <= list2.get(index2)) {
                result.add(list1.get(index1));
                index1 = index1 + 1;
            } else {
                result.add(list2.get(index2));
                index2 = index2 + 1;
            }
        }
        if (index1 < len1) {
            result.addAll(list1.subList(index1, len1));
        }
        if (index2 < len2) {
            result.addAll(list2.subList(index2, len2));
        }
        return result;
    }

    /**
     * Sorts the given list using Merge Sort.
     *
     * @param nums List to be sorted
     * @return Sorted list
     */
    public List<Integer> mergeSort(List<Integer> nums) {
        
        if (nums.size() <= 1)
            return nums;

        List<Integer> odds = new ArrayList<Integer>();
        List<Integer> evens = new ArrayList<Integer>();
        boolean odd = true;
        for (Integer num : nums) {
            if (odd)
                odds.add(num);
            else 
                evens.add(num);
            odd = !odd;
        }
        return merge(mergeSort(odds), mergeSort(evens));
    }


    /**
     * Sorts the given list using Quick Sort.
     *
     * @param nums List to be sorted
     * @return Sorted list
     */
    public List<Integer> quickSort(List<Integer> nums) {
        if (nums.size() <= 1)
            return nums;

        int pivot = nums.get(0);

        List<Integer> less = new ArrayList<Integer>();
        List<Integer> same = new ArrayList<Integer>();
        List<Integer> more = new ArrayList<Integer>();

        for (Integer num : nums) {
            if (num < pivot)
                less.add(num);
            else if (num > pivot)
                more.add(num);
            else
                same.add(pivot);
        }

        less = quickSort(less);
        more = quickSort(more);

        less.addAll(same);
        less.addAll(more);
        return less;
    }
    
    /**
     * Sorts the given list using Strand Sort.
     * 
     * @param nums List to be sorted
     * @return Sorted list
     */
    public List<Integer> strandSort(List<Integer> nums) {
    	// TODO Need to test this. I'm not sure if it works.
    	List<Integer> result = new ArrayList<Integer>();
    	while (nums.size() > 0) {
    		List<Integer> inOrder = new ArrayList<Integer>();
    		inOrder.add(nums.get(0));
    		for (Integer i : nums) {
    			if ( i >= inOrder.get(inOrder.size()-1) ) {
    				inOrder.add(i);
    			}
    		}
    		result = merge(inOrder, result);
    	}
    	return result;
    }


    /**
     * Main method, creates a random list of Integers and sorts them
     * using both merge sort and quick sort.
     *
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<Integer>();
        Random r = new Random();
        for (int i = 0; i < 50; i++) {
            nums.add(r.nextInt(100));
        }

        System.out.println("Original list: ");
        for (Integer n : nums) {
            System.out.println(n);
        }
        
        Sorter s = new Sorter();

        System.out.println("Merge-sorted list: ");
        List<Integer> msorted = s.mergeSort(nums);
        for (Integer n : msorted) {
            System.out.println(n);
        }
        s.printStats();
        s.reset();

        System.out.println("Quick-sorted list: ");
        List<Integer> qsorted = s.quickSort(nums);
        for (Integer n : qsorted) {
            System.out.println(n);
        }
        s.printStats();
        s.reset();

        System.out.println("Strand-sorted list: ");
        List<Integer> sorted = s.strandSort(nums);
        for (Integer n : sorted) {
            System.out.println(n);
        }
        s.printStats();
        s.reset();
    }

}