//  CodeCamp.java - CS314 Assignment 1

/*  Student information for assignment:
 * 
 * replace <NAME> with your name.
 *
 *  On my honor, Preeth Kanamangala, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Preeth Kanamangala
 *  email address: preeth.kanamangala@gmail.com
 *  UTEID: PK9297
 *  Section 5 digit ID: 50240
 *  Grader name:
 *  Number of slip days used on this assignment:
 */

import java.util.Random;
import java.util.Scanner;

public class CodeCamp {
  
    /**
     * Determine the Hamming distance between two arrays of ints. 
     * Neither the parameter <tt>aList</tt> or
     * <tt>bList</tt> are altered as a result of this method.<br>
     * @param aList != null, aList.length == bList.length
     * @param bList != null, bList.length == aList.length
     * @return the Hamming Distance between the two arrays of ints.
     */    
    public static int hammingDistance(int[] aList, int[] bList){
        // check preconditions
        if (aList == null || bList == null || aList.length != bList.length) 
            throw new IllegalArgumentException("Violation of precondition: " +
            		"hammingDistance. neither parameter may equal null, arrays" +
            		" must be equal length.");
        int distCount = 0;
        
        for (int i=0; i < aList.length; i++) {
        	if (aList[i] != bList[i]) {
        		distCount++;
        	}
        }
        
        return distCount; //must change
    }
    
    
    /**
     * Determine if one array of ints is a permutation of another.
     * Neither the parameter <tt>listA</tt> or 
     * the parameter <tt>listB</tt> are altered as a result of this method.<br>
     * @param listA != null
     * @param listB != null
     * @return <tt>true</tt> if listB is a permutation of listA, 
     * <tt>false</tt> otherwise
     * 
    */
    public static boolean isPermutation(int[] listA, int[] listB) {
        // check preconditions
        if (listA == null || listB == null) 
            throw new IllegalArgumentException("Violation of precondition: " +
            		"isPermutation. neither parameter may equal null.");
        
        int[] listC = listA;
        int[] listD = listB;
        
        for (int i=0; i < listC.length; i++) {
        	for (int j=0; i < listD.length; j++) {
        		if (listC[i] == listD[j]) {
        			listC[i] = (Integer) null;
        			listD[j] = (Integer) null;
        		}
        	}
        }
        
        for (int i=0; i < listC.length; i++) {
        	if (listC[i] != listD[i]) {
        		return false;
        	}
        }
        
        return true;
    }
    
    
    /**
     * Determine the index of the String that 
     * has the largest number of vowels. 
     * Vowels are defined as <tt>'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 
     * 'U', and 'u'</tt>.
     * The parameter <tt>list</tt> is not altered as a result of this method.
     * <p>pre: <tt>list != null</tt>, <tt>list.length > 0</tt>, there is an least 1 non null element in list
     * <p>post: return the index of the non-null element in list that has the 
     * largest number of characters that are vowels.
     * If there is a tie return the index closest to zero. 
     * The empty String, "", has zero vowels.
     * It is possible for the maximum number of vowels to be 0.<br>
     * @param list the array to check
     * @return the index of the non-null element in list that has 
     * the largest number of vowels.
     */
    public static int mostVowels(String[] list) {
        // check preconditions
        if (list == null || list.length == 0 || !atLeastOneNonNull(list))  
            throw new IllegalArgumentException("Violation of precondition: " +
            		"mostVowels. parameter may not equal null and must contain " +
            		"at least one none null value.");
        int[] vowelArray = new int[list.length];
        
        for (int i=0; i < list.length; i++) {
        	
        	String word = list[i];
        	
        	for (int j=0; j < word.length(); j++) {
        		if (word.toLowerCase().charAt(j) == 'a' || word.toLowerCase().charAt(j) == 'e' || word.toLowerCase().charAt(j) == 'i' || word.toLowerCase().charAt(j) == 'o' || word.toLowerCase().charAt(j) == 'u') {
        			vowelArray[i]++;
        		}
        	}
        }
        
        int max = vowelArray[0];
        int index = 0;
        
        for(int i=1; i < vowelArray.length; i++) {
        	if (vowelArray[i] > max) {
        		max = vowelArray[i];
        		index = i;
        	}
        }

        return index;
    }
    

    
    /**
     * Perform an experiment simulating the birthday problem.
     * Pick random birthdays for the given number of people. 
     * Return the number of pairs of people that share the
     * same birthday.<br>
     * @param numPeople The number of people in the experiment.
     * This value must be > 0
     * @param numDaysInYear The number of days in the year for this experiement.
     * This value must be > 0
     * @return The number of pairs of people that share a birthday 
     * after running the simulation.
     */
    public static int sharedBirthdays(int numPeople, int numDaysInYear) {
        // check preconditions
        if (numPeople <= 0 || numDaysInYear <= 0)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"sharedBirthdays. both parameters must be greater than 0. " +
                    "numPeople: " + numPeople + 
                    ", numDaysInYear: " + numDaysInYear);
        
