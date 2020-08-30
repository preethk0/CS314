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

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;


/*
CS 314 Students, put your results to the experiments and answers to
questions here:

SortedSet:
Title                  Size      Total Words      Distinct Words      Time
----------------------------------------------------------------------------------
Le Horror Altissime    59KB      9536             3192                0.0647 seconds
Tablets				   245KB     39650            10864               0.1692 seconds
Kai Lung's Golden	   503KB     85137            14851               0.5754 seconds
Hours
The Nabob 			   885KB     154871           23516               1.1739 seconds

AS A FACTOR OF PREV FILE:
Title                  Size      Total Words      Distinct Words      Time
----------------------------------------------------------------------------------
Small Sample		   8KB       1546             725                 0.0116 seconds 
Le Horror Altissime    7.4X      6.2X             4.4X                5.6X seconds
Tablets				   4.2X      4.2X             3.4X                2.6X seconds
Kai Lung's Golden	   2.1X      2.1X             1.4X                3.4X seconds
Hours
The Nabob 			   1.8X      1.8X             1.6X                2X seconds

UnsortedSet:
Title                  Size      Total Words      Distinct Words      Time
----------------------------------------------------------------------------------
Le Horror Altissime    59KB      9536             3192                0.1113 seconds
Tablets				   245KB     39650            10864               0.4783 seconds
Kai Lung's Golden	   503KB     85137            14851               1.0828 seconds
Hours
The Nabob 			   885KB     154871           23516               2.5048 seconds

AS A FACTOR OF PREV FILE:
Title                  Size      Total Words      Distinct Words      Time
----------------------------------------------------------------------------------
Small Sample		   8KB       1546             725                 0.0081 seconds 
Le Horror Altissime    7.4X      6.2X             4.4X                13.7X seconds
Tablets				   4.2X      4.2X             3.4X                4.3X seconds
Kai Lung's Golden	   2.1X      2.1X             1.4X                2.3X seconds
Hours
The Nabob 			   1.8X      1.8X             1.6X                2.3X seconds

HashSet:
Title                  Size      Total Words      Distinct Words      Time
----------------------------------------------------------------------------------
Le Horror Altissime    59KB      9536             3192                0.0049 seconds
Tablets				   245KB     39650            10864               0.0261 seconds
Kai Lung's Golden	   503KB     85137            14851               0.0451 seconds
Hours
The Nabob 			   885KB     154871           23516               0.0799 seconds

AS A FACTOR OF PREV FILE:
Title                  Size      Total Words      Distinct Words      Time
----------------------------------------------------------------------------------
Small Sample		   8KB       1546             725                 0.0015 seconds 
Le Horror Altissime    7.4X      6.2X             4.4X                3.3X seconds
Tablets				   4.2X      4.2X             3.4X                5.3X seconds
Kai Lung's Golden	   2.1X      2.1X             1.4X                1.7X seconds
Hours
The Nabob 			   1.8X      1.8X             1.6X                1.8X seconds

TreeSet:
Title                  Size      Total Words      Distinct Words      Time
----------------------------------------------------------------------------------
Le Horror Altissime    59KB      9536             3192                0.0088 seconds
Tablets				   245KB     39650            10864               0.0349 seconds
Kai Lung's Golden	   503KB     85137            14851               0.0611 seconds
Hours
The Nabob 			   885KB     154871           23516               0.1080 seconds

AS A FACTOR OF PREV FILE:
Title                  Size      Total Words      Distinct Words      Time
----------------------------------------------------------------------------------
Small Sample		   8KB       1546             725                 0.0028 seconds 
Le Horror Altissime    7.4X      6.2X             4.4X                3.1X seconds
Tablets				   4.2X      4.2X             3.4X                4.0X seconds
Kai Lung's Golden	   2.1X      2.1X             1.4X                1.8X seconds
Hours
The Nabob 			   1.8X      1.8X             1.6X                1.8X seconds

What do you think the order (Big O) of the two processText methods are for each kind of Set? 
For SortedSet: O(N * M)
For UnsortedSet: O(N * M)
For HashSet: O(N)
For TreeSet: O(Nlog(N))
*THESE WERE DETERMINED BY THE TIMING DATA ABOVE*

What are the orders (Big O) of your add methods? What do you think the Big O of the HashSet and TreeSet add methods are?
For SortedSet: O(N)
For UnsortedSet: O(N)
For HashSet: O(1)
For TreeSet: O(log(N))
*THESE WERE DETERMINED BY THE CODE FROM EACH METHOD*

What are the differences between HashSet and TreeSet when printing out the contents of the Set?
HashSet: Prints out elements in a seemingly random order.
TreeSet: Prints out elements in sorted order.

CS314 Students, why is it unwise to implement all three of the
intersection, union, and difference methods in the AbstractSet class:

This is only possible by causing each method to rely on each other. ie, intersection calls
union and difference, union calls intersection and difference, etc. This will cause a cycle of
dependency which may cause an infinite loop. Ex.

Let's say intersection() was called
Then union() will be called. If both union and intersection are in AbstractSet, union()
will use intersection again, which will use union again, etc etc. This cycle of dependency
could cause infinite loops.

*/

