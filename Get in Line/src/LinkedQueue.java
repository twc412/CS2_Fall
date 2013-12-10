/**
 * LinkedQueue.java
 *
 * $Id: LinkedQueue.java,v 1.3 2013/09/23 23:53:52 twc9438 Exp $
 *
 * $Log: LinkedQueue.java,v $
 * Revision 1.3  2013/09/23 23:53:52  twc9438
 * All done. About to submit.
 *
 * Revision 1.2  2013/09/23 23:37:55  twc9438
 * Fully functional working code. Just need to finish up my javadoc's then submit.
 *
 * Revision 1.1  2013/09/21 20:23:33  twc9438
 * Started to implement LinkedQueue. Javadoc's need done.
 *
 *
 */

/**
 * An implementation of a priority queue that uses linked nodes.
 * 
 * @author Troy Caro (twc9438@rit.edu)
 * @param <T> Objects in the LinkedList Priority Queue
 *
 */
public class LinkedQueue<T extends Prioritizable> implements PriorityQueue<T> {
	/**
	 * Private Linked List Node data structure.
	 * 
	 * @author Troy Caro (twc9438@rit.edu)
	 *
	 */
	private class Node {
		public Node(T o) {
			this.data = o;
			this.next = null;
		}
		
		/**
		 * Instance variables.
		 */
		public T data;
		public Node next;
	}
	
	/**
	 * Instance variables.
	 */
	private Node head, tail;
	
	/**
	 * Constructor call.
	 */
	public LinkedQueue() {
		this.head = null;
		this.tail = null;
	}
	
	/**
	 * Is there anything in the queue?
	 * 
	 * @return queue is empty.
	 */
	public boolean isEmpty() {
		return head == null && tail == null;
	}
	
	/**
	 * Add an item to the queue (at the appropriate location).
	 * 
	 * @param toInsert Item to be added
	 */
	public void insert(T toInsert) {
		Node previous = null;
		Node current = this.head;
		while (current != null && current.data.getPriority() >= toInsert.getPriority()){
			previous = current;
			current = current.next;
		}
		Node newNode = new Node(toInsert);
		if (previous == null) {
			newNode.next = this.head;
			this.head = newNode;
		}
		else {
			newNode.next = current;
			previous.next = newNode;
		}
	}
	
	/**
	 * Removes and returns the item at the front of the queue.
	 * 
	 * @return Removed element
	 */
	public T dequeue() {
		if (isEmpty()) {
			return null;
		}
		T tmp = head.data;
		head = head.next;
		/* Last element in the queue */
		if (head == null) {
			tail = null;
		}
		return tmp;
	}
}
