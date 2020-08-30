import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//  CodeCamp.java - CS314 Assignment 1 - Tester class

/*  Student information for assignment:
 *
 *  Name: Preeth Kanamangala
 *  email address: preeth.kanamangala@gmail.com
 *  UTEID: PK9297
 *  Section 5 digit ID: 50240
 *  Grader name: Amir
 *  Number of slip days used on this assignment:
 */

/* CS314 Students: place results of shared birthdays experiments in this comment.
 
 - The average number of pairs of people with shared birthdays from 1,000,000 experiments with 365 days per year and 182 people per experiment is 45.129403.
 - According to the table below, it takes 23 people so there is a 50% chance that at least 2 of the people have a shared birthday in a 365 day year.
 
Num people:   2, number of experiments with one or more shared birthday:   133, percentage:   0.27 
Num people:   3, number of experiments with one or more shared birthday:   417, percentage:   0.83 
Num people:   4, number of experiments with one or more shared birthday:   741, percentage:   1.48 
Num people:   5, number of experiments with one or more shared birthday:  1277, percentage:   2.55 
Num people:   6, number of experiments with one or more shared birthday:  2004, percentage:   4.01 
Num people:   7, number of experiments with one or more shared birthday:  2841, percentage:   5.68 
Num people:   8, number of experiments with one or more shared birthday:  3768, percentage:   7.54 
Num people:   9, number of experiments with one or more shared birthday:  4739, percentage:   9.48 
Num people:  10, number of experiments with one or more shared birthday:  5889, percentage:  11.78 
Num people:  11, number of experiments with one or more shared birthday:  7029, percentage:  14.06 
Num people:  12, number of experiments with one or more shared birthday:  8295, percentage:  16.59 
Num people:  13, number of experiments with one or more shared birthday:  9821, percentage:  19.64 
Num people:  14, number of experiments with one or more shared birthday: 11271, percentage:  22.54 
Num people:  15, number of experiments with one or more shared birthday: 12477, percentage:  24.95 
Num people:  16, number of experiments with one or more shared birthday: 14210, percentage:  28.42 
Num people:  17, number of experiments with one or more shared birthday: 15705, percentage:  31.41 
Num people:  18, number of experiments with one or more shared birthday: 17321, percentage:  34.64 
Num people:  19, number of experiments with one or more shared birthday: 18721, percentage:  37.44 
Num people:  20, number of experiments with one or more shared birthday: 20806, percentage:  41.61 
Num people:  21, number of experiments with one or more shared birthday: 22268, percentage:  44.54 
Num people:  22, number of experiments with one or more shared birthday: 23709, percentage:  47.42 
Num people:  23, number of experiments with one or more shared birthday: 25233, percentage:  50.47 
Num people:  24, number of experiments with one or more shared birthday: 26914, percentage:  53.83 
Num people:  25, number of experiments with one or more shared birthday: 28247, percentage:  56.49 
Num people:  26, number of experiments with one or more shared birthday: 30137, percentage:  60.27 
Num people:  27, number of experiments with one or more shared birthday: 31187, percentage:  62.37 
Num people:  28, number of experiments with one or more shared birthday: 32488, percentage:  64.98 
Num people:  29, number of experiments with one or more shared birthday: 33956, percentage:  67.91 
Num people:  30, number of experiments with one or more shared birthday: 35428, percentage:  70.86 
Num people:  31, number of experiments with one or more shared birthday: 36620, percentage:  73.24 
Num people:  32, number of experiments with one or more shared birthday: 37488, percentage:  74.98 
Num people:  33, number of experiments with one or more shared birthday: 38718, percentage:  77.44 
Num people:  34, number of experiments with one or more shared birthday: 39850, percentage:  79.70 
Num people:  35, number of experiments with one or more shared birthday: 40749, percentage:  81.50 
Num people:  36, number of experiments with one or more shared birthday: 41566, percentage:  83.13 
Num people:  37, number of experiments with one or more shared birthday: 42427, percentage:  84.85 
Num people:  38, number of experiments with one or more shared birthday: 43198, percentage:  86.40 
Num people:  39, number of experiments with one or more shared birthday: 43896, percentage:  87.79 
Num people:  40, number of experiments with one or more shared birthday: 44526, percentage:  89.05 
Num people:  41, number of experiments with one or more shared birthday: 45059, percentage:  90.12 
Num people:  42, number of experiments with one or more shared birthday: 45768, percentage:  91.54 
Num people:  43, number of experiments with one or more shared birthday: 46185, percentage:  92.37 
Num people:  44, number of experiments with one or more shared birthday: 46718, percentage:  93.44 
Num people:  45, number of experiments with one or more shared birthday: 47052, percentage:  94.10 
Num people:  46, number of experiments with one or more shared birthday: 47396, percentage:  94.79 
Num people:  47, number of experiments with one or more shared birthday: 47737, percentage:  95.47 
Num people:  48, number of experiments with one or more shared birthday: 48030, percentage:  96.06 
Num people:  49, number of experiments with one or more shared birthday: 48274, percentage:  96.55 
Num people:  50, number of experiments with one or more shared birthday: 48489, percentage:  96.98 
Num people:  51, number of experiments with one or more shared birthday: 48795, percentage:  97.59 
Num people:  52, number of experiments with one or more shared birthday: 48926, percentage:  97.85 
Num people:  53, number of experiments with one or more shared birthday: 49037, percentage:  98.07 
Num people:  54, number of experiments with one or more shared birthday: 49221, percentage:  98.44 
Num people:  55, number of experiments with one or more shared birthday: 49318, percentage:  98.64 
Num people:  56, number of experiments with one or more shared birthday: 49392, percentage:  98.78 
Num people:  57, number of experiments with one or more shared birthday: 49483, percentage:  98.97 
Num people:  58, number of experiments with one or more shared birthday: 49558, percentage:  99.12 
Num people:  59, number of experiments with one or more shared birthday: 49663, percentage:  99.33 
Num people:  60, number of experiments with one or more shared birthday: 49714, percentage:  99.43 
Num people:  61, number of experiments with one or more shared birthday: 49738, percentage:  99.48 
Num people:  62, number of experiments with one or more shared birthday: 49787, percentage:  99.57 
Num people:  63, number of experiments with one or more shared birthday: 49842, percentage:  99.68 
Num people:  64, number of experiments with one or more shared birthday: 49897, percentage:  99.79 
Num people:  65, number of experiments with one or more shared birthday: 49878, percentage:  99.76 
Num people:  66, number of experiments with one or more shared birthday: 49906, percentage:  99.81 
Num people:  67, number of experiments with one or more shared birthday: 49914, percentage:  99.83 
Num people:  68, number of experiments with one or more shared birthday: 49938, percentage:  99.88 
Num people:  69, number of experiments with one or more shared birthday: 49954, percentage:  99.91 
Num people:  70, number of experiments with one or more shared birthday: 49963, percentage:  99.93 
Num people:  71, number of experiments with one or more shared birthday: 49960, percentage:  99.92 
Num people:  72, number of experiments with one or more shared birthday: 49980, percentage:  99.96 
Num people:  73, number of experiments with one or more shared birthday: 49973, percentage:  99.95 
Num people:  74, number of experiments with one or more shared birthday: 49985, percentage:  99.97 
Num people:  75, number of experiments with one or more shared birthday: 49991, percentage:  99.98 
Num people:  76, number of experiments with one or more shared birthday: 49986, percentage:  99.97 
Num people:  77, number of experiments with one or more shared birthday: 49991, percentage:  99.98 
Num people:  78, number of experiments with one or more shared birthday: 49997, percentage:  99.99 
Num people:  79, number of experiments with one or more shared birthday: 49995, percentage:  99.99 
Num people:  80, number of experiments with one or more shared birthday: 49999, percentage: 100.00 
Num people:  81, number of experiments with one or more shared birthday: 49994, percentage:  99.99 
Num people:  82, number of experiments with one or more shared birthday: 49996, percentage:  99.99 
Num people:  83, number of experiments with one or more shared birthday: 49996, percentage:  99.99 
Num people:  84, number of experiments with one or more shared birthday: 49999, percentage: 100.00 
Num people:  85, number of experiments with one or more shared birthday: 49998, percentage: 100.00 
Num people:  86, number of experiments with one or more shared birthday: 50000, percentage: 100.00 
Num people:  87, number of experiments with one or more shared birthday: 50000, percentage: 100.00 
Num people:  88, number of experiments with one or more shared birthday: 50000, percentage: 100.00 
Num people:  89, number of experiments with one or more shared birthday: 50000, percentage: 100.00 
Num people:  90, number of experiments with one or more shared birthday: 50000, percentage: 100.00 
Num people:  91, number of experiments with one or more shared birthday: 50000, percentage: 100.00 
Num people:  92, number of experiments with one or more shared birthday: 49999, percentage: 100.00 
Num people:  93, number of experiments with one or more shared birthday: 50000, percentage: 100.00 
Num people:  94, number of experiments with one or more shared birthday: 50000, percentage: 100.00 
Num people:  95, number of experiments with one or more shared birthday: 50000, percentage: 100.00 
Num people:  96, number of experiments with one or more shared birthday: 50000, percentage: 100.00 
Num people:  97, number of experiments with one or more shared birthday: 50000, percentage: 100.00 
Num people:  98, number of experiments with one or more shared birthday: 50000, percentage: 100.00 
Num people:  99, number of experiments with one or more shared birthday: 50000, percentage: 100.00 
Num people: 100, number of experiments with one or more shared birthday: 50000, percentage: 100.00 

*/