public class SetTester {

    public static void main(String[] args){   
    	ISet<Integer> unsortedOne = new UnsortedSet<Integer>();
        unsortedOne.add(2);
        unsortedOne.add(11);
        unsortedOne.add(7);

        ISet<Integer> sortedOne = new SortedSet<Integer>();
        sortedOne.add(2);
        sortedOne.add(11);
        sortedOne.add(7);

        String unsortedString = "(2, 11, 7)";
        String sortedString = "(2, 7, 11)";
        
        //Test 1
        if (unsortedOne.toString().equals(unsortedString))
        	System.out.println("Passed Test 1");
        else
        	System.out.println("FAILED Test 1 - UnsortedSet Constructor (default)");
        
        //Test 2
        if (sortedOne.toString().equals(sortedString))
        	System.out.println("Passed Test 2");
        else
        	System.out.println("FAILED Test 2 - SortedSet Constructor (default)");
        
        ISet<Integer> unsortedFromSorted = new UnsortedSet<Integer>();
        unsortedFromSorted.addAll(sortedOne);
        ISet<Integer> sortedFromUnsorted = new SortedSet<Integer>(unsortedOne);
        
        //Test 3
        if (unsortedFromSorted.toString().equals(sortedString))
        	System.out.println("Passed Test 3");
        else
        	System.out.println("FAILED Test 3 - UnsortedSet Constructor (given SortedSet)");
        
        //Test4
        if (sortedFromUnsorted.toString().equals(sortedString))
        	System.out.println("Passed Test 4");
        else
        	System.out.println("FAILED Test 4 - SortedSet Constructor (given UnsortedSet)");
        
        //Contains Tests [5-10]
        
        //Test 5
        if (unsortedOne.contains(11))
        	System.out.println("Passed Test 5");
        else
        	System.out.println("FAILED Test 5 - SortedSet Contains");
        
        //Test 6
        if (sortedOne.contains(11))
        	System.out.println("Passed Test 6");
        else
        	System.out.println("FAILED Test 6 - SortedSet Contains");
        
        //Test 7
        if (!unsortedOne.contains(8))
        	System.out.println("Passed Test 7");
        else
        	System.out.println("FAILED Test 7 - UnsortedSet Contains");
        
        //Test 8
        if (!sortedOne.contains(8))
        	System.out.println("Passed Test 8");
        else
        	System.out.println("FAILED Test 8 - SortedSet Contains");
        
        //Test 9
        if (unsortedOne.containsAll(unsortedFromSorted))
        	System.out.println("Passed Test 9");
        else
        	System.out.println("FAILED Test 9 - UnsortedSet ContainsAll");
        
        //Test 10
        if (sortedOne.containsAll(unsortedFromSorted))
        	System.out.println("Passed Test 10");
        else
        	System.out.println("FAILED Test 10 - UnsortedSet ContainsAll");
        
        //Clear Tests [11-12]
        
        //Test 11
        unsortedFromSorted.clear();
        if (unsortedFromSorted.size() == 0)
        	System.out.println("Passed Test 11");
        else 
        	System.out.println("FAILED Test 11 - UnsortedSet Clear");
        
        //Test 12
        sortedFromUnsorted.clear();
        if (sortedFromUnsorted.size() == 0)
        	System.out.println("Passed Test 12");
        else
        	System.out.println("FAILED Test 12 - SortedSet Clear");
        
        //Remove Tests [13-16]
        //Test 13
        if (!unsortedOne.remove(8))
        	System.out.println("Passed Test 13");
        else
        	System.out.println("FAILED Test 13 - UnsortedSet Remove");
        
        //Test 14
        if (!sortedOne.remove(8))
        	System.out.println("Passed Test 14");
        else
        	System.out.println("FAILED Test 14 - SortedSet Remove");
        
        //Add Tests [17-20]
        
        //Test 17
        unsortedOne.add(11);
        unsortedString = "(2, 11, 7)";
        if (unsortedOne.toString().equals(unsortedString))
        	System.out.println("Passed Test 17");
        else
        	System.out.println("FAILED Test 17 - UnsortedSet Add");
        
        //Test 18
        sortedOne.add(11);
        sortedString = "(2, 7, 11)";
        if (sortedOne.toString().equals(sortedString))
        	System.out.println("Passed Test 18");
        else
        	System.out.println("FAILED Test 18 - SortedSet Add");
        
        //Test 19
        unsortedFromSorted.addAll(sortedOne);
        if (unsortedFromSorted.toString().equals(sortedString))
        	System.out.println("Passed Test 19");
        else
        	System.out.println("FAILED Test 19 - UnsortedSet AddAll");
        
        //Test 20
        sortedFromUnsorted.addAll(unsortedOne);
        sortedString = "(2, 7, 11)";
        if (sortedFromUnsorted.toString().equals(sortedString))
        	System.out.println("Passed Test 20");
        else
        	System.out.println("FAILED Test 20 - SortedSet AddAll");
        
        //Size Tests [21-22]
        
        //Test 21
        int realSize = 3;
        if (unsortedOne.size() == realSize)
        	System.out.println("Passed Test 21");
        else
        	System.out.println("FAILED Test 21 - UnsortedSet Size");
        
        //Test 22
        if (sortedOne.size() == realSize)
        	System.out.println("Passed Test 22");
        else
        	System.out.println("FAILED Test 22 - SortedSet Size");
        
        //Iterator Tests [23-24]
        
        //Test 23
        Iterator<Integer> it = unsortedOne.iterator();
        if (it.hasNext()) {
        	it.next();
        	it.next();
        	if (it.next() == 7)
        		System.out.println("Passed Test 23");
            else
            	System.out.println("FAILED Test 23 - UnsortedSet Iterator (next)");
        } else System.out.println("FAILED Test 23 - UnsortedSet Iterator (hasNext)");
        
        //Test 24
        it = sortedOne.iterator();
        if (it.hasNext()) {
        	it.next();
        	it.next();
        	if (it.next() == 11)
        		System.out.println("Passed Test 24");
            else
            	System.out.println("FAILED Test 24 - SortedSet Iterator (next)");
        } else System.out.println("FAILED Test 24 - SortedSet Iterator (hasNext)");
        
        //Difference Tests [25-26]
        
        //Test 25
        unsortedFromSorted = unsortedOne.difference(sortedOne);
        unsortedString = "()";
        if (unsortedFromSorted.toString().equals(unsortedString))
        	System.out.println("Passed Test 25");
        else
        	System.out.println("FAILED Test 25 - UnsortedSet Difference");
        
        //Test 26
        sortedFromUnsorted = sortedOne.difference(unsortedOne);
        sortedString = "()";
        if (sortedFromUnsorted.toString().equals(sortedString))
        	System.out.println("Passed Test 26");
        else
        	System.out.println("FAILED Test 26 - SortedSet Difference");
        
        //Test 27
        ISet<Integer> resultISet = unsortedFromSorted.difference(unsortedOne);
        unsortedString = "()";
        if (resultISet.toString().equals(unsortedString))
        	System.out.println("Passed Test 27");
        else
        	System.out.println("FAILED Test 27 - UnsortedSet Difference");
        
        //Test 28
        resultISet = sortedFromUnsorted.difference(sortedOne);
        if (resultISet.toString().equals(unsortedString))
        	System.out.println("Passed Test 28");
        else
        	System.out.println("FAILED Test 28 - SortedSet Difference");
        
        //Test 29
        resultISet = unsortedFromSorted.difference(sortedFromUnsorted);
        unsortedString = "()";
        if (resultISet.toString().equals(unsortedString))
        	System.out.println("Passed Test 29");
        else
        	System.out.println("FAILED Test 29 - UnsortedSet Difference");
        
        //Test 30
        resultISet = sortedFromUnsorted.difference(unsortedFromSorted);
        sortedString = "()";
        if (resultISet.toString().equals(sortedString))
        	System.out.println("Passed Test 30");
        else
        	System.out.println("FAILED Test 30 - SortedSet Difference");
        
        //Intersection Tests [31-36]
        
        //Test 31
        resultISet = unsortedOne.intersection(sortedOne);
        unsortedString = "(2, 11, 7)";
        if (resultISet.toString().equals(unsortedString))
        	System.out.println("Passed Test 31");
        else
        	System.out.println("FAILED Test 31 - UnsortedSet Intersection");
        
        //Test 32

        resultISet = sortedOne.intersection(unsortedOne);
        sortedString = "(2, 7, 11)";
        if (resultISet.toString().equals(sortedString))
        	System.out.println("Passed Test 32");
        else
        	System.out.println("FAILED Test 32 - SortedSet Intersection");
        
        //Test 33
        resultISet = unsortedFromSorted.intersection(sortedFromUnsorted);
        unsortedString = "()";
        if (resultISet.toString().equals(unsortedString))
        	System.out.println("Passed Test 33");
        else
        	System.out.println("FAILED Test 33 - UnsortedSet Intersection");
        
        //Test 34
        resultISet = sortedFromUnsorted.intersection(unsortedFromSorted);
        if (resultISet.toString().equals(unsortedString))
        	System.out.println("Passed Test 34");
        else
        	System.out.println("FAILED Test 34 - SortedSet Intersection");
        
        //Test 35
        resultISet = unsortedOne.intersection(unsortedOne);
        unsortedString = "(2, 11, 7)";
        if (resultISet.toString().equals(unsortedString))
        	System.out.println("Passed Test 35");
        else
        	System.out.println("FAILED Test 35 - UnsortedSet Intersection");
        
        //Test 36
        resultISet = sortedOne.intersection(sortedOne);
        sortedString = "(2, 7, 11)";
        if (resultISet.toString().equals(sortedString))
        	System.out.println("Passed Test 36");
        else
        	System.out.println("FAILED Test 36 - SortedSet Intersection");
        
        //Union Tests [37-42]
        
        //Test 37
        resultISet = unsortedOne.union(unsortedOne);
        if (resultISet.toString().equals(unsortedString))
        	System.out.println("Passed Test 37");
        else
        	System.out.println("FAILED Test 37 - UnsortedSet Union");
        
        //Test 38
        resultISet = sortedOne.union(sortedOne);
        if (resultISet.toString().equals(sortedString))
        	System.out.println("Passed Test 38");
        else
        	System.out.println("FAILED Test 38 - SortedSet Union");
        
        //Test 39
        resultISet = unsortedOne.union(sortedOne);
        unsortedString = "(2, 11, 7)"; 
        if (resultISet.toString().equals(unsortedString))
        	System.out.println("Passed Test 39");
        else
        	System.out.println("FAILED Test 39 - UnsortedSet Union");
        
        //Test 40
        resultISet = sortedOne.union(unsortedOne);
        sortedString = "(2, 7, 11)";
        if (resultISet.toString().equals(sortedString))
        	System.out.println("Passed Test 40");
        else
        	System.out.println("FAILED Test 40 - SortedSet Union");
        
        //Test 41
        sortedFromUnsorted.clear();
        unsortedFromSorted.clear();
        resultISet = unsortedFromSorted.union(sortedFromUnsorted);
        unsortedString = "()";
        if (resultISet.toString().equals(unsortedString))
        	System.out.println("Passed Test 41");
        else
        	System.out.println("FAILED Test 41 - SortedSet Union");
        
        //Test 42
        resultISet = sortedFromUnsorted.union(unsortedFromSorted);
        if (resultISet.toString().equals(unsortedString))
        	System.out.println("Passed Test 42");
        else
        	System.out.println("FAILED Test 42 - SortedSet Union");
        
        //Min and Max Tests [43-44]
        
        //Test 43
        Integer bound = ((SortedSet<Integer>) sortedOne).min();
        if (bound == 2)
        	System.out.println("Passed Test 43");
        else
        	System.out.println("FAILED Test 43 - Sorted Min");
        
        //Test 44
        bound = ((SortedSet<Integer>) sortedOne).max();
        if (bound == 11)
        	System.out.println("Passed Test 44");
        else
        	System.out.println("FAILED Test 44 - Sorted Min");
        
        //Equals Tests [45-50]
        
        //Test 45
        sortedFromUnsorted.addAll(unsortedOne);
        if (unsortedOne.equals(sortedFromUnsorted))
        	System.out.println("Passed Test 45");
        else
        	System.out.println("FAILED Test 45 - UnsortedSet Equals (self, sorted)");
        
        //Test 46
        if (unsortedOne.equals(sortedOne))
        	System.out.println("Passed Test 46");
        else
        	System.out.println("FAILED Test 46 - UnsortedSet Equals (different, sorted)");
        
        //Test 47
        if (sortedOne.equals(sortedFromUnsorted))
        	System.out.println("Passed Test 47");
        else
        	System.out.println("FAILED Test 47 - SortedSet Equals (different, sorted)");
        
        //Test 48
        if (sortedOne.equals(sortedOne))
        	System.out.println("Passed Test 48");
        else
        	System.out.println("FAILED Test 48 - SortedSet Equals (self)");
        
        //Test 49
        ISet<String> stringSet = new UnsortedSet<String>();
        stringSet.add("2");
        if (!unsortedOne.equals(stringSet))
        	System.out.println("Passed Test 49");
        else
        	System.out.println("FAILED Test 49 - UnsortedSet Equals (other data type)");
        
        //Test 50
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("2");
        if (!sortedOne.equals(arrayList))
        	System.out.println("Passed Test 50");
        else
        	System.out.println("FAILED Test 50 - SortedSet Equals (other Object)");   
    }

    /*
     * Method asks user for file and compares run times to add words from file to
     * various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest(){
        System.out.println();
        System.out.println("Opening Window to select file. You may have to minimize other windows.");
        String text = convertFileToString();
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets( new HashSet<String>(), text);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets( new TreeSet<String>(), text);
    }

    
    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for CS314 sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }


    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for Java Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }
    
    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, 
            int totalWords, int setSize) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString() );
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);


        System.out.print("Enter y to see the contents of this set: ");
        Scanner sc = new Scanner(System.in);
        String response = sc.next();

        if( response != null && response.length() > 0 && response.substring(0,1).equalsIgnoreCase("y") ){
            for(Object o : set)
                System.out.println(o);
        }	
        System.out.println();
    }


    /*
     * Ask user to pick a file via a file choosing window and
     * convert that file to a String. Since we are evalutatin the file
     * with many sets convert to string once instead of reading through
     * file multiple times.
     */
    private static String convertFileToString() {
        //create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        //read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            try {
                System.out.println(source.getName());
                Scanner s = new Scanner( new FileReader( source ) );

                while( s.hasNextLine() ) {
                    text.append( s.nextLine() );
                    text.append(" ");
                }

                s.close();
            }
            catch(IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            }
        }

        return text.toString();
    }
}