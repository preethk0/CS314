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
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 * <p>
 * The data type for E must be a type that implements Comparable.
 * <p>
 * Students are to implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must
 * be used as the internal storage container. For methods involving two set,
 * if that method can be done more efficiently if the other set is also a
 * SortedSet do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {
	private static final int NOT_FOUND = -1;
	private ArrayList<E> con;
    //O(1), simply initializes the con
    /**
     * create an empty SortedSet
     */
	public SortedSet() {
		// init con
		con = new ArrayList<>();
	}

    //O(NlogN) because the parameter set must be sorted first
    /**
     * create a SortedSet out of an unsorted set. <br>
     *
     * @param other != null
     */
	public SortedSet(ISet<E> other) {
		// check precon
		if (other == null) {
			throw new IllegalArgumentException("otherSet can't be null");
		}

		// fill con with elements from other
		con = new ArrayList<>();
		for (E item : other) {
			con.add(item);
		}

		// sort con
		quickSort(con, 0, con.size() - 1);
	}

    //O(1) because the container reference is simply being set
	private SortedSet(ArrayList<E> sortedConWithoutDupes) {
		con = sortedConWithoutDupes;
	}

    //quicksort method from slides
    //O(1) swap method
	private void swapReferences(ArrayList<E> a, int index1, int index2) {
		E temp = a.get(index1);
		a.set(index1, a.get(index2));
		a.set(index2, temp);
	}

    //O(NlogN) - typical quicksort algorithm adapted from slides
	private void quickSort(ArrayList<E> list, int start, int stop) {
		if (start >= stop)
			return; // base case list of 0 or 1 elements

		int pivotIndex = (start + stop) / 2;

		// Place pivot at start position
		swapReferences(list, pivotIndex, start);
		E pivot = list.get(start);

		// Begin partitioning
		int i, j = start;

		// from first to j are elements less than or equal to pivot
		// from j to i are elements greater than pivot
		// elements beyond i have not been checked yet
		for (i = start + 1; i <= stop; i++) { // is current element less than or equal to pivot
			if (list.get(i).compareTo(pivot) <= 0) { // if so move it to the less than or equal portion
				j++;
				swapReferences(list, i, j);
			}
		}

		// restore pivot to correct spot
		swapReferences(list, start, j);
		quickSort(list, start, j - 1); // Sort small elements
		quickSort(list, j + 1, stop); // Sort large elements
	}

    //O(1): simply returns the first element in the array
    /**
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     *
     * @return the smallest element in this SortedSet.
     */
	public E min() {
		// because it's sorted, first element will always be least
		return con.get(0);
	}

    //O(1): simply returns the last element in the array
    /**
     * Return the largest element in this SortedSet.
     * <br> pre: size() != 0
     *
     * @return the largest element in this SortedSet.
     */
	public E max() {
		// because it's sorted, last element will always be greatest
		return con.get(con.size() - 1);
	}

    //O(N) - contains is O(log N), but the while loop could, worst-case cause O(N) performance
    //       causing the need to either shift over every element by adding at the front or
    //       adding at the back after going through every element, so O(N) is the dominating term
    @Override
	public boolean add(E item) {
		// check precon
		if (item == null) {
			throw new IllegalArgumentException("item can't be null");
		}

		// add only if set doesn't already contain item
		if (!contains(item)) {
			// iterate through con until a valid spot for item is found
			int index = 0;
			while (index < con.size() && item.compareTo(con.get(index)) > 0) {
				index++;
			}

			con.add(index, item);
			return true;
		}

		// if set already contained item, return false (set unaltered)
		return false;

	}

    //O(N) (if otherSet is a SortedSet) because we are doing a single pass through of this and the other set
    //     this is possible because it is very similar to a merge algorithm from mergesort, which is O(N)
    //O(NlogN) if otherSet is not a SortedSet because the sorting cost of the SortedSet constructor dominates
    //         the other O(N) part of the method, resulting in O(N log N) performance
    @Override
	public boolean addAll(ISet<E> otherSet) {
		if (otherSet instanceof SortedSet) {
			SortedSet<E> other = (SortedSet<E>) otherSet;
			ArrayList<E> newCon = new ArrayList<>(con.size() + other.con.size());
			int thisConIndex = 0;
			int otherConIndex = 0;
			boolean changed = false; // store boolean to determine if new elements were added
			// merge algorithm very similar to that from mergesort
			// go through both cons simultaneously
			while (thisConIndex < con.size() && otherConIndex < other.con.size()) {
				int compare = other.con.get(otherConIndex).compareTo(con.get(thisConIndex));
				// add min(con(thisConIndex), other.con(otherConIndex))
				if (compare < 0) {
					newCon.add(other.con.get(otherConIndex));
					otherConIndex++;
					changed = true;
				} else if (compare > 0) {
					newCon.add(con.get(thisConIndex));
					thisConIndex++;
					changed = true;
				} else { // if elements are the same, add one of them and increment both indices
					newCon.add(con.get(thisConIndex));
					thisConIndex++;
					otherConIndex++;
				}
			}

			changed = addRemaining(other, newCon, thisConIndex, otherConIndex, changed);

			// set con to the newCon in which elements were merged
			con = newCon;
			return changed;
		}

		// if param is unsorted, sort it and then call the same method
		return addAll(new SortedSet<>(otherSet));
	}

	// helper method that adds the remaining elements from the list that is not empty yet
	private boolean addRemaining(SortedSet<E> other, ArrayList<E> newCon, int thisConIndex, int otherConIndex,
			boolean changed) {
		// after initial loop, <= 1 of the cons has elements leftover
		// these two while loops deal with these leftover elements
		while (thisConIndex < con.size()) {
			newCon.add(con.get(thisConIndex));
			thisConIndex++;
		}

		while (otherConIndex < other.con.size()) {
			newCon.add(other.con.get(otherConIndex));
			otherConIndex++;
			changed = true;
		}
		return changed;
	}

    @Override
    //O(N) - array.clear() is O(N) because every element must be reset
	public void clear() {
		con.clear();
	}

    @Override
    //O(log N) because binSearch() is O(log N) because it keeps halving the search space until the item
    //         is found or determined to not be present within the array/set
	public boolean contains(E item) {
		// check precon
		if (item == null) {
			throw new IllegalArgumentException("item can't be null");
		}

		// if binSearch finds the item, this set contains the item
		return binSearch(item) != NOT_FOUND;
	}

    //O(log N) - keeps halving the search space until the item
    //           is found or determined to not be present within the array/set
    //binary search algorithm
	private int binSearch(E item) {
		int high = con.size() - 1;
		int low = 0;
		while (low <= high) {
			int mid = (low + high) / 2;
			// if found element, return its index
			if (con.get(mid).equals(item)) {
				return mid;
			}

            //if element is lower than mid, adjust search space to the lower half of current search space
			if (item.compareTo(con.get(mid)) < 0) {
				high = mid - 1;
            } else { //if element is higher than mid, adjust search space to the higher half of the current search space
				low = mid + 1;
			}
		}

		// if never found item, return NOT_FOUND (-1)
		return NOT_FOUND;
	}

    //O(N) - despite the nested while loop, because index isn't constantly getting reset to 0, this
    //       method only passes through each set once (possible because they are sorted, similar to
    //       the merge algorithm in addAll()).
    //O(NlogN) if otherSet is not a sorted set - the superclass method calls contains() on every element
    //         of otherSet; contains() is O(log N); when called N times, this results in
    //         O(NlogN) performance
    @Override
	public boolean containsAll(ISet<E> otherSet) {
		// check precon
		if (otherSet == null) {
			throw new IllegalArgumentException("otherSEt can't be null");
		}

		if (otherSet instanceof SortedSet) {
			SortedSet<E> other = (SortedSet<E>) otherSet;
			int index = 0;
			int otherIndex = 0;
			// go through each element of other
			while (otherIndex < other.con.size()) {
				// loop from current index (not start; current is fine because sorted)
				// to find element from other in this set
				while (index < con.size() && !con.get(index).equals(other.con.get(otherIndex))) {
					index++;
				}

				// if element wasn't found, early return false
				if (index == con.size()) {
					return false;
				}

				otherIndex++;
				index++;
			}

			// if element was always found, return true
			return true;
		}

		// if param is unsorted, sort it and then call the same method
		return containsAll(new SortedSet<>(otherSet));
	}

    //O(N) - similar to the merge algorithm, despite the nested while loop, each set is gone through only once
    //O(NlogN) if otherSet isn't sorted, because the method simply creates a sorted set out of the unsorted set and
    //         then calls the same O(N) difference method; the sorting cost dominates the method
    @Override
	public ISet<E> difference(ISet<E> otherSet) {
		if (otherSet instanceof SortedSet) {
			SortedSet<E> other = (SortedSet<E>) otherSet;
			ArrayList<E> outputCon = new ArrayList<>(con.size());
			int index = 0;
			int otherIndex = 0;
			// loop through every element of this set
			while (index < con.size()) {
				// loop through other from current index (not 0, because sorted) until element >= current is found
				while (otherIndex < other.con.size() && con.get(index).compareTo(other.con.get(otherIndex)) > 0) {
					otherIndex++;
				}

				// if element >= current not found, then because it's sorted
				// we can add the rest of the current set to the new set and return early
				if (otherIndex == other.con.size()) {
					while (index < con.size()) {
						outputCon.add(con.get(index));
						index++;
					}

					return new SortedSet<>(outputCon);
				} else if (!con.get(index).equals(other.con.get(otherIndex))) {
					// if element >= current found, add to new set ONLY if element from otherSet != current
					outputCon.add(con.get(index));
				}

				index++;
			}

			// custom constructor to create sorted sets by just setting the con.
			return new SortedSet<>(outputCon);
		}

		// if param is unsorted, sort it and then call the same method
		return difference(new SortedSet<>(otherSet));
	}

    //O(N) - not overridden because this method simply uses difference() and union() to create the
    //       intersection as implemented in AbstractSet, and all these methods are O(N), so
    //       this method is also O(N)
//    @Override
//    public ISet<E> intersection(ISet<E> otherSet) {
//        return null;
//    }

    //O(1) - simply creates an iterator and points to the first element in the container
    @Override
	public Iterator<E> iterator() {
		return con.iterator();
	}

    //O(N) - arraylist remove() is O(N) because elements may have to be shifted over when removing
    //       in the worst case, N elements will be shifted, resulting in O(N) performance despite the binary search
    @Override
	public boolean remove(E item) {
		// check precon
		if (item == null) {
			throw new IllegalArgumentException("item can't be null");
		}

		// binary search for index
		int indexToRemove = binSearch(item);
		if (indexToRemove != NOT_FOUND) {
			// if item is in con, remove it
			con.remove(indexToRemove);
		}

		// return if item was in con
		return indexToRemove != NOT_FOUND;
	}

    //O(1) - ArrayList size() is O(1) (probably just stores it in an instance var)
    @Override
	public int size() {
		return con.size();
	}

    //O(N) if otherSet is sorted - this simply uses addAll(), which is O(N), so this is also O(N)
    //O(NlogN) if otherSet isn't sorted, we sort it in the SortedSet constructor and then call union
    //         so the sorting cost dominates, resulting in O(NlogN) performance
    @Override
	public ISet<E> union(ISet<E> otherSet) {
		if (otherSet instanceof SortedSet) {
			SortedSet<E> other = (SortedSet<E>) otherSet;
			SortedSet<E> output = new SortedSet<>(this);
			// add all of this set and otherSet to output and return
			output.addAll(other);
			return output;
		}

		// if param is unsorted, sort it and then call the same method
		return union(new SortedSet<>(otherSet));
	}

    //O(N) if o is a sorted set, because ArrayList.equals() is O(N) (compare every element once)
    //O(Nlog N) if o isn't sorted, because the O(NlogN) sorting cost of the SortedSet constructor dominates
    //          the O(N) ArrayList.equals() cost, resulting in O(NlogN) performance
    @Override
	public boolean equals(Object o) {
		// compare references
		if (this == o) {
			return true;
		}

		// must be an ISet
		if (!(o instanceof ISet)) {
			return false;
		}

		// compare sizes first
		if (((ISet<?>) o).size() != size()) {
			return false;
		}

		// if sorted set, call ArrayList.equals()
		if (o instanceof SortedSet) {
			SortedSet<?> sortedSet = (SortedSet<?>) o;
			return con.equals(sortedSet.con);
		}

		// if unsorted set, create a sorted set and call equals again
		return equals(new SortedSet<>((ISet<E>) o));
	}
}