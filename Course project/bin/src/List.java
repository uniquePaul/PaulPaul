/**
 * Defines a doubly-linked list class

 * @author Chris Lai
 * @author Wen Yan
 * CIS22C Lab 4
 */

import java.util.NoSuchElementException;


public class List<T extends Comparable<T> >  {

	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
			
		}
	}

	private int length;
	private Node first;
	private Node last;
	private Node iterator;

	/****CONSTRUCTOR****/

    /**
     * Instantiates a new List with default values
     * @postcondition we get a new list with 0 nodes and no firsts and lasts
     */
	
    public List() {
    	length = 0;
    	first = null;
    	last = null;
    	iterator = null;	
    }
    
	/**
	 * Instantiates a new List by copying another List
	 * @param original the List to make a copy of
	 * @postcondition a new List object, which is an identical
	 * but separate copy of the List original
	 */
	public List(List<T> original) {
    	if(original.length == 0) {
    		length = 0;
    		first = null;
    		last = null;
    		iterator = null;
    		
    	}else {
    		Node temp = original.first;
    		
    		while(temp != null) {
    			addLast(temp.data);
    			temp = temp.next;
    		}
    		
    		iterator = null;
    	}
	}

	public void printListDebug() {

		if( iterator == null ) {
			System.out.println("iterator: null");
		}else {
			System.out.println("iterator: "+iterator.data);
		}

		if( first == null ) {
			System.out.println("first: NONE");
		}else {
			System.out.println("first: "+first.data);

		}

		if( last == null ) {
			System.out.println("last: NONE");
		}else {
			System.out.println("last: "+last.data);

		}

		Node n = first;
		while( n != null ) {
			System.out.println("-----");
			System.out.println("value:"+n.data);
			if( n.next != null ) {
				System.out.println("next:"+n.next.data);

			}else {
				System.out.println("value: NONE");

			}

			if( n.prev != null ) {
				System.out.println("prev:"+n.prev.data);

			}else {
				System.out.println("prev: NONE");

			}
			n = n.next;

		}
		System.out.println("*** List Debug Print ***");

	}
	
	
	/****ACCESSORS****/

	
	/**
	 * Returns the value stored in the first node
	 * @precondition:!isEmpty() 
	 * @return the value stored at node first
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getFirst() throws NoSuchElementException{
		if (isEmpty()) {
			throw new NoSuchElementException("getFirst: List is Empty. No data to access!");
		}
		return first.data;
	}

	/**
	 * Returns the value stored in the last node
	 * @precondition:!isEmpty()  
	 * @return the value stored in the node last
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getLast() throws NoSuchElementException{
		if (isEmpty()) {
			throw new NoSuchElementException("getFirst: List is Empty. No data to access!");
		}
		return last.data;
	}

    /**
     * Returns the element currently pointed at by the iterator
     * @precondition iterator != null
     * @return the value pointed at by the iterator
     * @throws NullPointerException when the precondition is violated
     */
    public T getIterator() throws NullPointerException {
        if(offEnd()) {
        	throw new NullPointerException("getIterator(): Iterator is off end. Cannot return data.");
        }   
        return iterator.data;
    }
    
	/**
	 * Returns the current length of the list
	 * @return the length of the list from 0 to n
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns whether the list is currently empty
	 * @return whether the list is empty
	 */
    public boolean isEmpty() {
        return length == 0;
    }
    
    /**  
     * Returns whether or not the iterator is off the end of the list
     * i.e. is NULL
     * @return true if the iterator is null
     */
    public boolean offEnd() {
        return iterator == null;
    }

	/**
	 * Overrides the equals method for object to compares this list to another list to see if they contain the same data in the same order
	 * @return the List as a String for display
	 */
	@SuppressWarnings("unchecked")
	@Override public boolean equals(Object o) {
		if(o == this) {
			return true;
		} else if (!(o instanceof List)) {
			return false;
		} else {
			List<T> L = (List<T>)o; 
			if (this.length != L.length) {
				return false;
			} else {
				Node temp1 = this.first;
				Node temp2 = L.first;
				while (temp1 != null) { //Lists are same length
					if (!(temp1.data.equals(temp2.data))) {
						return false;
					}
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
				return true;
			}
		}

	} 

	/**
	 * Determines whether a List is sorted
	 * by calling its recursive helper method
	 * isSorted
	 * Note: An empty List can be
	 * considered to be (trivially) sorted
	 * @return whether this List is sorted
	 */
	public boolean inSortedOrder() {
		return inSortedOrder(first);

	}
	
	/**
	 * Helper method to inSortedOrder 
	 * Determines whether a List is 
	 * sorted in ascending order recursively
	 * @return whether this List is sorted
	 */
	private boolean inSortedOrder(Node node) {
		if(length == 0 || node == null ) {
			return true;
		}
		if( node.next != null && node.data.compareTo(node.next.data) > 0) {
			return false;
		}
		return inSortedOrder(node.next);	
	}

	/**
     * Uses the iterative linear search
     * algorithm to locate a specific
     * element in the list
     * @param element the value to search for
     * @return the location of value in the
     * List or -1 to indicate not found
     * Note that if the List is empty we will
     * consider the element to be not found
     * @postcondition: position of the iterator remains
     * unchanged!
     */
    public int linearSearch(T element) {

    	Node temp = first;
    	int place = 1;
    	
        if(temp == null)
        {
        	return -1;
        }
        while(temp!=null)
        {
        	if(!element.equals(temp.data)) {
        	temp = temp.next;
        	place++;
        	} else {
        		return place;
        	}
        }

        return -1;
        
    	
    }
    /**
     * Returns the index from 1 to length
     * where value is located in the List
     * by calling the private helper method
     * binarySearch
     * @param value the value to search for
     * @return the index where value is 
     * stored from 1 to length, or -1 to
     * indicate not found
     * @precondition isSorted()
     * @postcondition the position of the
     * iterator must remain unchanged! 
     * @throws IllegalStateException when the
     * precondition is violated.
     */
    public int binarySearch(T value) throws IllegalStateException {
        if(!inSortedOrder()) {
        	throw new IllegalStateException("binarySearch :List not sorted!");
        }
    	
    	return binarySearch(1,length,value);
    }
    
    /**
     * Searches for the specified value in
     * the List by implementing the recursive
     * binarySearch algorithm
     * @param low the lowest bounds of the search
     * @param high the highest bounds of the search
     * @param value the value to search for
     * @return the index at which value is located
     * or -1 to indicate not found
     * @postcondition the location of the iterator
     * must remain unchanged
     */
    private int binarySearch(int low, int high, T value) {
    	if( high < low ) {
    		return -1;
    	}
    	
    	int mid = low + (high - low)/2;
    	Node temp = first;
    	for(int i = 1; i < mid; i++) {
    		temp = temp.next;
    	}
    	
    	int val = temp.data.compareTo(value);
    	if( val == 0 ){
    		return mid;
    	}
    	if( val > 0 ) {
    		return binarySearch(low, mid-1,value);
    	}else {
    		return binarySearch(mid+1,high,value);
    	}
       
    }	
	
	/**
     * Returns the index of the iterator
     * from 1 to n. Note that there is 
     * no index 0. Does not use recursion.
     * @precondition offEnd != true
     * @return the index of the iterator
     * @throws NullPointerException when
     * the precondition is violated
     */
    public int getIndex() throws NullPointerException{
        if(offEnd())
        {
        	throw new NullPointerException("getIndex(): "+"Cannot get index of iterator if it is null");
        }
        
        int index = 0;
        do {
        	this.reverseIterator();
        	index++;
        	
        }while(offEnd() == false );
    	
    	return index;
    			
    }

    
	/****MUTATORS****/

	/**
	 * Creates a new first element
	 * @param data the data to insert at the front of the list
	 * @postcondition: a new first node is created
	 */
	public void addFirst(T data) {
		if (first == null) {

			first = last = new Node(data);
		} else {
			Node N = new Node(data);
			N.next=first;
			N.prev=null;
			first.prev=N;
			first = N;
		}
		length++;
	}

	/**
	 * Creates a new last element
	 * @param data the data to insert at the
	 * end of the list
	 * @postcondition: a new last node is created
	 */
	public void addLast(T data) {

		if(last==null)
		{
			first= last= new  Node(data);
		}
		else {
			Node N= new Node(data);

			last.next=N;
			N.prev = last;
			last=N;
		}
		length++;
		return;
	}

	/**
	 * removes the element at the front of the list
	 * @precondition: first!=null 
	 * @postcondition: First node in the list is removed
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeFirst() throws NoSuchElementException{
		if (first==null) {
			throw new NoSuchElementException("removeFirst(): Cannot remove "
					+ "from an empty List!");
		} 

		if(length==1)
		{
			first=last=null;
			length--;
			return;
		}

		first=first.next;
		first.prev=null;
		length--; 
		return;
	}

	/**
	 * Removes the element at the end of the list
	 * @precondition: last!=null
	 * @postcondition: Last node in the list is removed
	 * @throws NoSuchElementException when precondition is violated
	 * fix with iterator
	 */
	public void removeLast() throws NoSuchElementException{
		if(isEmpty())
		{
			throw new NoSuchElementException("removeLast(): "+"Cannot remove from an empty list");
		}
		else if(length==1)
		{
			last=first=null;
			length--;
			return;
		}
		else {
			Node temp=first;
			while(temp.next!=last)
			{
				temp=temp.next;
			}
			last=temp;
			last.next=null;
		}


		length--;
		return;

	}

	/**
	 * Removes the element currently pointed to by the iterator
	 * @precondition iterator != null
	 * @postcondition iterator then points to NULL
	 * @throws NullPointerException
	 */
	public void removeIterator() {

		if(offEnd()==true)
		{
			throw new NullPointerException("removeIterator()"+"Cannot remove the iterator when it equals null");
		}
		else if(iterator==first){
			removeFirst();
		}
		else if(iterator==last)
		{
			removeLast();
		}
		else {

			iterator.prev.next=iterator.next;
			iterator.next.prev = iterator.prev;
			length--;
		}
		iterator=null;
	}
	
	/**
	 * Moves the iterator to the start of the list
	 * @precondition isEmpty!=true
	 * @postcondition iterator points to the first element in the list
	 * @throws NullPointerException
	 */
	public void placeIterator()
	{
		if(isEmpty()==true)
		{
			throw new NoSuchElementException("Cannot place "
					+ "an iterator on a empty list!");
		}
		iterator=first;
	}
	
	/**
	 * Moves iterator up by one node
	 * @precondition !isEmpty
	 * @postcondition iterator moves up by one node
	 * @throws NullPointerException
	 */
	public void advanceIterator()throws NullPointerException
	{
		if(length==0)
		{
			throw new NullPointerException("Cannot advance "
					+ "an iterator on a empty list.");
		}

		iterator=iterator.next;
	}
	
	/**
	 * Moves the iterator down by one node
	 * @precondition length>1
	 * @postcondition iterator moves down by one node
	 * @throws NoSuchElementException
	 */
	public void reverseIterator()
	{
		if(length==0)
		{
			throw new NoSuchElementException("Cannot advance "
					+ "an iterator on a empty list.");
		}
		iterator=iterator.prev;
	}

	/**
	     * Places the iterator at first
	     * and then iteratively advances 
	     * it to the specified index
	     * no recursion
	     * @param index the index where
	     * the iterator should be placed
	     * @precondition 1 <= index <= length
	     * @throws IndexOutOfBoundsException
	     * when precondition is violated
	     */
	    public void advanceToIndex(int index) throws IndexOutOfBoundsException{
	        placeIterator();
	        if(index<1 || index>length)
	        {
	        	throw new IndexOutOfBoundsException("advanceToIndex(): "+"Index is out of bounds of the list");
	        }
	        while(iterator!=null)
	        {
	        	index--;
	        	if(  index > 0 ) {
	        		iterator=iterator.next;
	        	}else {
	        		return;
	        	}
	        	
	        	
	        }
	        
	    }

	/**
	 * Adds the element at the node
	 * referenced by the iterator
	 * @precondition iterator != null
	 * @throws NullPointerElementException
	 */
	public void addIterator(T data)throws NullPointerException
	{
		if(offEnd()==true) 
		{
			throw new NullPointerException("Iterator is outside the bounds of the list");
		}
		Node N=new Node(data);
		if(iterator==last)
		{
			addLast(data);
			return;
		}

		if( iterator.next != null ) {
			iterator.next.prev = N;
		}

		N.prev=iterator;
		N.next = iterator.next;
		iterator.next=N;
		length++;
	}
	
	
	/****ADDITIONAL OPERATIONS****/
	/**
	 * Prints a linked list to the console
	 * in reverse by calling the private 
	 * recursive helper method printInReverse
	 */
	
	public void printInReverse() {
		printInReverse(last);
		return;
	}

	/**
	 * Recursively prints a linked list to the console
	 * in reverse order from last to first (no loops)
	 * Each element separated by a space
	 * Should print a new line after all
	 * elements have been displayed
	 */    

	private void printInReverse(Node node) {
		if(node == null) {
			System.out.println();
			return;
		}
    	System.out.print(node.data + " ");
    	printInReverse(node.prev);
	}

	/**
	 * Overrides the toString method for object to make a String with each element in the List separated by a blank line
	 * List with each value on its own line
	 * At the end of the List a new line
	 * @return the List as a String for display
	 */
	@Override public String toString() {
		String result = "";
		Node temp = first;
		while(temp != null) {
			result += temp.data.toString() ;
			result += " ";
			temp=temp.next;

		}
		return result;
	}



}