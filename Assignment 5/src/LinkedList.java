
/*
 * Student information for assignment: On my honor, Preeth Kanamangala, this programming
 * assignment is my own work and I have not provided this code to any other
 * student. 
 * UTEID: PK9297
 * email address: preeth.kanamangala@gmail.com
 * Grader name: Amir
 * Number of slip days I am using: 1
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements IList<E> {
	
	private DoubleListNode<E> first;
	private DoubleListNode<E> last;
	private int size;

	// BIG O: O(N).
	// this helper method returns the node at a given position, which must be greater than or equal to zero and less than size.
	public DoubleListNode<E> getNodeAtPos(int pos) {
		if (pos < 0 || pos >= size) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		DoubleListNode<E> nodeAtPos;
		if (pos == size - 1) {
			nodeAtPos = last;
		} else if (pos == 0) {
			nodeAtPos = first;
		} else if (pos < (size / 2)) { // if the position is in the FIRST HALF of the list, then we want to start at first and use getNext()
			nodeAtPos = first;
			for (int i = 0; i < pos; i++) {
				nodeAtPos = nodeAtPos.getNext();
			}
		} else { // if the position is in the LAST HALF of the list, then we want to start at last and use getPrev()
			nodeAtPos = last;
			for (int i = size - 1; i > pos; i--) {
				nodeAtPos = nodeAtPos.getPrev();
			}
		}
		return nodeAtPos;
	}

	// BIG O: O(1).
	/**
	 * Add an item to the end of this list. <br>
	 * pre: item != null <br>
	 * post: size() = old size() + 1, get(size() - 1) = item
	 * 
	 * @param item the data to be added to the end of this list, item != null
	 */
	public void add(E item) {
		if (item == null) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		DoubleListNode<E> newNode = new DoubleListNode<E>(null, item, null);
		if (size == 0) {
			first = newNode;
		} else { // link it to the previous last then set it as the new last
			last.setNext(newNode);
			newNode.setPrev(last);
		}
		last = newNode;
		size++;
	}

	// BIG O: O(N).
	/**
	 * Insert an item at a specified position in the list. <br>
	 * pre: 0 <= pos <= size(), item != null <br>
	 * post: size() = old size() + 1, get(pos) = item, all elements in the list with
	 * a positon >= pos have a position = old position + 1
	 * 
	 * @param pos  the position to insert the data at in the list
	 * @param item the data to add to the list, item != null
	 */
	public void insert(int pos, E item) {
		if (pos < 0 || pos > size || item == null) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		if (pos == size) {
			add(item);
		} else if (pos == 0) {
			addFirst(item);
		} else { // we want to insert a new node in between the current position node and its predecessor then link all three of these nodes together
			DoubleListNode<E> prevNode = getNodeAtPos(pos).getPrev();
			DoubleListNode<E> currNode = getNodeAtPos(pos);
			DoubleListNode<E> newNode = new DoubleListNode<E>(prevNode, item, currNode);
			prevNode.setNext(newNode);
			currNode.setPrev(newNode);
			size++;
		}
	}

	// BIG O: O(N).
	/**
	 * Change the data at the specified position in the list. the old data at that
	 * position is returned. <br>
	 * pre: 0 <= pos < size(), item != null <br>
	 * post: get(pos) = item, return the old get(pos)
	 * 
	 * @param pos  the position in the list to overwrite
	 * @param item the new item that will overwrite the old item, item != null
	 * @return the old data at the specified position
	 */
	public E set(int pos, E item) {
		if (pos < 0 || pos >= size || item == null) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		E oldData = getNodeAtPos(pos).getData();
		getNodeAtPos(pos).setData(item); // changes the data of a node at a given position
		return oldData;
	}

	// BIG O: O(N).
	/**
	 * Get an element from the list. <br>
	 * pre: 0 <= pos < size() <br>
	 * post: return the item at pos
	 * 
	 * @param pos specifies which element to get
	 * @return the element at the specified position in the list
	 */
	public E get(int pos) {
		if (pos < 0 || pos >= size) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		E nodeData;
		if (pos == 0) {
			nodeData = first.getData();
		} else if (pos == size - 1) {
			nodeData = last.getData();
		} else {
			nodeData = getNodeAtPos(pos).getData();
		}
		return nodeData;
	}

	// BIG O: O(N).
	/**
	 * Remove an element in the list based on position. <br>
	 * pre: 0 <= pos < size() <br>
	 * post: size() = old size() - 1, all elements of list with a position > pos
	 * have a position = old position - 1
	 * 
	 * @param pos the position of the element to remove from the list
	 * @return the data at position pos
	 */
	public E remove(int pos) {
		if (pos < 0 || pos >= size) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		E oldData;
		if (pos == 0) {
			oldData = removeFirst();
		} else if (pos == size - 1) {
			oldData = removeLast();
		} else { // gets the nodes adjacent to the node being removed and links them together
			oldData = get(pos);
			DoubleListNode<E> prevNode = getNodeAtPos(pos).getPrev();
			DoubleListNode<E> nextNode = getNodeAtPos(pos).getNext();
			prevNode.setNext(nextNode);
			nextNode.setPrev(prevNode);
			size--;
		}
		return oldData;
	}

	// BIG O: O(N).
	/**
	 * Remove the first occurrence of obj in this list. Return <tt>true</tt> if this
	 * list changed as a result of this call, <tt>false</tt> otherwise. <br>
	 * pre: obj != null <br>
	 * post: if obj is in this list the first occurrence has been removed and size()
	 * = old size() - 1. If obj is not present the list is not altered in any way.
	 * 
	 * @param obj The item to remove from this list. obj != null
	 * @return Return <tt>true</tt> if this list changed as a result of this call,
	 *         <tt>false</tt> otherwise.
	 */
	public boolean remove(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		DoubleListNode<E> temp = first;
		for (int i = 0; i < size; i++) {
			if (temp.getData().equals(obj)) { // the moment that the data of a node equals the object, remove the node at return true
				remove(i);
				return true;
			}
			temp = temp.getNext();
		}
		return false;
	}

	// BIG O: O(N).
	/**
	 * Return a sublist of elements in this list from <tt>start</tt> inclusive to
	 * <tt>stop</tt> exclusive. This list is not changed as a result of this call.
	 * <br>
	 * pre: <tt>0 <= start <= size(), start <= stop <= size()</tt> <br>
	 * post: return a list whose size is stop - start and contains the elements at
	 * positions start through stop - 1 in this list.
	 * 
	 * @param start index of the first element of the sublist.
	 * @param stop  stop - 1 is the index of the last element of the sublist.
	 * @return a list with <tt>stop - start</tt> elements, The elements are from
	 *         positions <tt>start</tt> inclusive to <tt>stop</tt> exclusive in this
	 *         list. If start == stop an empty list is returned.
	 */
	
	public IList<E> getSubList(int start, int stop) {
		if (start < 0 || start > size || start > stop || stop > size) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		IList<E> subList = new LinkedList<E>();
		for (int i = start; i < stop; i++) { // adds all nodes to the subList from START position to STOP position
			subList.add(this.get(i));
		}
		return subList;
	}
	 
	// BIG O: O(1).
	/**
	 * Return the size of this list. In other words the number of elements in this
	 * list. <br>
	 * pre: none <br>
	 * post: return the number of items in this list
	 * 
	 * @return the number of items in this list
	 */
	public int size() {
		return size;
	}

	// BIG O: O(N).
	/**
	 * Find the position of an element in the list. <br>
	 * pre: item != null <br>
	 * post: return the index of the first element equal to item or -1 if item is
	 * not present
	 * 
	 * @param item the element to search for in the list. item != null
	 * @return return the index of the first element equal to item or a -1 if item
	 *         is not present
	 */
	public int indexOf(E item) {
		if (item == null) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		int indexOf = -1;
		DoubleListNode<E> temp = first;
		for (int i = 0; i < size; i++) {
			if (temp.getData().equals(item)) { // if the data in the current node equals the item, immediately return the index of the node
				indexOf = i;
				return indexOf;
			}
			temp = temp.getNext();
		}
		return indexOf;
	}

	// BIG O: O(N).
	/**
	 * find the position of an element in the list starting at a specified position.
	 * <br>
	 * pre: 0 <= pos < size(), item != null <br>
	 * post: return the index of the first element equal to item starting at pos or
	 * -1 if item is not present from position pos onward
	 * 
	 * @param item the element to search for in the list. Item != null
	 * @param pos  the position in the list to start searching from
	 * @return starting from the specified position return the index of the first
	 *         element equal to item or a -1 if item is not present between pos and
	 *         the end of the list
	 */
	public int indexOf(E item, int pos) {
		if (pos < 0 || pos >= size || item == null) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		int indexOf = -1;
		DoubleListNode<E> temp = getNodeAtPos(pos); // this loop has to start searching from this node at the provided position
		for (int i = pos; i < size; i++) {
			if (temp.getData().equals(item)) {
				indexOf = i;
				return indexOf;
			}
			temp = temp.getNext();
		}
		return indexOf;
	}

	// BIG O: O(1).
	/**
	 * return the list to an empty state. <br>
	 * pre: none <br>
	 * post: size() = 0
	 */
	public void makeEmpty() {
		first = null;
		last = null;
		size = 0;
	}

	// BIG O: O(1).
	/**
	 * return an Iterator for this list. <br>
	 * pre: none <br>
	 * post: return an Iterator object for this List
	 */
	public Iterator<E> iterator() {
		return new LinkedIterator();
	}

	private class LinkedIterator implements Iterator<E> {
		private DoubleListNode<E> nodeWithNext; // this variable stores the node that is next in the list
		private int removePos; // this variable stores the position of the current node that can be removed
		private boolean removeOk;

		private LinkedIterator() {
			nodeWithNext = first;
			removePos = -1; // since the position of the node that can be removed lags 1 behind the nodeWithNext
			removeOk = false;
		}

		// BIG O: O(1).
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException("hasNext() is false.");
			}
			E data = nodeWithNext.getData(); // get the data and move the node along the list
			nodeWithNext = nodeWithNext.getNext();
			removePos++;
			removeOk = true;
			return data;
		}

		// BIG O: O(1).
		public boolean hasNext() {
			return nodeWithNext != null;
		}

		// BIG O: O(1).
		public void remove() { 
			if (!removeOk) {
				throw new IllegalStateException("Must call next() first.");
			}

			if (size == 1) { // special special case where there's only one element in the list, remove just clears the list
				makeEmpty();
				size++; // so this cancels out the size = 0 function that makeEmpty() does since we decrement size in this method anyway
			} else if (removePos == 0) { // special case for removing first element, sets the next node as the new first
				nodeWithNext.setPrev(null);
				first = nodeWithNext;
			} else if (removePos == size() - 1) { // special case for removing last element, sets the previous node as the new last
				nodeWithNext = last.getPrev();
				nodeWithNext.setNext(null);
				last = nodeWithNext;
			} else { // normal case where the next node has to go back twice to reach the node previous to the one being removed and link with it
				nodeWithNext.setPrev(nodeWithNext.getPrev().getPrev());
				nodeWithNext.getPrev().getPrev().setNext(nodeWithNext);
			}
			removePos--;
			removeOk = false;
			size--;
		}
	}

	// BIG O: O(N).
	/**
	 * Remove all elements in this list from <tt>start</tt> inclusive to
	 * <tt>stop</tt> exclusive. <br>
	 * pre: <tt>0 <= start < size(), start <= stop <= size()</tt> <br>
	 * post: <tt>size() = old size() - (stop - start)</tt>
	 * 
	 * @param start position at beginning of range of elements to be removed
	 * @param stop  stop - 1 is the position at the end of the range of elements to
	 *              be removed
	 */
	public void removeRange(int start, int stop) {
		if (start < 0 || start >= size || start > stop || stop > size) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		int shift = stop - start;
		DoubleListNode<E> startNode = getNodeAtPos(start);
		DoubleListNode<E> stopNode = new DoubleListNode<E>();
		if (stop != size) { // if the stop == size then we'd have a nullPointerException so we avoid this
			stopNode = getNodeAtPos(stop);
		}

		if (start == 0) { // general case of if we remove some number of nodes from the front, we have to set a new first
			stopNode.setPrev(null);
			first = stopNode;
			if (stop == size - 1) { // if we remove all but one node, we need to set a new last too
				last = stopNode;
			}
		} else if (stop == size) {
			if (start == 0) { // if we remove all nodes, it is the same thing as making the list empty
				makeEmpty();
			} else { // general case of if we remove some number of nodes from the back, we have to set a new last
				startNode.getPrev().setNext(null);
				last = startNode.getPrev();
			}
		} else { // normal case of linking the predecessor of the start node (since inclusive) to the stop node
			startNode.getPrev().setNext(stopNode);
			stopNode.setPrev(startNode.getPrev());
		}
		size = size - shift;
	}

	// BIG O: O(N).
	/**
	 * Return a String version of this list enclosed in square brackets, [].
	 * Elements are in are in order based on position in the list with the first
	 * element first. Adjacent elements are separated by comma's
	 * 
	 * @return a String representation of this IList
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if (size != 0) {
			DoubleListNode<E> temp = first;
			sb.append(temp.getData());
			for (int i = 0; i < size() - 1; i++) { // go through the list and add the data for each node into the string
				sb.append(", ");
				temp = temp.getNext();
				sb.append(temp.getData());
			}
		}
		sb.append("]");
		return sb.toString();
	}

	// BIG O: O(N).
	/**
	 * Determine if this IList is equal to other. Two ILists are equal if they
	 * contain the same elements in the same order.
	 * 
	 * @return true if this IList is equal to other, false otherwise
	 */
	public boolean equals(Object other) {
		boolean result = true;
		
		if (!(other instanceof IList)) { // basic class check
			result = false;
		} else {
			DoubleListNode<E> temp = first;
			LinkedList<?> otherTemp = (LinkedList<?>) other;
			for (int i = 0; i < size; i++) {
				if (!temp.getData().equals(otherTemp.get(i))) { // go through the list and the moment one piece of data does not line up with "other", return false
					return false;
				}
				temp = temp.getNext();
			}
		}
		return result;
	}

	// BIG O: O(1).
	/**
	 * add item to the front of the list. <br>
	 * pre: item != null <br>
	 * post: size() = old size() + 1, get(0) = item
	 * 
	 * @param item the data to add to the front of this list
	 */
	public void addFirst(E item) {
		if (item == null) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		if (size == 0) {
			add(item);
		} else {
			DoubleListNode<E> newNode = new DoubleListNode<E>(null, item, first); // this new node has its next reference set to first and then become the new first
			first.setPrev(newNode);
			first = newNode;
			size++;
		}
	}

	// BIG O: O(1).
	/**
	 * add item to the end of the list. <br>
	 * pre: item != null <br>
	 * post: size() = old size() + 1, get(size() -1) = item
	 * 
	 * @param item the data to add to the end of this list
	 */
	public void addLast(E item) {
		if (item == null) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		add(item); // same as add
	}

	// BIG O: O(1).
	/**
	 * remove and return the first element of this list. <br>
	 * pre: size() > 0 <br>
	 * post: size() = old size() - 1
	 * 
	 * @return the old first element of this list
	 */
	public E removeFirst() {
		if (size <= 0) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		E oldData = first.getData();
		first = first.getNext(); // set the new next as the next of the previous first
		size--;
		return oldData;
	}

	// BIG O: O(1).
	/**
	 * remove and return the last element of this list. <br>
	 * pre: size() > 0 <br>
	 * post: size() = old size() - 1
	 * 
	 * @return the old last element of this list
	 */
	public E removeLast() {
		if (size <= 0) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		E oldData = last.getData();
		last = last.getPrev(); // set the new last as the prev of the previous last
		last.setNext(null);
		size--;
		return oldData;
	}
}