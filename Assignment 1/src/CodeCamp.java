//  CodeCamp.java - CS314 Assignment 1

/*  Student information for assignment:
 * 
 *  Replace <NAME> with your name.
 *
 *  On my honor, Preeth Kanamangala, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Preeth Kanamangala
 *  email address: preeth.kanamangala@gmail.com
 *  UTEID: PK9297
 *  Section 5 digit ID: 50240
 *  Grader name: Amir
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
      
        int symbolDifferenceCount = 0;
        
        for (int i = 0; i < aList.length; i++) {
        	if (aList[i] != bList[i]) {
        		symbolDifferenceCount++;
        	}
        }
        return symbolDifferenceCount;
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
        
        if (listA.length != listB.length) {
        	return false;
        }
    
        double[] listACopy = arrayCopy(listA);	// copies listA into a new double array
        double[] listBCopy = arrayCopy(listB); // copies listB into a new double array
        
        for (int indexA = 0; indexA < listACopy.length; indexA++) {	// if any 2 items from each array match, place an arbitrary IMPOSSIBLE (0.99 since listA and listB are int[]) value into each array at that index
        	for (int indexB = 0; indexB < listBCopy.length; indexB++) {
        		if (listACopy[indexA] == listBCopy[indexB]) {
        			listACopy[indexA] = 0.99;
        			listBCopy[indexB] = 0.99;
        		}
        	}
        }
        
        for (int i = 0; i < listACopy.length; i++) { // if each index doesn't contain this arbitrary IMPOSSIBLE value, at LEAST one element did not correspond
        	if (listACopy[i] != listBCopy[i]) {
        		return false;
        	}
        }
        
        return true;
    }
    
    private static double[] arrayCopy(int[] listOriginal) {	// copy each element from the original list to a double[] copy of the list
    	double[] listCopy = new double[listOriginal.length];
    	
        for (int i = 0; i < listCopy.length; i++) {
        	listCopy[i] = listOriginal[i];
        }
        
        return listCopy;
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
      
        int[] numberOfVowels = new int[list.length];
        for (int indexOfArray = 0; indexOfArray < list.length; indexOfArray++) {
        	
        	String specificString = list[indexOfArray];
        	
        	if (specificString == null) { // avoids null processing error
        		numberOfVowels[indexOfArray] = -1;
        	}
        	else {
            	for (int indexOfString = 0; indexOfString < specificString.length(); indexOfString++) {	// checks every character of the string for vowels
            		char vowelCharacter = specificString.toLowerCase().charAt(indexOfString);
            		if (vowelCharacter == 'a' || vowelCharacter == 'e' || vowelCharacter == 'i' || vowelCharacter == 'o' || vowelCharacter == 'u') {
            			numberOfVowels[indexOfArray]++;
            		}
            	}
        	}
        }
        return indexOfMostVowels(numberOfVowels);
    }
    
    private static int indexOfMostVowels (int[] numberOfVowels) {	// each index in this array holds the number of vowels for each string, so finds index with most number of vowels
        int maxIndex = 0;
    	int maxNumberOfVowels = numberOfVowels[0];
        
        for (int i = 0; i < numberOfVowels.length; i++) {
        	if (numberOfVowels[i] > maxNumberOfVowels) {
        		maxIndex = i;
        		maxNumberOfVowels = numberOfVowels[i];
        	}
        }
        return maxIndex;
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
        int[] birthDaysList = new int[numPeople];
        
        for (int i = 0; i < birthDaysList.length; i++) {
        	birthDaysList[i] = r.nextInt(numDaysInYear) + 1;
        }
        
        int totalPairs = 0;
        
        for (int dayOfYear = 1; dayOfYear <= numDaysInYear; dayOfYear++) { // goes through all days in a year
            
        	int totalPairsByBirthDay = 0;
            
        	for(int i = 0; i < birthDaysList.length; i++) { // finds how many total people have a particular day as their birthday
        		if(birthDaysList[i] == dayOfYear) {
        			totalPairsByBirthDay++;
        		}
        	}
        	if(totalPairsByBirthDay > 1) {
        		totalPairs = totalPairs + ((totalPairsByBirthDay * (totalPairsByBirthDay - 1)) / 2); // the simplified combination formula for total CHOOSE two
        	}
        }
		return totalPairs;
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
        final char QUEEN = 'q';
        
        for (int r = 0; r < board.length; r++) { 
        	for (int c = 0; c < board.length; c++) {
        		if (board[r][c] == QUEEN) {
        			for (int rowCheckIndex = 0; rowCheckIndex < board.length; rowCheckIndex++) {
        				if (rowCheckIndex != r && board[rowCheckIndex][c] == QUEEN) { // checks if there are any OTHER queens on the same column
        					status = false;
        				}
        			}
        			for (int columnCheckIndex = 0; columnCheckIndex < board.length; columnCheckIndex++) { //checks if there are any OTHER queens on the same row
        				if (columnCheckIndex != c && board[r][columnCheckIndex] == QUEEN) {
        					status = false;
        				}
        			}
        			status = diagonalCheck(board, r, c, QUEEN);
        		}
        	}
        }

        return status;
    }
    
    public static boolean diagonalCheck(char[][] board, int r, int c, char QUEEN) {
		for (int rAnotherSquare = 0; rAnotherSquare < board.length; rAnotherSquare++) { // checks for all OTHER queens
			for (int cAnotherSquare = 0; cAnotherSquare < board.length; cAnotherSquare++) {
				if ((rAnotherSquare != r && cAnotherSquare != c) && board[rAnotherSquare][cAnotherSquare] == QUEEN && (Math.abs(rAnotherSquare - r) == Math.abs(cAnotherSquare - c))) { // if a given queen has a slope of one, then it is on the same diagonal
					return false;
				}
			}
		}
		return true; 
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
        
        int maxValue = city[0][0];
        
        for (int topLeftRowIndex = 0; topLeftRowIndex < city.length; topLeftRowIndex++) {	// runs through every possible rectangle configuration
        	for (int topLeftColumnIndex = 0; topLeftColumnIndex < city[0].length; topLeftColumnIndex++) {
        		for (int bottomRightRowIndex = topLeftRowIndex; bottomRightRowIndex < city.length; bottomRightRowIndex++) {
        			for (int bottomRightColumnIndex = topLeftColumnIndex; bottomRightColumnIndex < city[0].length; bottomRightColumnIndex++) {
        				int plotValue = 0;
        				for (int startRow = topLeftRowIndex; startRow <= bottomRightRowIndex; startRow++) {	// sums all plot values from length limit to width limit
        					for (int startCol = topLeftColumnIndex; startCol <= bottomRightColumnIndex; startCol++) {
        						plotValue += city[startRow][startCol];
        					}
        					
        					if (maxValue < plotValue) {
        						maxValue = plotValue;
        					}
        				}
        			}
        		}
        	}
        }
        return maxValue;
    }
     
    // !!!!! ***** !!!!! ***** !!!!! ****** !!!!! ****** !!!!! ****** !!!!!!
    
    public static double sharedBirthdaysAverage(){	
    	int experimentCount = 0;
    	double totalPairs = 0;
    	
    	while (experimentCount != 1000000) {
    		totalPairs = totalPairs + sharedBirthdays(182, 365);
    		experimentCount++;
    	}
    	return (totalPairs/1000000);
    }
    
    public static void sharedBirthdaysChance(){
    	
    	for (int i=2; i <= 100; i++) {
    		
    		int experimentCount = 0;
    		double sharedBirthdays = 0;
    		
    		while (experimentCount != 50000) {
    			
    			int totalPairs = sharedBirthdays(i, 365);
    			
    			if (totalPairs > 0) {
    				sharedBirthdays++;
    			}
    		experimentCount++;
    		}    	System.out.printf("Num people: %3d, number of experiments with one or more shared birthday: %5.0f, percentage: %6.2f \n", i, sharedBirthdays, ((sharedBirthdays/50000) * 100));
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
        while(isSquare && row < numRows) {
            isSquare = (mat[row] != null) && (mat[row].length == numRows);
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
        while(correct && row < mat.length) {
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
        while(!found && index < list.length) {
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
    
    // make constructor private so no instances of CodeCamp can be created
    private CodeCamp() {
        
    }
}