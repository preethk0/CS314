/*  Student information for assignment:
 *
 *  On our honor, Pranav Eswaran and Preeth Kanamangala, this programming assignment is our own work
 *  and we have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID: PK9297
 *  email address: preeth@cs.utexas.edu
 *  Grader name: Amir
 *  Section number: 50240
 *
 *  Student 2
 *  UTEID: pve84
 *  email address: pranave@cs.utexas.edu
 *  Grader name: Amir
 *  Section number: 50240
 *
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * A simple implementation of an ISet.
 * Elements are not in any particular order.
 * Students are to implement methods that
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently.
 * An ArrayList must be used as the internal storage container.
 */
public class UnsortedSet<E> extends AbstractSet<E> {
    private ArrayList<E> con;

    //O(1) constructor - simply initializes con
	public UnsortedSet() {
		con = new ArrayList<>();
	}

    @Override
    //O(N) - calls contains(), which is O(N), and then adds, O(1)
	public boolean add(E item) {
		// check precon
		if (item == null) {
			throw new IllegalArgumentException("item can't be null");
		}

		// add item only if it isn't already in the set
		if (!contains(item)) {
			con.add(item);
			return true;
		}

		return false;
	}

    //O(N^2) - calls add (O(N)) for every element (N elements) in otherSet; N O(N) calls = O(N^2)
//	@Override
//	public boolean addAll(ISet<E> otherSet) {
//
//	}

    @Override
    //O(N) - must reset every element while maintaining capacity
	public void clear() {
		con.clear();
	}

    //use AbstractSet methods for contains() and containsAll()
    //because they are already O(N) and O(N^2) respectively

    //O(N) - iterates over every element until item found or end of list reached
//	@Override
//	public boolean contains(E item) {
//	}

    //O(N^2) - for each element in otherSet, call contains(), resulting in N O(N) method calls -> O(N^2)
//	@Override
//	public boolean containsAll(ISet<E> otherSet) {
//	}

    @Override
    //O(N^2) - for each item in this set, call contains() on otherSet, resulting in N O(N) calls -> O(N^2)
	public ISet<E> difference(ISet<E> otherSet) {
		// check precon
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet can't be null");
		}

		ISet<E> output = new UnsortedSet<>();
		// loop through this set
		for (E item : this) {
			// add only if item isn't in otherSet
			if (!otherSet.contains(item)) {
				output.add(item);
			}
		}

		return output;
	}

    //O(N^2) - uses the difference() and union() methods
//	@Override
//	public ISet<E> intersection(ISet<E> otherSet) {
//		return null;
//	}


    //O(1) simply return the ArrayList iterator
    @Override
	public Iterator<E> iterator() {
		return con.iterator();
	}

    //O(N) - iterate across every element in this set to find item, and then remove it
    //       shifting elements and the iteration can result in O(N) performance
//	@Override
//	public boolean remove(E item) {
//    
//	}

    @Override
    //O(1) - calls ArrayList.size() which is O(1) (stored in instance var of con probably)
	public int size() {
		return con.size();
	}

    @Override
    //O(N^2) - calls addAll() for otherSet and this set; multiple calls of addAll() (which is O(N^2)) causes O(N^2) performance
	public ISet<E> union(ISet<E> otherSet) {
		// check precon
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet can't be null");
		}

		ISet<E> output = new UnsortedSet<>();
		// add to output all elements in this and otherSet
		output.addAll(this);
		output.addAll(otherSet);
		return output;
	}

    //@Override
    //O(N^2) - nested loop to check if every element of this set is in o somewhere.
    //         need to loop over all of o for every element in this set (used in AbstractSet)
//	public boolean equals(Object o) {
//    
//	}
}