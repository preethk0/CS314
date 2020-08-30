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

/**
 * Students are to complete this class. 
 * Students should implement as many methods
 * as they can using the Iterator from the iterator 
 * method and the other methods. 
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {
     
    //provided toString(), O(N) (have to print every element)
	public String toString() {
		StringBuilder result = new StringBuilder();
		String separator = ", ";
		result.append("(");

		Iterator<E> it = this.iterator();
		while (it.hasNext()) {
			result.append(it.next());
			result.append(separator);
		}
		// get rid of extra separator
		if (this.size() > 0)
			result.setLength(result.length() - separator.length());

		result.append(")");
		return result.toString();
	}

    @Override
    //can't do add without internal container
	public boolean add(E item) {
		return false;
	}

    @Override
    //O(N^2) - call add() on every element of otherSet; add() is O(N)
    //         so calling it on every element of otherSet = O(N^2) performance
	public boolean addAll(ISet<E> otherSet) {
		// check precon
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet can't be null");
		}

		// iterate across otherSet, adding elements if they aren't already present
		boolean output = false;
		for (E element : otherSet) {
			if (!contains(element)) {
				add(element);
				output = true;
			}
		}

		return output;
	}

    @Override
    //use remove and next from iterator to clear()
    //iterating across the list like this is O(N) (every element must have remove() called on it)
	public void clear() {
		Iterator<E> iterator = iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
	}

    @Override
    //O(N) - check every element in this set until item is found or determined to not be present
	public boolean contains(E item) {
		// check precon
		if (item == null) {
			throw new IllegalArgumentException("item can't be null");
		}

		// loop through this set to find item
		for (E element : this) {
			// return true early if item found
			if (element.equals(item)) {
				return true;
			}
		}

		return false;
	}

    @Override
    //O(N^2) - for every element in otherSet, call O(N) contains(); N O(N) method calls = O(N^2) performance
	public boolean containsAll(ISet<E> otherSet) {
		// check precon
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSet can't be null");
		}

		// call contains() on every element in otherSet
		for (E element : otherSet) {
			// early return if element not found
			if (!contains(element)) {
				return false;
			}
		}

		return true;
	}

    @Override
	public ISet<E> difference(ISet<E> otherSet) {
		return null;
	}

    @Override
    //O(N^2) because difference() and union() are O(N^2)
    //intersection = union minus differences
	public ISet<E> intersection(ISet<E> otherSet) {
		// check precon
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSEt can't be null");
		}

		// take union and differences
		ISet<E> union = union(otherSet);
		ISet<E> thisDiff = difference(otherSet);
		ISet<E> otherDiff = otherSet.difference(this);

		// subtract differences out, resulting in intersection
		return union.difference(thisDiff).difference(otherDiff);
	}

    @Override
    //can't implement without container
	public Iterator<E> iterator() {
		return null;
	}

    @Override
    //O(N) remove method - iterate using iterator until element found to remove
	public boolean remove(E item) {
		// check precon
		if (item == null) {
			throw new IllegalArgumentException("item can't be null");
		}

		Iterator<E> it = iterator();
		// loop until element found
		// if found, remove it, return true
		while (it.hasNext()) {
			if (it.next().equals(item)) {
				it.remove();
				return true;
			}
		}

		// return false if element not found
		return false;
	}

    @Override
    //O(N) - use iterator to count elements
    //only way to do this without internal container
	public int size() {
		int count = 0;
		for (E ignored : this) {
			count++;
		}

		return count;
	}

    @Override
	public ISet<E> union(ISet<E> otherSet) {
		return null;
	}

    //O(N^2) - nested loop to check if every element of this set is in o somewhere.
    //         need to loop over all of o for every element in this set
	public boolean equals(Object o) {
		// check type
		if (o instanceof AbstractSet) {
			// check sizes first
			if (((AbstractSet<?>) o).size() != size()) {
				return false;
			}

			AbstractSet<?> that = (AbstractSet<?>) o;
			// loop through all elements in this, searching for them in other set
			for (E element : this) {
				boolean found = false;
				Iterator<?> it = that.iterator();
				while (it.hasNext() && !found) {
					if (it.next().equals(element)) {
						found = true;
					}
				}

				if (!found) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}