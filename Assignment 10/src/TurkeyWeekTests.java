import java.util.Iterator;

// I want sleep and turkey
/*                    .--.
    {\             / q {\
    { `\           \ (-(~`
   { '.{`\          \ \ )
   {'-{ ' \  .-""'-. \ \
   {._{'.' \/       '.) \
   {_.{.   {`            |
   {._{ ' {   ;'-=-.     |
    {-.{.' {  ';-=-.`    /
     {._.{.;    '-=-   .'
      {_.-' `'.__  _,-'
         jgs   |||`
              .='==,

               .--.
              /} p \             /}
             `~)-) /           /` }
              ( / /          /`}.' }
               / / .-'""-.  / ' }-'}
              / (.'       \/ '.'}_.}
             |            `}   .}._}
             |     .-=-';   } ' }_.}
             \    `.-=-;'  } '.}.-}
              '.   -=-'    ;,}._.}
                `-,_  __.'` '-._}
              jgs   `|||
                   .=='=,
  // ASCII art from http://www.ascii-art.de/ascii/t/turkey.txt
 */
public class TurkeyWeekTests {

    public static void main(String[] args) {
    	// In hindsight, I probably should have used a set of strings
    	// so I could make the entire thing turkey themed. Oh well.
    	
    	int testNum = 1;
        ISet<Integer> s1 = new UnsortedSet<>();
        ISet<Integer> s2 = new SortedSet<>();
        
        checkReturn(s1.add(-1), -1);
        checkReturn(s1.add(1), 1);
        checkReturn(!s1.add(1), 1);
        checkReturn(s2.add(-1), -1);
        checkReturn(s2.add(1), 1);
        checkReturn(!s2.add(1), 1);
        
        //test 1 & 2
        testSet(testNum++, s1.contains(-1), "Unsorted - basic add without duplicates");
        testSet(testNum++, s2.contains(-1), "Sorted - basic add without duplicates");
        
        //test 3 & 4
        testSet(testNum++, s1.contains(1), "Unsorted - basic add with duplicates");
        testSet(testNum++, s2.contains(1), "Sorted - basic add with duplicates");

        s1 = new UnsortedSet<>();
        s2 = new SortedSet<>();   
        for (int i = 0; i < 1000; i++) {
        	checkReturn(s1.add(i), i);
        	checkReturn(s2.add(i), i);
        }
        
        //test 5 & 6
        testSet(testNum++, s1.contains(314), "Unsorted - many adds without duplicates");
        testSet(testNum++, s2.contains(314), "Sorted - many add without duplicates");
        
        s1 = new UnsortedSet<>();
        s2 = new SortedSet<>();   
        for (int i = 0; i < 1000; i++) {
        	for (int j = 0; j < 5; j++) {
        		if (j != 0) {
        			checkReturn(!s1.add(i), i);
        			checkReturn(!s2.add(i), i);
        		} else {
        			checkReturn(s1.add(i), i);
        			checkReturn(s2.add(i), i);
        		}
        	}
        }
        
        //test 7 & 8
        testSet(testNum++, s1.contains(311), "Unsorted - many adds with duplicates");
        testSet(testNum++, s2.contains(311), "Sorted - many add with duplicates");
        
        s1 = new UnsortedSet<>();
        s2 = new SortedSet<>();
        
        ISet<Integer> temp = new UnsortedSet<>();
        for (int i = 10; i >= 0; i--) {
        	checkReturn(temp.add(i), i);
        }
        s1.addAll(temp);
        s2.addAll(temp);
        
        //test 9 & 10
        testSet(testNum++, s1.contains(6), "Unsorted - basic addAll without duplicates");
        testSet(testNum++, s2.contains(6), "Sorted - basic addAll without duplicates");
        
        s1.addAll(temp);
        s2.addAll(temp);
        s1.addAll(temp);
        s2.addAll(temp);
        
        //test 11 & 12
        testSet(testNum++, s1.contains(6), "Unsorted - basic addAll with duplicates");
        testSet(testNum++, s2.contains(6), "Sorted - basic addAll with duplicates");
        
        s1 = new UnsortedSet<>();
        s2 = new SortedSet<>();
        temp = new UnsortedSet<>();
        
        for (int i = 1000; i >= 0; i--) {
        	checkReturn(temp.add(i), i);
        }
        s1.addAll(temp);
        s2.addAll(temp);
        
        //test 13 & 14
        testSet(testNum++, s1.contains(314), "Unsorted - large addAll without duplicates");
        testSet(testNum++, s2.contains(314), "Sorted - large addAll without without");
        
        for (int i = 0; i < 5; i++) {
        	s1.addAll(temp);
        	s2.addAll(temp);
        }
        
        //test 15 & 16
        testSet(testNum++, s1.contains(311), "Unsorted - large addAll with duplicates");
        testSet(testNum++, s2.contains(311), "Sorted - large addAll with duplicates");
        
        s1.clear();
        s2.clear();
        
        //test 17 & 18
        testSet(testNum++, !s1.contains(311), "Unsorted - clearing many elements");
        testSet(testNum++, !s2.contains(311), "Sorted - clearing many elements");
        
        s1.clear();
        s2.clear();
        
        //test 19 & 20
        testSet(testNum++, !s1.contains(311), "Unsorted - clearing empty list");
        testSet(testNum++, !s2.contains(311), "Sorted - clearing empty list");
     
        checkReturn(s1.add(1), 1);
        checkReturn(s2.add(1), 1);
        s1.clear();
        s2.clear();
        
        //test 21 & 22
        testSet(testNum++, !s1.contains(1), "Unsorted - clearing 1-element list");
        testSet(testNum++, !s2.contains(1), "Sorted - clearing 1-element list");
       
        //test 23 & 24
        testSet(testNum++, !s1.contains(1), "Unsorted - contains on an empty list");
        testSet(testNum++, !s2.contains(1), "Sorted - contains on an empty list");
        
        checkReturn(s1.add(1), 1);
        checkReturn(s2.add(1), 1);
        
        //test 25 & 26
        testSet(testNum++, s1.contains(1), "Unsorted - contains on 1-element list");
        testSet(testNum++, s2.contains(1), "Sorted - contains on 1-element list");
        
        checkReturn(!s1.add(1), 1);
        checkReturn(!s2.add(1), 1);
        checkReturn(s1.remove(1), 1);
        checkReturn(s2.remove(1), 1);
        
        //test 27 & 28
        testSet(testNum++, !s1.contains(1), "Unsorted - contains on removed element");
        testSet(testNum++, !s2.contains(1), "Sorted - contains on removed element");
        
        s1 = new UnsortedSet<>();
        s2 = new SortedSet<>();
        temp = new UnsortedSet<>();
        
        for (int i = 0; i < 10; i++) {
        	checkReturn(temp.add(i), i);
        }
        s1.addAll(temp);
        s2.addAll(temp);
        
        checkReturn(!s1.add(1), 1);
        checkReturn(!s2.add(1), 1);
        
        //test 29 & 30
        testSet(testNum++, s1.containsAll(temp), "Unsorted - containsAll on simple in order list");
        testSet(testNum++, s2.containsAll(temp), "Sorted - containsAll on simple in order list");
        
        s1 = new UnsortedSet<>();
        s2 = new UnsortedSet<>();
        
        for (int i = 0; i < 9; i++) {
        	checkReturn(s1.add(i), i);
        	checkReturn(s2.add(i), i);
        }
        
        //test 31 & 32
        testSet(testNum++, !s1.containsAll(temp), "Unsorted - containsAll on simple in order list");
        testSet(testNum++, !s2.containsAll(temp), "Sorted - containsAll on simple in order list");
        
        s1 = new UnsortedSet<>();
        s2 = new UnsortedSet<>();
        
        checkReturn(temp.remove(4), 4);
        checkReturn(temp.add(4), 4);
        for (int i = 9; i > 0; i--) {
        	checkReturn(s1.add(i), i);
        	checkReturn(s2.add(i), i);
        }
        
        //test 33 & 34
        testSet(testNum++, !s1.containsAll(temp), "Unsorted - containsAll on out of order list");
        testSet(testNum++, !s2.containsAll(temp), "Sorted - containsAll on out of order list");
        
        checkReturn(s1.add(0), 0);
        checkReturn(s2.add(0), 0);
        
        //test 35 & 36
        testSet(testNum++, s1.containsAll(temp), "Unsorted - containsAll on out of order list");
        testSet(testNum++, s2.containsAll(temp), "Sorted - containsAll on out of order list");
        
        checkReturn(s1.remove(5), 5);
        checkReturn(s2.remove(5), 5);
        
        //test 37 & 38
        testSet(testNum++, !s1.containsAll(temp), "Unsorted - containsAll on out of order list");
        testSet(testNum++, !s2.containsAll(temp), "Sorted - containsAll on out of order list");
        
        s1 = new UnsortedSet<>();
        s2 = new UnsortedSet<>();
        temp = new UnsortedSet<>();
        
        //test 39 & 40
        testSet(testNum++, s1.containsAll(temp), "Unsorted - containsAll on an empty list");
        testSet(testNum++, s2.containsAll(temp), "Sorted - containsAll on an empty list");
        
        checkReturn(temp.add(1), 1);
        
        //test 41 & 42
        testSet(testNum++, !s1.containsAll(temp), "Unsorted - containsAll on an empty list");
        testSet(testNum++, !s2.containsAll(temp), "Sorted - containsAll on an empty list");
        
        s1 = new UnsortedSet<>();
        s2 = new UnsortedSet<>();
        temp = new UnsortedSet<>();
        s1 = s1.difference(temp);
        s2 = s2.difference(temp);
        
        //test 43 & 44
        testSet(testNum++, s1.containsAll(temp), "Unsorted - difference on empty list");
        testSet(testNum++, s2.containsAll(temp), "Sorted - difference on empty list");
        
        checkReturn(temp.add(5), 5);
        s1 = s1.difference(temp);
        s2 = s2.difference(temp);
        
        //test 45 & 46
        testSet(testNum++, !s1.containsAll(temp), "Unsorted - difference on non-empty list");
        testSet(testNum++, !s2.containsAll(temp), "Sorted - difference on non-empty list");
        
        checkReturn(s1.add(5), 5);
        checkReturn(s2.add(5), 5);
        temp = new UnsortedSet<>();
        s1 = s1.difference(temp);
        s2 = s2.difference(temp);
        
        //test 47 & 48
        testSet(testNum++, s1.containsAll(temp), "Unsorted - difference on empty list");
        testSet(testNum++, s2.containsAll(temp), "Sorted - difference on empty list");
        
        s1 = new UnsortedSet<>();
        s2 = new UnsortedSet<>();
        for (int i = 0; i <= 10; i++) {
        	checkReturn(s1.add(i), 10);
        	checkReturn(s2.add(i), 10);
        }
        for (int i = 5; i <= 15; i++) {
        	checkReturn(temp.add(i), i);
        }
        ISet<Integer> expect = new UnsortedSet<>();
        for (int i = 0; i < 5; i++) {
        	checkReturn(expect.add(i), i);
        }
        s1 = s1.difference(temp);
        s2 = s2.difference(temp);
        
        //test 49 & 50
        testSet(testNum++, s1.containsAll(expect), "Unsorted - difference on normal list");
        testSet(testNum++, s2.containsAll(expect), "Sorted - difference on normal list");
        
        //test 51 & 52
        testSet(testNum++, s1.equals(expect), "Unsorted - equals on normal list");
        testSet(testNum++, s2.equals(expect), "Sorted - equals on normal list");
        
        expect = new UnsortedSet<>();
        for (int i = 4; i >= 0; i--) {
        	checkReturn(expect.add(i), i);
        }
        
        //test 53 & 54
        testSet(testNum++, s1.equals(expect), "Unsorted - equals on unsorted normal list");
        testSet(testNum++, s2.equals(expect), "Sorted - equals on unsorted normal list");
        
        //test 55 & 56
        testSet(testNum++, !s1.equals(null), "Unsorted - equals on null");
        testSet(testNum++, !s2.equals(null), "Sorted - equals on null");
        
        //test 57 & 58
        testSet(testNum++, !s1.equals(new Integer(1)), "Unsorted - equals on unrelated object");
        testSet(testNum++, !s2.equals(new Integer(1)), "Sorted - equals on unrelated object");
        
        s1 = new UnsortedSet<>();
        s2 = new UnsortedSet<>();
        temp = new UnsortedSet<>();
        for (int i = 0; i <= 10; i++) {
        	checkReturn(s1.add(i), i);
        	checkReturn(s2.add(i), i);
        }
        for (int i = 5; i <= 15; i++) {
        	checkReturn(temp.add(i), i);
        }
        expect = new UnsortedSet<>();
        for (int i = 5; i <= 10; i++) {
        	checkReturn(expect.add(i), i);
        }
        s1 = s1.intersection(temp);
        s2 = s2.intersection(temp);
        
        //test 59 & 60
        testSet(testNum++, s1.containsAll(expect), "Unsorted - intersection on normal list");
        testSet(testNum++, s2.containsAll(expect), "Sorted - intersection on normal list");
        
        temp = new UnsortedSet<>();
        expect = new UnsortedSet<>();
        s1 = s1.intersection(temp);
        s2 = s2.intersection(temp);
        
        //test 61 & 62
        testSet(testNum++, s1.equals(expect), "Unsorted - intersection on empty list");
        testSet(testNum++, s2.equals(expect), "Sorted - intersection on empty list");
        
        checkReturn(temp.add(1), 1);
        checkReturn(temp.add(2), 2);
        s1 = s1.intersection(temp);
        s2 = s2.intersection(temp);
        
        //test 63 & 64
        testSet(testNum++, s1.equals(expect), "Unsorted - intersection on empty list");
        testSet(testNum++, s2.equals(expect), "Sorted - intersection on empty list");
        
        Iterator<Integer> is1 = s1.iterator();
        Iterator<Integer> is2 = s2.iterator();
        
        //test 65 & 66
        testSet(testNum++, !is1.hasNext(), "Unsorted - Iterator hasNext on empty list");
        testSet(testNum++, !is2.hasNext(), "Sorted - Iterator hasNext on empty list");
        
        checkReturn(s1.add(1), 1);
        checkReturn(s2.add(1), 1);
        is1 = s1.iterator();
        is2 = s1.iterator();
        
        //test 67 & 68
        testSet(testNum++, is1.hasNext(), "Unsorted - Iterator hasNext on normal list");
        testSet(testNum++, is2.hasNext(), "Sorted - Iterator hasNext on normal list");
        
        checkReturn(s1.add(2), 2);
        checkReturn(s2.add(2), 2);
        is1 = s1.iterator();
        is2 = s1.iterator();
        is1.next();
        is2.next();
        
        //test 69 & 70
        testSet(testNum++, is1.hasNext(), "Unsorted - Iterator hasNext on normal list");
        testSet(testNum++, is2.hasNext(), "Sorted - Iterator hasNext on normal list");
        
        is1.next();
        is2.next();
        
        //test 71 & 72
        testSet(testNum++, !is1.hasNext(), "Unsorted - Iterator hasNext on normal list");
        testSet(testNum++, !is2.hasNext(), "Sorted - Iterator hasNext on normal list");
        
        s1 = new UnsortedSet<>();
        s2 = new SortedSet<>();
        checkReturn(s1.add(1), 1);
        checkReturn(s2.add(1), 1);
        is1 = s1.iterator();
        is2 = s1.iterator();
        
        //test 73 & 74
        testSet(testNum++, is1.next().equals(1), "Unsorted - Iterator next on normal list");
        testSet(testNum++, is2.next().equals(1), "Sorted - Iterator next on normal list");
        
        checkReturn(s1.add(2), 2);
        checkReturn(s2.add(2), 2);
        is1 = s1.iterator();
        is2 = s2.iterator();
        
        //test 75 & 76
        testSet(testNum++, is1.next().equals(1) && is1.next().equals(2), "Unsorted - Iterator next on normal list");
        testSet(testNum++, is2.next().equals(1) && is2.next().equals(2), "Sorted - Iterator next on normal list");
        
        s1 = new UnsortedSet<>();
        s2 = new SortedSet<>();
        checkReturn(s1.add(2), 2);
        checkReturn(s2.add(2), 2);
        checkReturn(s1.add(1), 1);
        checkReturn(s2.add(1), 1);
        is1 = s1.iterator();
        is2 = s2.iterator();
        
        //test 77 & 78
        testSet(testNum++, is1.next().equals(2) && is1.next().equals(1), "Unsorted - Iterator next on un-ordered list");
        testSet(testNum++, is2.next().equals(1) && is2.next().equals(2), "Sorted - Iterator next on un-ordered list");
        
        is1.remove();
        is2.remove();
        
        //test 79 & 80
        testSet(testNum++, true, "Unsorted - Iterator remove on normal list");
        testSet(testNum++, true, "Sorted - Iterator remove on normal list");
        
        s1 = new UnsortedSet<>();
        s2 = new SortedSet<>();
        temp = new UnsortedSet<>();
        checkReturn(!s1.remove(1), 1);
        checkReturn(!s2.remove(1), 1);
        checkReturn(!s1.remove(2), 2);
        checkReturn(!s2.remove(2), 2);
        
        //test 81 & 82
        testSet(testNum++, s1.equals(temp), "Unsorted - remove on empty list");
        testSet(testNum++, s2.equals(temp), "Sorted - remove on empty list");
        
        s1.add(2);
        s1.add(1);
        s2.add(2);
        s2.add(1);
        checkReturn(s1.remove(1), 1);
        checkReturn(s2.remove(1), 1);
        checkReturn(!s1.remove(0), 0);
        checkReturn(!s2.remove(0), 0);
        temp.add(2);
        
        //test 83 & 84
        testSet(testNum++, s1.equals(temp), "Unsorted - remove on normal list");
        testSet(testNum++, s2.equals(temp), "Sorted - remove on normal list");
        
        s1 = new UnsortedSet<>();
        s2 = new SortedSet<>();
        checkReturn(temp.remove(2), 2);
        temp.add(0);
        for (int i = 1000; i >= 0; i--) {
        	checkReturn(s1.add(i), i);
        	checkReturn(s2.add(i), i);
        }
        for (int j = 0; j < 3; j++) {
        	for (int i = 1; i <= 1000; i++) {
        		if (j == 0) {
        			checkReturn(s1.remove(i), i);
        			checkReturn(s2.remove(i), i);
        		}
    			checkReturn(!s1.remove(i), i);
    			checkReturn(!s2.remove(i), i);
        	}
        }
        
        //test 85 & 86
        testSet(testNum++, s1.equals(temp), "Unsorted - remove on large unsorted list");
        testSet(testNum++, s2.equals(temp), "Sorted - remove on large unsorted list");
        
        //test 87 & 88
        testSet(testNum++, s1.size() == 1, "Unsorted - size on 1-element list");
        testSet(testNum++, s2.size() == 1, "Sorted - size on large unsorted list");
        
        checkReturn(s1.remove(0), 0);
        checkReturn(s2.remove(0), 0);
        
        //test 89 & 90
        testSet(testNum++, s1.size() == 0, "Unsorted - size on empty list");
        testSet(testNum++, s2.size() == 0, "Sorted - size on empty list");
        
        checkReturn(!s1.remove(0), 0);
        checkReturn(!s2.remove(0), 0);
        
        //test 91 & 92
        testSet(testNum++, s1.size() == 0, "Unsorted - size on empty list");
        testSet(testNum++, s2.size() == 0, "Sorted - size on empty list");
        
        checkReturn(s1.add(1), 1);
        checkReturn(s2.add(1), 1);
        
        //test 93 & 94
        testSet(testNum++, s1.size() == 1, "Unsorted - size on 1-element list");
        testSet(testNum++, s2.size() == 1, "Sorted - size on 1-element list");
        
        checkReturn(!s1.add(1), 1);
        checkReturn(!s2.add(1), 1);
        
        //test 95 & 96
        testSet(testNum++, s1.size() == 1, "Unsorted - size on 1-element list");
        testSet(testNum++, s2.size() == 1, "Sorted - size on 1-element list");
        
        temp = new UnsortedSet<>();
        s1 = s1.union(temp);
        s2 = s1.union(temp);
        expect = new UnsortedSet<>();
        checkReturn(expect.add(1), 1);
        
        //test 97 & 98
        testSet(testNum++, s1.equals(expect), "Unsorted - union with empty list");
        testSet(testNum++, s2.equals(expect), "Sorted - union with empty list");
        
        checkReturn(temp.add(2), 2);
        s1 = temp.union(s1);
        s2 = temp.union(s2);
        checkReturn(expect.add(2), 2);
        
        //test 99 & 100
        testSet(testNum++, s1.equals(expect), "Unsorted - union with unsorted simple list");
        testSet(testNum++, s2.equals(expect), "Sorted - union with unsorted simple list");
        
        temp = new UnsortedSet<>();
        s1 = new UnsortedSet<>();
        s2 = new UnsortedSet<>();
        expect = new UnsortedSet<>();
        
        for (int i = 5; i >= 0; i--) {
        	temp.add(i);
        }
        for (int i = 90; i >= 84; i--) {
        	checkReturn(s1.add(i), i);
        	checkReturn(s2.add(i), i);
        }
        s1 = s1.union(temp);
        s2 = s2.union(temp);
        
        for (int i = 0; i <= 5; i++) {
        	expect.add(i);
        }
        for (int i = 84; i <= 90; i++) {
        	expect.add(i);
        }
        
        //test 101 & 102
        testSet(testNum++, s1.equals(expect), "Unsorted - union with complex unsorted list");
        testSet(testNum++, s2.equals(expect), "Sorted - union with complex unsorted list");
        
        s1 = new UnsortedSet<>();
        s2 = new SortedSet<>();
        expect = new UnsortedSet<>();
        s1 = s1.union(temp);
        s2 = s2.union(temp);
        
        for (int i = 0; i <= 5; i++) {
        	checkReturn(expect.add(i), i);
        }
        
        //test 103 & 104
        testSet(testNum++, s1.equals(expect), "Unsorted - union as empty list");
        testSet(testNum++, s2.equals(expect), "Sorted - union as empty list");
        
        s2 = new SortedSet<>();
        checkReturn(s2.add(2), 2);
        
        //test 105
        testSet(testNum++, ((SortedSet<Integer>) s2).min() == 2, "Sorted - min on 1-element list");
        
        checkReturn(s2.add(1), 1);
        
        //test 106
        testSet(testNum++, ((SortedSet<Integer>) s2).min() == 1, "Sorted - min on simple list");
        
        s2 = new SortedSet<>();
        checkReturn(s2.add(-5), -5);
        checkReturn(s2.add(9), 9);
        checkReturn(s2.add(4), 4);
        temp = new UnsortedSet<>();
        checkReturn(temp.add(6), 6);
        checkReturn(temp.add(12), 12);
        checkReturn(temp.add(-7), -7);
        checkReturn(temp.add(20), 20);
        checkReturn(temp.add(-2), -2);
        s2 = s2.union(temp);
        
        //test 107
        testSet(testNum++, ((SortedSet<Integer>) s2).min() == -7, "Sorted - min on a complex list");
        
        s2 = new SortedSet<>();
        checkReturn(s2.add(2), 2);
        checkReturn(s2.add(1), 1);
        
        //test 108
        testSet(testNum++, ((SortedSet<Integer>) s2).max() == 2, "Sorted - max on simple list");
        
        checkReturn(s2.remove(2), 2);
        
        //test 109
        testSet(testNum++, ((SortedSet<Integer>) s2).max() == 1, "Sorted - max on 1-element list");
        
        s2 = new SortedSet<>();
        checkReturn(s2.add(-5), -5);
        checkReturn(s2.add(9), 9);
        checkReturn(s2.add(4), 4);
        temp = new UnsortedSet<>();
        checkReturn(temp.add(6), 6);
        checkReturn(temp.add(12), 12);
        checkReturn(temp.add(-7), -7);
        checkReturn(temp.add(20), 20);
        checkReturn(temp.add(-2), -2);
        s2 = s2.union(temp);
        
        //test 110
        testSet(testNum++, ((SortedSet<Integer>) s2).max() == 20, "Sorted - max on a complex list");
    }
    
    private static void testSet(int testNum, boolean result, String desc) {
    	String res = result ? "Passed" : "Failed";
    	System.out.println("Test " + testNum + ": " + res + " - " + desc);
    }
    
    private static void checkReturn(boolean result, Integer elem) {
    	if (!result) {
    		System.out.println("SMALL ERROR: The operation with " + elem + " returned the wrong result");
    	}
    }
}