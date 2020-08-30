/*  Student information for assignment:
*
*  On my honor, Preeth Kanamangala, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  Name: Preeth Kanamangala
*  email address: preeth.kanamangala@gmail.com
*  UTEID: PK9297
*  Grader name: Amir
*  Number of slip days used on this assignment: 1
*/

/*
 * ADDING AT END. ArrayList (O(1)) vs LinkedList (O(1)). BOTH are about the same.
 * ADDING AT FRONT. ArrayList (O(N)) vs LinkedList (O(1)). LINKEDLIST is faster.
 * REMOVING FROM FRONT. ArrayList (O(N)) vs LinkedList (O(1)). LINKEDLIST is faster.
 * GETTING RANDOM. ArrayList (O(1)) vs LinkedList (O(N)). ARRAYLIST is faster.
 * GETTING ALL USING ITERATOR. ArrayList (O(1)) vs LinkedList (O(1)). BOTH are about the same.
 * GETTING ALL USING METHOD. ArrayList (O(1)) vs LinkedList (O(N)). ARRAYLIST is faster.
 * 
 * The Big O of each operation was determined whether the total time doubled with each N doubling (O(1)) or quadrupled with each N doubling (O(N)).
 */

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class LinkedListTester {

	public static void main(String[] args) {

		LinkedList<String> list = new LinkedList<>();
		Object[] actual;
		Object[] expected;

		// test 1.1: add to end
		System.out.println("\nTest 1.1: add to end");
		list.add("Banana");
		if (list.toString().equals("[Banana]"))
			System.out.println("Passed test 1.1");
		else
			System.out.println("Failed test 1.1");

		// test 1.2: add to end
		System.out.println("\nTest 1.2: add to end");
		list.add("Apple");
		if (list.get(1).equals("Apple"))
			System.out.println("Passed test 1.2");
		else
			System.out.println("Failed test 1.2");

		// test 1.3: add to end
		System.out.println("\nTest 1.3: add to end");
		list.add("Panda");
		if (list.toString().equals("[Banana, Apple, Panda]"))
			System.out.println("Passed test 1.3");
		else
			System.out.println("Failed test 1.3");

		// test 2.1: insert
		System.out.println("\nTest 2.1: insert");
		list.insert(0, "Bear");
		if (list.size() == 4)
			System.out.println("Passed test 2.1");
		else
			System.out.println("Failed test 2.1");

		// test 2.2: insert
		System.out.println("\nTest 2.2: insert");
		list.insert(4, "Eagle");
		if (list.toString().equals("[Bear, Banana, Apple, Panda, Eagle]"))
			System.out.println("Passed test 2.2");
		else
			System.out.println("Failed test 2.2");

		// test 2.3: insert
		System.out.println("\nTest 2.3: insert");
		list.insert(4, "Potato");
		if (list.get(4).equals("Potato"))
			System.out.println("Passed test 2.3");
		else
			System.out.println("Failed test 2.3");

		// test 3.1: set
		System.out.println("\nTest 3.1: set");
		String data = list.set(4, "Flower");
		if (data.equals("Potato"))
			System.out.println("Passed test 3.1");
		else
			System.out.println("Failed test 3.1");

		// test 3.2: set
		System.out.println("\nTest 3.2: set");
		list.set(0, "Tree");
		if (list.toString().equals("[Tree, Banana, Apple, Panda, Flower, Eagle]"))
			System.out.println("Passed test 3.2");
		else
			System.out.println("Failed test 3.2");

		// test 3.3: set
		System.out.println("\nTest 3.3: set");
		list.set(5, "Turtle");
		if (list.toString().equals("[Tree, Banana, Apple, Panda, Flower, Turtle]"))
			System.out.println("Passed test 3.3");
		else
			System.out.println("Failed test 3.3");

		// test 4.1: get
		System.out.println("\nTest 4.1: get");
		if (list.get(5).equals("Turtle"))
			System.out.println("Passed test 4.1");
		else
			System.out.println("Failed test 4.1");

		// test 4.2: get
		System.out.println("\nTest 4.2: get");
		if (list.get(1).equals("Banana"))
			System.out.println("Passed test 4.2");
		else
			System.out.println("Failed test 4.2");

		// test 4.3: get
		System.out.println("\nTest 4.3: get");
		if (list.get(0).equals("Tree"))
			System.out.println("Passed test 4.3");
		else
			System.out.println("Failed test 4.3");

		// test 5.1: remove
		System.out.println("\nTest 5.1: remove");
		String result = list.remove(5);
		if (list.toString().equals("[Tree, Banana, Apple, Panda, Flower]"))
			System.out.println("Passed test 5.1");
		else
			System.out.println("Failed test 5.1");

		// test 5.2: remove
		System.out.println("\nTest 5.2: remove");
		if (result.equals("Turtle"))
			System.out.println("Passed test 5.2");
		else
			System.out.println("Failed test 5.2");

		// test 5.3: remove
		System.out.println("\nTest 5.3: remove");
		list.remove(0);
		if (list.toString().equals("[Banana, Apple, Panda, Flower]"))
			System.out.println("Passed test 5.3");
		else
			System.out.println("Failed test 5.3");

		// test 6.1: remove(E obj)
		System.out.println("\nTest 6.1: remove(E obj)");
		boolean removed = list.remove("Potato");
		if (removed == false)
			System.out.println("Passed test 6.1");
		else
			System.out.println("Failed test 6.1");

		// test 6.2: remove(E obj)
		System.out.println("\nTest 6.2: remove(E obj)");
		boolean removed2 = list.remove("Apple");
		actual = toArray(list);
		expected = new Object[] { "Banana", "Panda", "Flower" };
		if (arraysSame(actual, expected))
			System.out.println("Passed test 6.2");
		else
			System.out.println("Failed test 6.2");

		// test 6.3: remove(E obj)
		System.out.println("\nTest 6.3: remove(E obj)");
		list.remove("Zebra");
		if (removed2 == true)
			System.out.println("Passed test 6.3");
		else
			System.out.println("Failed test 6.3");

		// test 7.1: getSubList
		System.out.println("\nTest 7.1: getSubList");
		IList<String> resultList = list.getSubList(0, 1);
		if (resultList.toString().equals("[Banana]"))
			System.out.println("Passed test 7.1");
		else
			System.out.println("Failed test 7.1");

		// test 7.2: getSubList
		System.out.println("\nTest 7.2: getSubList");
		if (list.getSubList(1, 3).toString().equals("[Panda, Flower]"))
			System.out.println("Passed test 7.2");
		else
			System.out.println("Failed test 7.2");

		// test 7.3: getSubList
		System.out.println("\nTest 7.3: getSubList");
		if (list.getSubList(0, 0).toString().equals("[]"))
			System.out.println("Passed test 7.3");
		else
			System.out.println("Failed test 7.3");

		// test 8.1: size
		System.out.println("\nTest 8.1: size");
		if (list.getSubList(0, 1).size() == 1)
			System.out.println("Passed test 8.1");
		else
			System.out.println("Failed test 8.1");

		// test 8.2: size
		System.out.println("\nTest 8.2: size");
		list = new LinkedList<>();
		if (list.size() == 0)
			System.out.println("Passed test 8.2");
		else
			System.out.println("Failed test 8.2");

		// test 8.3: size
		System.out.println("\nTest 8.3: size");
		list.add("Ryan");
		list.add("Bob");
		list.add("Jeff");
		list.add("Caleb");
		list.add("Justin");
		list.add("Mary");
		list.removeLast();
		if (list.size() == 5)
			System.out.println("Passed test 8.3");
		else
			System.out.println("Failed test 8.3");

		// test 9.1: indexOf
		System.out.println("\nTest 9.1: indexOf");
		list.makeEmpty();
		list.add("Son");
		list.add("Mother");
		list.remove("Mother");
		if (list.indexOf("Son") == 0)
			System.out.println("Passed test 9.1");
		else
			System.out.println("Failed test 9.1");

		// test 9.2: indexOf
		System.out.println("\nTest 9.2: indexOf");
		if (list.indexOf("Mother") == -1)
			System.out.println("Passed test 9.2");
		else
			System.out.println("Failed test 9.2");

		// test 9.3: indexOf
		System.out.println("\nTest 9.3: indexOf");
		list.add("Father");
		if (list.indexOf("Father") == 1)
			System.out.println("Passed test 9.3");
		else
			System.out.println("Failed test 9.3");

		// test 10.1: indexOf((E item, int pos)
		System.out.println("\nTest 10.1: indexOf((E item, int pos)");
		list.add("Son");
		if (list.indexOf("Son", 0) == 0)
			System.out.println("Passed test 10.1");
		else
			System.out.println("Failed test 10.1");

		// test 10.2: indexOf((E item, int pos)
		System.out.println("\nTest 10.2: indexOf((E item, int pos)");
		if (list.indexOf("Son", 1) == 2)
			System.out.println("Passed test 10.2");
		else
			System.out.println("Failed test 10.2");

		// test 10.3: indexOf((E item, int pos)
		System.out.println("\nTest 10.3: indexOf((E item, int pos)");
		if (list.indexOf("Mother", 0) == -1)
			System.out.println("Passed test 10.3");
		else
			System.out.println("Failed test 10.3");

		// test 11.1: makeEmpty
		System.out.println("\nTest 11.1: makeEmpty");
		list.makeEmpty();
		if (list.size() == 0)
			System.out.println("Passed test 11.1");
		else
			System.out.println("Failed test 11.1");

		// test 11.2: makeEmpty
		System.out.println("\nTest 11.2: makeEmpty");
		if (list.toString().equals("[]"))
			System.out.println("Passed test 11.2");
		else
			System.out.println("Failed test 11.2");

		// test 11.3: makeEmpty
		System.out.println("\nTest 11.3: makeEmpty");
		list.add("Pear");
		list.add("Grapes");
		list.makeEmpty();
		if (list.indexOf("Grapes") == -1)
			System.out.println("Passed test 11.3");
		else
			System.out.println("Failed test 11.3");

		// test 12.1: removeRange
		System.out.println("\nTest 12.1: removeRange");
		list.add("D");
		list.add("A");
		list.add("T");
		list.add("A");
		list.add("S");
		list.add("T");
		list.add("R");
		list.add("U");
		list.add("C");
		list.removeRange(0, 8);
		if (list.toString().equals("[C]"))
			System.out.println("Passed test 12.1");
		else
			System.out.println("Failed test 12.1");

		// test 12.2: removeRange
		System.out.println("\nTest 12.2: removeRange");
		list.makeEmpty();
		list.add("D");
		list.add("A");
		list.add("T");
		list.add("A");
		list.add("S");
		list.add("T");
		list.add("R");
		list.add("U");
		list.add("C");
		list.removeRange(4, 9);
		if (list.toString().equals("[D, A, T, A]"))
			System.out.println("Passed test 12.2");
		else
			System.out.println("Failed test 12.2");

		// test 12.3: removeRange
		System.out.println("\nTest 12.3: removeRange");
		list.removeRange(3, 3);
		if (list.size() == 4)
			System.out.println("Passed test 12.3");
		else
			System.out.println("Failed test 12.3");

		// test 13.1: toString
		System.out.println("\nTest 13.1: toString");
		list.makeEmpty();
		list.add("Plant");
		if (list.toString().equals("[Plant]"))
			System.out.println("Passed test 13.1");
		else
			System.out.println("Failed test 13.1");

		// test 13.2: toString
		System.out.println("\nTest 13.2: toString");
		list.add("Record");
		list.insert(1, "Bush");
		list.removeLast();
		if (list.toString().equals("[Plant, Bush]"))
			System.out.println("Passed test 13.2");
		else
			System.out.println("Failed test 13.2");

		// test 13.3: toString
		System.out.println("\nTest 13.3: toString");
		list.remove("Plant");
		list.remove("Bush");
		if (list.toString().equals("[]"))
			System.out.println("Passed test 13.3");
		else
			System.out.println("Failed test 13.3");

		// test 14.1: equals
		System.out.println("\nTest 14.1: equals");
		list = new LinkedList<>();
		list.add("10");
		list.add("20");
		ArrayList<String> array = new ArrayList<>();
		array.add("10");
		array.add("20");
		if (list.equals(array) == false)
			System.out.println("Passed test 14.1");
		else
			System.out.println("Failed test 14.1");

		// test 14.2: equals
		System.out.println("\nTest 14.2: equals");
		list.makeEmpty();
		list.add("314");
		LinkedList<Integer> other = new LinkedList<>();
		other.add(314);
		if (list.equals(other) == false)
			System.out.println("Passed test 14.2");
		else
			System.out.println("Failed test 14.2");

		// test 14.3: equals
		System.out.println("\nTest 14.3: equals");
		list.add("414");
		LinkedList<String> other2 = new LinkedList<>();
		other2.add("314");
		other2.add("414");
		if (list.equals(other2) == true)
			System.out.println("Passed test 14.3");
		else
			System.out.println("Failed test 14.3");

		// test 15.1: addFirst
		System.out.println("\nTest 15.1: addFirst");
		list.addFirst("CS");
		actual = toArray(list);
		expected = new Object[] { "CS", "314", "414" };
		System.out.println("Expected result: " + Arrays.toString(expected));
		System.out.println("Actual result: " + Arrays.toString(actual));
		if (arraysSame(actual, expected))
			System.out.println("Passed test 15.1");
		else
			System.out.println("Failed test 15.1");

		// test 15.2: addFirst
		System.out.println("\nTest 15.2: addFirst");
		list.addFirst("Mike Scott");
		if (list.size() == 4)
			System.out.println("Passed test 15.2");
		else
			System.out.println("Failed test 15.2");

		// test 15.3: addFirst
		System.out.println("\nTest 15.3: addFirst");
		list.addFirst("UT Austin");
		if (list.get(4).equals("414"))
			System.out.println("Passed test 15.3");
		else
			System.out.println("Failed test 15.3");

		// test 16.1: addLast
		System.out.println("\nTest 16.1: addLast");
		list.makeEmpty();
		list.addLast("WOW");
		actual = toArray(list);
		expected = new Object[] { "WOW" };
		System.out.println("Expected result: " + Arrays.toString(expected));
		System.out.println("Actual result: " + Arrays.toString(actual));
		if (arraysSame(actual, expected))
			System.out.println("Passed test 16.1");
		else
			System.out.println("Failed test 16.1");

		// test 16.2: addLast
		System.out.println("\nTest 16.2: addLast");
		list.addLast("AMAZING");
		actual = toArray(list);
		expected = new Object[] { "WOW", "AMAZING" };
		System.out.println("Expected result: " + Arrays.toString(expected));
		System.out.println("Actual result: " + Arrays.toString(actual));
		if (arraysSame(actual, expected))
			System.out.println("Passed test 16.2");
		else
			System.out.println("Failed test 16.2");

		// test 16.3: addLast
		System.out.println("\nTest 16.3: addLast");
		list.addLast("FANTASTIC");
		if (list.size() == 3)
			System.out.println("Passed test 16.3");
		else
			System.out.println("Failed test 16.3");

		// test 17.1: removeFirst
		System.out.println("\nTest 17.1: removeFirst");
		list.removeFirst();
		actual = toArray(list);
		expected = new Object[] { "AMAZING", "FANTASTIC" };
		System.out.println("Expected result: " + Arrays.toString(expected));
		System.out.println("Actual result: " + Arrays.toString(actual));
		if (arraysSame(actual, expected))
			System.out.println("Passed test 17.1");
		else
			System.out.println("Failed test 17.1");

		// test 17.2: removeFirst
		System.out.println("\nTest 17.2: removeFirst");
		list.removeFirst();
		list.add("WOW");
		actual = toArray(list);
		expected = new Object[] { "FANTASTIC", "WOW" };
		System.out.println("Expected result: " + Arrays.toString(expected));
		System.out.println("Actual result: " + Arrays.toString(actual));
		if (arraysSame(actual, expected))
			System.out.println("Passed test 17.2");
		else
			System.out.println("Failed test 17.2");

		// test 17.3: removeFirst
		System.out.println("\nTest 17.3: removeFirst");
		list.removeFirst();
		if (list.size() == 1)
			System.out.println("Passed test 17.3");
		else
			System.out.println("Failed test 17.3");

		// test 18.1: removeLast
		System.out.println("\nTest 18.1: removeLast");
		list.add("AMAZING");
		list.removeLast();
		actual = toArray(list);
		expected = new Object[] { "WOW" };
		System.out.println("Expected result: " + Arrays.toString(expected));
		System.out.println("Actual result: " + Arrays.toString(actual));
		if (arraysSame(actual, expected))
			System.out.println("Passed test 18.1");
		else
			System.out.println("Failed test 18.1");

		// test 18.2: removeLast
		System.out.println("\nTest 18.2: removeLast");
		list.add("CS");
		list.add("UT");
		list.removeLast();
		actual = toArray(list);
		expected = new Object[] { "WOW", "CS" };
		System.out.println("Expected result: " + Arrays.toString(expected));
		System.out.println("Actual result: " + Arrays.toString(actual));
		if (arraysSame(actual, expected))
			System.out.println("Passed test 18.2");
		else
			System.out.println("Failed test 18.2");

		// test 18.3: removeLast
		System.out.println("\nTest 18.3: removeLast");
		list.removeLast();
		if (list.size() == 1)
			System.out.println("Passed test 18.3");
		else
			System.out.println("Failed test 18.3");

		// test 19.1: Iterator hasNext
		System.out.println("\nTest 19.1: iterator hasNext");
		list.makeEmpty();
		list.add("TESTING");
		Iterator<String> it = list.iterator();
		if (it.hasNext())
			System.out.println("Passed test 19.1");
		else
			System.out.println("Failed test 19.1");

		// test 19.2: Iterator hasNext
		System.out.println("\nTest 19.2: iterator hasNext");
		list.add("AGAIN");
		Iterator<String> it2 = list.iterator();
		it2.next();
		it2.next();
		if (!it2.hasNext())
			System.out.println("Passed test 19.2");
		else
			System.out.println("Failed test 19.2");

		// test 19.3: Iterator hasNext
		System.out.println("\nTest 19.2: iterator hasNext");
		list.remove("AGAIN");
		list.remove("TESTING");
		Iterator<String> it3 = list.iterator();
		if (!it3.hasNext())
			System.out.println("Passed test 19.3");
		else
			System.out.println("Failed test 19.3");

		// test 20.1: Iterator next
		System.out.println("\nTest 20.1: iterator next");
		list.makeEmpty();
		list.add("ITEM1");
		list.add("ITEM2");
		list.add("ITEM3");
		list.add("ITEM4");
		Iterator<String> it4 = list.iterator();
		String old = it4.next();
		if (old.equals("ITEM1"))
			System.out.println("Passed test 20.1");
		else
			System.out.println("Failed test 20.1");

		// test 20.2: Iterator next
		System.out.println("\nTest 20.2: iterator next");
		String old2 = it4.next();
		if (old2.equals("ITEM2"))
			System.out.println("Passed test 20.2");
		else
			System.out.println("Failed test 20.2");

		// test 20.3: Iterator next
		System.out.println("\nTest 20.3: iterator next");
		String old3 = it4.next();
		if (old3.equals("ITEM3"))
			System.out.println("Passed test 20.3");
		else
			System.out.println("Failed test 20.3");

		// test 21.1: Iterator remove
		System.out.println("\nTest 21.1: iterator remove");
		it4.remove();
		if (list.size() == 3)
			System.out.println("Passed test 21.1");
		else
			System.out.println("Failed test 21.1");

		// test 21.2: Iterator remove
		System.out.println("\nTest 21.2: iterator remove");
		list.makeEmpty();
		list.add("Pillow");
		list.add("Blanket");
		Iterator<String> it5 = list.iterator();
		it5.next();
		it5.next();
		it5.remove();
		String expectedString = "[Pillow]";
		if (list.toString().equals(expectedString))
			System.out.println("Passed test 21.2");
		else
			System.out.println("Failed test 21.2");

		// test 21.3: Iterator remove
		System.out.println("\nTest 21.3: iterator remove");
		Iterator<String> it6 = list.iterator();
		it6.next();
		it6.remove();
		if (list.toString().equals("[]"))
			System.out.println("Passed test 21.3");
		else
			System.out.println("Failed test 21.3");

		comparison();
	}

   private static Object[] toArray(LinkedList<String> list) {
       Object[] result = new Object[list.size()];
       Iterator<String> it = list.iterator();
       int index = 0;
       while( it.hasNext() ){
           result[index] = it.next();
           index++;
       }
       return result;
   }

   //pre: none
   private static boolean arraysSame(Object[] one, Object[] two)  {
       boolean same;
       if( one == null || two == null ) {
           same = (one == two);
       }
       else {
           //neither one or two are null
           assert one != null && two != null;
           same = one.length == two.length;
           if( same ) {
               int index = 0;
               while( index < one.length && same ) {
                   same = ( one[index].equals(two[index]) );
                   index++;
               }
           }
       }
       return same;
   }
  
   
   public static final int NUM_DOUBLINGS_OF_N = 5;
   private static final int NUM_REPEATS_OF_TEST = 100;

   // A method to be run to compare the LinkedList you are completing and the Java ArrayList class
   public static void comparison() {
       Stopwatch s = new Stopwatch();
       
       int initialN = 30000;
       addEndArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
       addEndLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

       initialN = 2000;
       addFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
       initialN = 10000;
       addFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

       initialN = 2000;
       removeFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
       initialN = 5000;
       removeFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

       initialN = 10000;
       getRandomArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
       initialN = 1000;
       getRandomLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

       initialN = 50000;
       getAllArrayListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);
       getAllLinkedListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);

       initialN = 100000;
       getAllArrayListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);
       initialN = 1000;
       getAllLinkedListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);

   }

   // These pairs of method illustarte a failure to use polymorphism
   // If the students had implemented the Java list interface there
   // could be a single method. Also we could use function objects to
   // reduce the awful repitition of code.
   public static void addEndArrayList(Stopwatch s, int initialN, int numTests) {

       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               s.start();
               for (int j = 0; j < n; j++) {
                   javaList.add(j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Adding at end: ArrayList", totalTimes, initialN);
   }

   private static void showResults(String title, double[] times, int initialN) {
       System.out.println();
       System.out.println("Num Repeats: " + NUM_REPEATS_OF_TEST);
       System.out.println(title);
       for (double time : times) {
           System.out.print("N = " + initialN + ", total time: ");
           System.out.printf("%7.4f\n", time);
           initialN *= 2;
       }
       System.out.println();
   }

   public static void addEndLinkedList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList<>();
               s.start();
               for (int j = 0; j < n; j++) {
                   studentList.add(j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Adding at end: LinkedList", totalTimes, initialN);
   }

   public static void addFrontArrayList(Stopwatch s, int initialN, int numTests){

       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               s.start();
               for (int j = 0; j < n; j++) {
                   javaList.add(0, j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Adding at front: ArrayList", totalTimes, initialN);
   }

   public static void addFrontLinkedList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList<>();
               s.start();
               for (int j = 0; j < n; j++) {
                   studentList.insert(0, j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Adding at front: LinkedList", totalTimes, initialN);
   }

   public static void removeFrontArrayList(Stopwatch s, int initialN, int numTests){     
       double[] totalTimes = new double[numTests];        
       for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               ArrayList<String> javaList = new ArrayList<>();
               for (int j = 0; j < n; j++) {
                   javaList.add(j + "");
               }
               s.start();
               while (!javaList.isEmpty()) {
                   javaList.remove(0);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Removing from front: ArrayList", totalTimes, initialN);
   }

   public static void removeFrontLinkedList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;    
           for (int i = 0; i < numTests; i++) {
               LinkedList<String> studentList = new LinkedList<>();
               for (int j = 0; j < n; j++) {
                   studentList.add(j + "");
               }
               s.start();
               while (studentList.size() != 0) {
                   studentList.removeFirst();
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("removing from front: LinkedList", totalTimes, initialN);
   }

   public static void getRandomArrayList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           int total = 0;
           Random r = new Random();
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               for (int j = 0; j < n; j++) {
                   javaList.add(j);
               }
               s.start();
               for (int j = 0; j < n; j++) {
                   total += javaList.get(r.nextInt(javaList.size()));
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting random: ArrayList", totalTimes, initialN);
   }

   public static void getRandomLinkedList(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           int total = 0;
           Random r = new Random();
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList< >();
               for (int j = 0; j < n; j++) {
                   studentList.add(j);
               }
               s.start();
               for (int j = 0; j < n; j++) {
                   total += studentList.get( r.nextInt(studentList.size()) );
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting random: LinkedList", totalTimes, initialN);
   }

   public static void getAllArrayListUsingIterator(Stopwatch s, int initialN, int numTests){

       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           int total = 0;
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               for (int j = 0; j < n; j++) {
                   javaList.add(j);
               }
               Iterator<Integer> it = javaList.iterator();
               s.start();
               while (it.hasNext()) {
                   total += it.next();
               }        
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting all using iterator: ArrayList", totalTimes, initialN);
   }

   public static void getAllLinkedListUsingIterator(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           int total = 0;
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList<>();
               for (int j = 0; j < n; j++) {
                   studentList.add(j);
               }
               Iterator<Integer> it = studentList.iterator();
               s.start();
               while (it.hasNext()) {
                   total += it.next();
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting all using iterator: LinkedList", totalTimes, initialN);
   }

   public static void getAllArrayListUsingGetMethod(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               ArrayList<Integer> javaList = new ArrayList<>();
               for (int j = 0; j < n; j++) {
                   javaList.add(j);
               }
               s.start();
               int x = 0;
               for (int j = 0; j < javaList.size(); j++) {
                   x += javaList.get(j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting all using get method: ArrayList", totalTimes, initialN);
   }

   public static void getAllLinkedListUsingGetMethod(Stopwatch s, int initialN, int numTests){
       double[] totalTimes = new double[numTests];        
       for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
           int n = initialN;
           for (int i = 0; i < numTests; i++) {
               LinkedList<Integer> studentList = new LinkedList<>();
               for (int j = 0; j < n; j++) {
                   studentList.add(j);
               }
               s.start();
               int x = 0;
               for (int j = 0; j < studentList.size(); j++) {
                   x += studentList.get(j);
               }
               s.stop();
               totalTimes[i] += s.time();
               n *= 2;
           }
       }
       showResults("Getting all using get method: LinkedList", totalTimes, initialN);
   }

   // for future changes
   private static interface ArrayListOp {
       public <E> void op(ArrayList<E> list, E data);
   }
   
   private ArrayListOp addAL = new ArrayListOp() {
       public <E> void op(ArrayList<E> list, E data) {
           list.add(data);
       }
   };
   
   private ArrayListOp addFrontAL = new ArrayListOp() {
       public <E> void op(ArrayList<E> list, E data) {
           list.add(0, data);
       }
   };
   
   private ArrayListOp removeFrontAL = new ArrayListOp() {
       public <E> void op(ArrayList<E> list, E data) {
           list.remove(0);
       }
   };

   private static interface LinkedListOp<E> {
       public void op(LinkedList<E> list);
   }
}