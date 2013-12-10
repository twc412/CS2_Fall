import java.util.ArrayList;

/**
 * ChainedMap.java
 *
 * $Id: ChainedMap.java,v 1.2 2013/10/13 19:05:46 twc9438 Exp $
 *
 * $Log: ChainedMap.java,v $
 * Revision 1.2  2013/10/13 19:05:46  twc9438
 * Still  need to add functionality
 *
 * Revision 1.1  2013/10/13 17:37:45  twc9438
 * Initial commit.
 *
 *
 */

/**
 * @author Troy Caro (twc9438@rit.edu)
 *
 */
public class ChainedMap<K, V> {
	
	/**
	 * Private class to represent a pair of key and value objects.
	 * 
	 * @author Troy Caro (twc9438@rit.edu)
	 *
	 * @param <K>
	 * @param <V>
	 */
	private class Pair<K, V> {
		private K key;
		private V value;
		private Pair<K, V> next;
		
		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
		
		public K getKey() {
			return this.key;
		}
		
		public V getvalue() {
			return this.value;
		}
	}
	
	/**
	 * ChainedMap instance variables.
	 */
	private ArrayList<Pair<K, V>> table;
	private int numKeys, cap;
	public final int INITIAL_CAPACITY = 10;
	public final double MAX_LOAD = 0.70;

	public ChainedMap() {
		table = new ArrayList<Pair<K, V>>(INITIAL_CAPACITY);
		cap = INITIAL_CAPACITY;
		for (int x = 0; x < cap; x++) table.add(null);
		numKeys = 0;
	}
	
	public V get(K key) {
		// TODO Auto-generated method stub for 'get'
		return null;
	}
	
	/**
	 * @param name
	 * @param s
	 */
	public void put(K key, V value) {
		// TODO Auto-generated method stub for 'put'
	}
	
	public V remove(K key) {
		// TODO Auto-generated method stub for 'remove'
		return null;
	}
}