public class CodeCampTester {

    public static void main(String[] args){
        final String newline = System.getProperty("line.separator");
        
        //test 1, hamming distance
        int[] h1 = {0, 0, 0};
        int[] h2 = {-1, -1, -1};
        int expected = 3;
        int actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println("Test 1 hamming distance: expected value: " + 
                expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 1, hamming distance");
        else
            System.out.println(" ***** FAILED ***** test 1, hamming distance");
        
        // test 2, hamming distance
        h1 = new int[] {2, 4, 6, 8, 10, 12, 14};
        h2 = new int[] {1, 2, 4, 8, 16, 32, 64};
        expected = 6;
        actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println(newline + "Test 2 hamming distance: expected value: " 
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 2, hamming distance");
        else
            System.out.println(" ***** FAILED ***** test 2, hamming distance");
        
        //test 3, isPermutation
        int[] a = {3, 5, 9, 1, 13, 7};
        int[] b = {11, 3, 7, 11, 9, 5};
        boolean expectedBool = false;
        boolean actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 3 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 3, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 3, isPermutation");
        
        //test 4, is Permutation
        a = new int[1];
        b = new int[1];
        expectedBool = true;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 4 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 4, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 4, isPermutation");
        
        // test 5, mostVowels
        String[] sList = {"iioouaiep", "potato grass tree man standing"};
        int expectedResult = 1;
        int actualResult = CodeCamp.mostVowels(sList);
        System.out.println(newline + "Test 5 mostVowels: expected result: " 
                + expectedResult + ", actual result: " + actualResult);
        if( actualResult == expectedResult )
            System.out.println("passed test 5, mostVowels");
        else
            System.out.println("***** FAILED ***** test 5, mostVowels");
  
        // test 6, mostVowels
        sList = new String[] {"yEaH i''am a CoMp sCi Maj0R", "   ", null, null, "? ! ( +)=== /*-/*-//", "superbadmovie"};
        expectedResult = 0;
        actualResult = CodeCamp.mostVowels(sList);
        System.out.println(newline + "Test 6 mostVowels: expected result: " 
                + expectedResult + ", actual result: " + actualResult);
        if( actualResult == expectedResult )
            System.out.println("passed test 6, mostVowels");
        else
            System.out.println("***** FAILED ***** test 6, mostVowels");
        
        //test 7, sharedBirthdays
        int pairs = CodeCamp.sharedBirthdays(25, 20);
        System.out.println(newline + "Test 7 shared birthdays: expected: " +
                "a value of 1 or more, actual: " + pairs);
        if( pairs > 0 )
            System.out.println("passed test 7, shared birthdays");
        else
            System.out.println("***** FAILED ***** test 7, shared birthdays");        
        
        //test 8, sharedBirthdays
        pairs = CodeCamp.sharedBirthdays(10, 1);
        System.out.println(newline + "Test 8 shared birthdays: expected: 45" +
                ", actual: " + pairs);
        if( pairs == 45 )
            System.out.println("passed test 8, shared birthdays");
        else
            System.out.println("***** FAILED ***** test 8, shared birthdays. " +
                    "Expected pairs to be 45. Value returned: " + pairs);   
        
        //test 9, queensAreASafe
        char[][] board = { {'q', '.', '.'},
                          {'.', '.', '.'},
                          {'.', '.', 'q'}};
        
        expectedBool = false;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Test 9 queensAreSafe: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 9, queensAreSafe");
        else
            System.out.println(" ***** FAILED ***** test 9, queensAreSafe");

        //test 10, queensAreASafe
        board = new char[][]{{'.', 'q', '.', '.'},
                             {'.', '.', '.', 'q'},
                             {'.', '.', '.', '.'},
                             {'q', '.', '.', '.'}};
        expectedBool = true;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Test 10 queensAreSafe: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 10, queensAreSafe");
        else
            System.out.println(" ***** FAILED ***** test 10, queensAreSafe");
      
        // test 11, getValueOfMostValuablePlot
        int[][] city = {{0, 0, 0, 0, -1},
                          {-1, 2, 0, 0, 0},
                          {0, 1, 0, 0, 0},
                          {-1, 0, 0, -2, 1},
                          {0, 0, 1, -2, 0},
                          {0, 0, 1, 0, 4}};
        
        expected = 6;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println(newline + "Test 11 getValueOfMostValuablePlot: expected value: " 
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 11, getValueOfMostValuablePlot");
        else
            System.out.println(" ***** FAILED ***** test 11, getValueOfMostValuablePlot");

        // test 12, getValueOfMostValuablePlot
        city = new int[][]{{-2, -2, -2, 4, 4, -4, 8, -2, 4}};
        expected = 14;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println(newline + "Test 12 getValueOfMostValuablePlot: expected value: " 
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 12, getValueOfMostValuablePlot");
        else
            System.out.println(" ***** FAILED ***** test 12, getValueOfMostValuablePlot");
       
        System.out.println("The average number of pairs of people with shared birthdays from 1,000,000 experiments with 365 days per year and 182 people per experiment is " + CodeCamp.sharedBirthdaysAverage());
        
        CodeCamp.sharedBirthdaysChance();
        
    }
    
    // pre: list != null
    private static int[] intListToArray(List<Integer> list) {
        if(list == null)
            throw new IllegalArgumentException("list parameter may not be null.");
        int[] result = new int[list.size()];
        int arrayIndex = 0;
        for(int x : list) {
            result[arrayIndex++] = x;
        }
        return result;
    }
}