        Random r = new Random();
        
        int[] birthDays = new int[numPeople];
        
        for (int i=0; i < birthDays.length; i++) {
        	birthDays[i] = r.nextInt(numDaysInYear) + 1;
        }
        
        int counter = 0;
        int pairs = 0;
        
        for (int i=1; i < numDaysInYear+1; i++) {
        	for(int j=0; j < birthDays.length; i++) {
        		if(birthDays[j] == i) {
        			counter++;
        		}
        	}
        	
        	if(counter > 1) {
        		pairs = pairs + counter;
        	}
        	
        }

        return pairs; //must change
    }
    
    
    /**
     * Determine if the chess board represented by board is a safe set up.
     * <p>pre: board != null, board.length > 0, board is a square matrix.
     * (In other words all rows in board have board.length columns.),
     * all elements of board == 'q' or '.'. 'q's represent queens, '.'s
     * represent open spaces.<br>
     * <p>post: return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
     * the parameter <tt>board</tt> is not altered as a result of 
     * this method.<br>
     * @param board the chessboard
     * @return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
    */
    public static boolean queensAreSafe(char[][] board) {
        char[] validChars = {'q', '.'};
        // check preconditions
        if (board == null || board.length == 0 || !isSquare(board) 
                || !onlyContains(board, validChars))
            throw new IllegalArgumentException("Violation of precondition: " +
            		"queensAreSafe. The board may not be null, must be square, " +
            		"and may only contain 'q's and '.'s");        
        
        boolean status = true;
        
        for (int i=0; i < board.length; i++) {
        	for (int j=0; j < board.length; j++) {
        		if(board[i][j] == 'q') {
        			for (int y=0; y < board.length; y++) {
        				if (board[y][j] == 'q') {
        					status = false;
        				}
        			}
        			for (int x=0; x < board.length; x++) {
        				if (board[i][x] == 'q') {
        					status = false;
        				}
        			}
        			int sum = i+j;
        			int a = 0;
        			int b = sum;
        			while(a != sum+1) {
        				if (a < board.length && b < board.length) {
        					if (board[a][b] == 'q') {
        						status = false;
        					}
        				}
        			a++;
        			b--;
        			}
        			int z=i;
        			int w=j;
        			if (z-1 > -1 && w-1 > -1) {
        				z = z-1;
        				w = w-1;
        				while (z!=-1 && w!=-1) {
        					if (board[z][w] == 'q') {
        						status = false;
        					}
        				z--;
        				w--;
        				}
        			}
        			z=i;
        			w=j;
        			if (z+1 < board.length && w+1 < board.length) {
        				z = z+1;
        				w = w+1;
        				while (z!=board.length && w!=board.length) {
        					if (board[z][w] == 'q') {
        						status = false;
        					}
        				z++;
        				w++;
        				}
        			}
        		}
        	}
        }

        return status; //must change
    }
    
    
    /**
     * Given a 2D array of ints return the value of the
     * most valuable contiguous sub rectangle in the 2D array.
     * The sub rectangle must be at least 1 by 1. 
     * <p>pre: <tt>mat != null, mat.length > 0, mat[0].length > 0,
     * mat</tt> is a rectangular matrix.
     * <p>post: return the value of the most valuable contigous sub rectangle
     * in <tt>city</tt>.<br>
     * @param city The 2D array of ints representing the value of
     * each block in a portion of a city.
     * @return return the value of the most valuable contigous sub rectangle
     * in <tt>city</tt>.
     */
    public static int getValueOfMostValuablePlot(int[][] city) {
        // check preconditions
        if (city == null || city.length == 0 || city[0].length == 0 
                || !isRectangular(city) )
            throw new IllegalArgumentException("Violation of precondition: " +
            		"getValueOfMostValuablePlot. The parameter may not be null," +
            		" must value at least one row and at least" +
                    " one column, and must be rectangular."); 
        
        int max = 0;
        
        for (int i=0; i < city.length; i++) {
        	for (int j=0; j < city.length; j++) {
        		for (int i2=0; i < city.length; i2++) {
        			for (int j2=0; j2 < city.length; j2++) {
        				int sum = 0;
        				for (int startRow=i; startRow < i2; startRow++) {
        					for (int startCol=j; startCol < j2; startCol++) {
        						sum = sum + city[startRow][startCol];
        					}
        					
        					if (max > sum) {
        						max = sum;
        					}
        				}
        			}
        		}
        	}
        }
        return max; //must change
    }
    
    
    // !!!!! ***** !!!!! ***** !!!!! ****** !!!!! ****** !!!!! ****** !!!!!!
    public static double sharedBirthdaysAverage(String[] args){
    	int count = 0;
    	int sum = 0;
    	
    	while (count != 1000000) {
    		sum = sum + sharedBirthdays(182, 365);
    		count++;
    	}
    	
    	return sum/1000000;
    }
    
    public static void sharedBirthdaysChance(String[] args){
    	int daysPerYear = 365;
    	for (int i=2; i < 101; i++) {
    		int count = 0;
    		int acceptedRuns = 0;
    		while (count != 50000) {
    			int pairValue = sharedBirthdays(i, 365);
    			if(pairValue > 0) {
    				acceptedRuns++;
    			}
    		count++;
    		}
    	System.out.println("Num people: " + i + ", number of experiments with one or more shared birthday: " + acceptedRuns + ", percentage: " + acceptedRuns/50000);
    	}
    }
    
    
    // pre: list != null
    // post: return true if at least one element in list is null
    // otherwise return false.
    private static boolean atLeastOneNonNull(String[] list) {
        // check precondition
        if(list == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"atLeastOneNonNull. parameter may not equal null.");
        
        boolean foundNonNull = false;
        int i = 0;
        while( !foundNonNull && i < list.length ){
            foundNonNull = list[i] != null;
            i++;
        }
        return foundNonNull;
    }
    
    
    /* pre: mat != null
    post: return true if mat is a square matrix, false otherwise
     */
    private static boolean isSquare(char[][] mat) {
        if(mat == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"isSquare. paremeter may not be null.");

        final int numRows = mat.length;
        int row = 0;
        boolean isSquare = true;
        while( isSquare && row < numRows ) {
            isSquare = ( mat[row] != null) && (mat[row].length == numRows);
            row++;
        }
        return isSquare;
    }
    
    
    /* pre: mat != null, valid != null
    post: return true if all elements in mat are one of the characters in valid
     */
    private static boolean onlyContains(char[][] mat, char[] valid) {
        // check preconditions
        if(mat == null || valid == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"onlyContains. paremeters may not be null.");
        
        int row = 0;
        boolean correct = true;
        while( correct && row < mat.length) {
            int col = 0;
            while(correct && col < mat[row].length) {
                correct = contains(valid, mat[row][col]);
                col++;
            }
            row++;
        }
        return correct;
    }
    
    
    /*  pre: list != null
        post: return true if c is in list
     */
    private static boolean contains(char[] list, char tgtChar) {
        // check preconditions
        if(list == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"contains. paremeter may not be null.");

        boolean found = false;
        int index = 0;
        while( !found && index < list.length) {
            found = list[index] == tgtChar;
            index++;
        }
        return found;
    }
   
    
     // pre: mat != null, mat.length > 0
     // post: return true if mat is rectangular
    private static boolean isRectangular(int[][] mat) {
        // check preconditions
        if(mat == null || mat.length == 0)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"isRectangular. paremeter may not be null and must contain" +
            		" at least one row.");

        boolean correct = mat[0] != null;
        int row = 0;
        while(correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == mat[0].length);
            row++;
        }
        return correct;
    }
    
    // make constructor pirvate so no instances of CodeCamp can be created
    private CodeCamp() {
        
    }
}