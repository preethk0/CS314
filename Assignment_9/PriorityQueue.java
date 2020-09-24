package Assignment_9;
/*  Student information for assignment:
 *
 *  On OUR honor, Pranav Eswaran and Preeth Kanamangala, this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used: 1
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID: PVE84
 *  email address: pranave@utexas.edu
 *  Grader name: Amir
 *
 *  Student 2
 *  UTEID: PK9297
 *  email address: preeth@utexas.edu
 *
 */

import java.util.LinkedList;

public class PriorityQueue<E extends Comparable<? super E>> {
    // linked-list based queue
    private LinkedList<E> queue;

    public PriorityQueue() {
        queue = new LinkedList<>();
    }

    public void enqueue(E node) {
        queue.add(getIndex(node), node);
    }

    private int getIndex(E node) {
        int index = 0;
        // loop until index to insert is found
        for (E n : queue) {
            if (n.compareTo(node) > 0) {
                return index;
            }
            index++;
        }

        return index;
    }

    public E dequeue() {
        // return first element in queue, taking it out of the queue
        return queue.pollFirst();
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }

    public int size() {
        return queue.size();
    }
}
