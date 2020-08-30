/*  Student information for assignment:
 *
 *  On MY honor, Preeth Kanamangala, this programming assignment is MY own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: PK9297
 *  email address: preeth.kanamangala@gmail.com
 *  Grader name: Amir
 *  Section number: 50240
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Various recursive methods to be implemented.
 * Given shell file for CS314 assignment.
 */
public class Recursive {

    /**
     * Problem 1: convert a base 10 int to binary recursively.
     *   <br>pre: n != Integer.MIN_VALUE<br>
     *   <br>post: Returns a String that represents N in binary.
     *   All chars in returned String are '1's or '0's. 
     *   Most significant digit is at position 0
     *   @param n the base 10 int to convert to base 2
     *   @return a String that is a binary representation of the parameter n
     */
	public static String getBinary(int n) {
		if (n == Integer.MIN_VALUE) {
			throw new IllegalArgumentException("Failed precondition: getBinary. " + "n cannot equal Integer.MIN_VALUE. n: " + n);
		}

		String binaryString = "";
		if (n / 2 == 0) { // base case is when the number reaches 0 or 1 (there are no divisions left to be made)
			return Integer.toString(n % 2);
		} else {
			binaryString = getBinary(n / 2) + Integer.toString(Math.abs(n % 2)); // the recursive step is making the call for half of the number
		}
		return binaryString;
	}

    /**
     * Problem 2: reverse a String recursively.<br>
     *   pre: stringToRev != null<br>
     *   post: returns a String that is the reverse of stringToRev
     *   @param stringToRev the String to reverse.
     *   @return a String with the characters in stringToRev in reverse order.
     */
    public static String revString(String stringToRev) {
		if (stringToRev == null) {
			throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");
		}

		if (stringToRev.isEmpty()) { // base case is if there are no more characters left in the string
			return stringToRev;
		}
		String firstChar = Character.toString(stringToRev.charAt(0));
		String subString = stringToRev.substring(1);
		return revString(subString) + firstChar; // the recursive step is making the call with one less char at the front of the string
	}

    /**
     * Problem 3: Returns the number of elements in data
     * that are followed directly by value that is
     * double that element.
     * pre: data != null
     * post: return the number of elements in data that are followed immediately by double the value
     * @param data The array to search.
     * @return The number of elements in data that are followed immediately by a value that
     * is double the element.
     */
	public static int nextIsDouble(int[] data) {
		if (data == null) {
			throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");
		}

		return isDouble(data, 0);
    }

	public static int isDouble(int[] data, int index) {
		int countNextIsDouble = 0;

		if (index >= data.length - 1) { // base case is when we reach the end of the array
			return 0;
		} else {
			if (2 * data[index] == data[index + 1]) { // the recursive step is checking if the NEXT element is a double
				countNextIsDouble = ++countNextIsDouble + isDouble(data, index + 1); // we increment the count if it is double
			} else {
				countNextIsDouble = countNextIsDouble + isDouble(data, index + 1);
			}
		}
		return countNextIsDouble;
	}

    /**  Problem 4: Find all combinations of mnemonics for the given number.<br>
     *   pre: number != null, number.length() > 0, all characters in number are digits<br>
     *   post: see tips section of assignment handout
     *   @param number The number to find mnemonics for
     *   @return An ArrayList<String> with all possible mnemonics for the given number
     */
	public static ArrayList<String> listMnemonics(String number) {
		if (number == null || number.length() == 0 || !allDigits(number)) {
			throw new IllegalArgumentException("Failed precondition: listMnemonics");
		}

		ArrayList<String> mnemonics = new ArrayList<String>();
		recursiveMnemonics(mnemonics, "", number);
		return mnemonics;
	}

    /*
     * Helper method for listMnemonics
     * mnemonics stores completed mnemonics
     * mneominicSoFar is a partial (possibly complete) mnemonic
     * digitsLeft are the digits that have not been used from the original number
     */
	private static void recursiveMnemonics(ArrayList<String> mnemonics, String mnemonicSoFar, String digitsLeft) {
		if (digitsLeft.equals("")) { // base case is if there are no digits left in the string
			mnemonics.add(mnemonicSoFar);
		} else {
			String digitLetters = digitLetters(digitsLeft.charAt(0)); // all the possible letters for this digit
			for (int index = 0; index < digitLetters.length(); index++) {
				mnemonicSoFar = mnemonicSoFar.concat(Character.toString(digitLetters.charAt(index))); // make a letter choice
				recursiveMnemonics(mnemonics, mnemonicSoFar, digitsLeft.substring(1)); // recursive step is to choose one letter then call the method on remaining digits
				mnemonicSoFar = mnemonicSoFar.substring(0, mnemonicSoFar.length() - 1); // undo the letter choice
			}
		}
	}

    // used by method digitLetters
    private static final String[] letters = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    /* helper method for recursiveMnemonics
     * pre: ch is a digit '0' through '9'
     * post: return the characters associated with this digit on a phone keypad
     */
	private static String digitLetters(char ch) {
		if (ch < '0' || ch > '9') {
			throw new IllegalArgumentException("parameter ch must be a digit, 0 to 9. Given value = " + ch);
		}
		int index = ch - '0';
		return letters[index];
	}

    /* helper method for listMnemonics
     * pre: s != null
     * post: return true if every character in s is a digit ('0' through '9')
     * */
	private static boolean allDigits(String s) {
		if (s == null) {
			throw new IllegalArgumentException("Failed precondition: allDigits. String s cannot be null.");
		}
		boolean allDigits = true;
		int i = 0;
		while (i < s.length() && allDigits) {
			allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
			i++;
		}
		return allDigits;
	}

    /**
     * Problem 5: Draw a Sierpinski Carpet.
     * @param size the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     */
    public static void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,size,size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }

    /* Helper method for drawCarpet
     * Draw the individual squares of the carpet.
     * @param g The Graphics object to use to fill rectangles
     * @param size the size of the current square
     * @param limit the smallest allowable size of squares
     * @param x the x coordinate of the upper left corner of the current square
     * @param y the y coordinate of the upper left corner of the current square
     */
	private static void drawSquares(Graphics g, int size, int limit, double x, double y) {
		g.fillRect((int) x + size / 3, (int) y + size / 3, size / 3, size / 3); // fill like this since we want to divide into a 3 x 3 grid

		if (size > limit) { // the base case is when the size is less than or equal to the limit
			size = size / 3;
			for (double xCoor = x; xCoor <= x + 2 * size; xCoor += size) { // this loop essentially moves the coordinates around the cut out middle square
				for (double yCoor = y; yCoor <= y + 2 * size; yCoor += size) {
					drawSquares(g, size, limit, xCoor, yCoor); // the recursive step is cutting the fill size down while moving around the x and y coordinates
				}
			}
		}
	}   
    
    /**
     * Problem 6: Determine if water at a given point
     * on a map can flow off the map.
     * <br>pre: map != null, map.length > 0,
     * map is a rectangular matrix, 0 <= row < map.length, 0 <= col < map[0].length
     * <br>post: return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map, false otherwise.
     * @param map The elevations of a section of a map.
     * @param row The starting row of a drop of water.
     * @param col The starting column of a drop of water.
     * @return return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map, false otherwise.
     */
	public static boolean canFlowOffMap(int[][] map, int row, int col) {
		if (map == null || map.length == 0 || !isRectangular(map) || !inbounds(row, col, map)) {
			throw new IllegalArgumentException("Failed precondition: canFlowOffMap");
		}

		if (row == 0 || col == 0 || row == map.length - 1 || col == map[0].length - 1) { // base case is if the row or column we moved to is on the edge
			return true;
		}
		int[][] directionsArray = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // represents all four directions
		for (int index = 0; index < directionsArray.length; index++) {
			row += directionsArray[index][0]; // makes a move out of the four directions
			col += directionsArray[index][1];
			if (col >= 0 && col < map[0].length && row >= 0 && row < map.length) { // the following two statements check if our move was allowed
				if (map[row][col] < map[row - directionsArray[index][0]][col - directionsArray[index][1]]) {
					if ((canFlowOffMap(map, row, col))) { // the recursive step is moving a space if conditions are satisfied
						return true;
					}
				}
			}
			row -= directionsArray[index][0]; // undo the move so we can try all combinations
			col -= directionsArray[index][1];
		}
		return false; // the base case is also if all moves have been tried and the row or column cannot reach the edge
	}

    /* helper method for canFlowOfMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null,
     */
	private static boolean inbounds(int r, int c, int[][] mat) {
		if (mat == null) {
			throw new IllegalArgumentException("Failed precondition: inbounds. The 2d array mat may not be null.");
		}
		return r >= 0 && r < mat.length && mat[r] != null && c >= 0 && c < mat[r].length;
	}

    /*
     * helper method for canFlowOfMap - CS314 stdents you should not have to
     * call this method,
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
	private static boolean isRectangular(int[][] mat) {
		assert (mat != null) && (mat.length > 0) : "Violation of precondition: isRectangular";
		if (mat == null || mat.length == 0) {
			throw new IllegalArgumentException("Failed precondition: inbounds. The 2d array mat may not be null and must have at least 1 row.");
		}
		boolean correct = true;
		final int numCols = mat[0].length;
		int row = 0;
		while (correct && row < mat.length) {
			correct = (mat[row] != null) && (mat[row].length == numCols);
			row++;
		}
		return correct;
	}

    /**
     * Problem 7: Find the minimum difference possible between teams
     * based on ability scores. The number of teams may be greater than 2.
     * The goal is to minimize the difference between the team with the
     * maximum total ability and the team with the minimum total ability.
     * <br> pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * <br> post: return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability.
     * @param numTeams the number of teams to form.
     * Every team must have at least one member
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability. The return value will be greater than or equal to 0.
     * @throws Exception 
     */
	public static int minDifference(int numTeams, int[] abilities) throws Exception {
		if (numTeams < 2 || abilities == null || abilities.length < numTeams) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		int minDifference = minDifferenceHelper(0, new int[numTeams], abilities, new int[numTeams]);
		return minDifference;
	}
    
	// this is the helper method that does the actual recursion
	private static int minDifferenceHelper(int index, int[] teamAbilities, int[] playerAbilities, int[] numPeople) {
		if (index == playerAbilities.length) { // the base case is if we've gone through every player
			if (hasEmpty(numPeople)) {
				return Integer.MAX_VALUE;
			}
			return findMaxMinDifference(teamAbilities); // after the teams have been filled we find the difference
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < teamAbilities.length; i++) {
			teamAbilities[i] += playerAbilities[index]; // make a player to team choice
			numPeople[i]++;
			min = Math.min(min, minDifferenceHelper(index + 1, teamAbilities, playerAbilities, numPeople)); // recursive step is calling method for NEXT player
			teamAbilities[i] -= playerAbilities[index]; // undo this player choice so we can test all configurations
			numPeople[i]--;
		}

		return min;
	}

	// this method checks if there is at least one player on each team
	private static boolean hasEmpty(int[] numPeople) {
		for (int index = 0; index < numPeople.length; index++) {
			if (numPeople[index] == 0) {
				return true;
			}
		}
		return false;
	}
    
	// this method finds the difference between the team with the maximum ability and the team with the minimum ability
	private static int findMaxMinDifference(int[] teams) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for (int index = 0; index < teams.length; index++) {
			if (teams[index] > max) {
				max = teams[index];
			}
			if (teams[index] < min) {
				min = teams[index];
			}
		}
		return max - min;
	}

    /**
     * Problem 8: Maze solver. Return 2 if it is possible to escape the maze after
     * collecting all the coins. Return 1 if it is possible to escape the maze 
     * but without collecting all the coins. Return 0 if it is not possible
     * to escape the maze. More details in the assignment handout.
     * <br>pre: board != null
     * <br>pre: board is a rectangular matrix
     * <br>pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*'
     * <br>pre: there is a single 'S' character present
     * <br>post: rawMaze is not altered as a result of this method.
     * Return 2 if it is possible to escape the maze after
     * collecting all the coins. Return 1 if it is possible to escape the maze 
     * but without collecting all the coins. Return 0 if it is not possible
     * to escape the maze. More details in the assignment handout.
     * @param rawMaze represents the maze we want to escape. 
     * rawMaze is not altered as a result of this method.
     * @return per the post condition
     */
	final static char START = 'S';
	final static char EXIT = 'E';
	final static char COIN = '$';
	final static char GREEN = 'G';
	final static char YELLOW = 'Y';
	final static char IMPASSABLE = '*';

	public static int canEscapeMaze(char[][] rawMaze) {
		if (rawMaze == null || !containsChars(rawMaze) || !containsStart(rawMaze) || !isRectangular(rawMaze)) {
			throw new IllegalArgumentException("Does not satisfy pre-conditions.");
		}

		int numCoins = numCoins(rawMaze); // total number of coins in the maze
		int row = getRowCol(rawMaze)[0]; // row of start
		int col = getRowCol(rawMaze)[1]; // column of start
		char[][] copyRawMaze = Arrays.copyOf(rawMaze, rawMaze.length); // do all operations on a copy so that the original array is not altered
		copyRawMaze[row][col] = GREEN;
		return canEscapeMazeHelper(copyRawMaze, numCoins, 0, row, col); // note that currCoins stores how many coins we have on the current path
	}
    
	private static int canEscapeMazeHelper(char[][] copyRawMaze, int numCoins, int currCoins, int row, int col) {
		if (copyRawMaze[row][col] == EXIT) { // base case is reaching the row and column of the exit cell
			return currCoins == numCoins ? 2 : 1;
		}
		int max = 0;
		int[][] directionArray = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // array for all four directions
		for (int i = 0; i < directionArray.length; i++) {
			row += directionArray[i][0]; // we make a move in one of the four directions
			col += directionArray[i][1];
			if (col >= 0 && col < copyRawMaze[0].length && row >= 0 && row < copyRawMaze.length && copyRawMaze[row][col] != IMPASSABLE) { // if a move is legal
				int undo = 0; // this undo variable is just an identifier to be able to restore a cell to its previous state
				if (copyRawMaze[row][col] == COIN) {
					copyRawMaze[row][col] = YELLOW;
					currCoins++;
					undo = 1;
				} else if (copyRawMaze[row][col] == GREEN) {
					copyRawMaze[row][col] = YELLOW;
					undo = 2;
				} else if (copyRawMaze[row][col] == YELLOW) {
					copyRawMaze[row][col] = IMPASSABLE;
					undo = 3;
				}
				max = Math.max(max, canEscapeMazeHelper(copyRawMaze, numCoins, currCoins, row, col)); // recursive step is calling method after a move is made
				if (undo == 1) {
					currCoins--;
				}
				copyRawMaze = undoCell(undo, copyRawMaze, row, col); // restore state of cell
			}
			row -= directionArray[i][0]; // this is where we undo our move to try other combinations
			col -= directionArray[i][1];
		}
		return max; // the final base case is if we can't reach an exit after all combinations (max would remain 0)
	}
    
	// this helper method restores the state of cell based on the undo identifier
	private static char[][] undoCell(int undo, char[][] copyRawMaze, int row, int col) {
		if (undo == 1) {
			copyRawMaze[row][col] = COIN;
		} else if (undo == 2) {
			copyRawMaze[row][col] = GREEN;
		} else if (undo == 3) {
			copyRawMaze[row][col] = YELLOW;
		}
		return copyRawMaze;
	}
	
	// this helper method checks if the board only contains the allowed characters
	private static boolean containsChars(char[][] rawMaze) {
		for (int row = 0; row < rawMaze.length; row++) {
			for (int col = 0; col < rawMaze[0].length; col++) {
				if (rawMaze[row][col] != 'S' && rawMaze[row][col] != 'E' && rawMaze[row][col] != '$' && rawMaze[row][col] != 'G'
						&& rawMaze[row][col] != 'Y' && rawMaze[row][col] != '*') {
					return false;
				}
			}
		}
		return true;
	}
    
	// this helper method checks if the board contains a single start
	private static boolean containsStart(char[][] rawMaze) {
		int count = 0;
		for (int row = 0; row < rawMaze.length; row++) {
			for (int col = 0; col < rawMaze[0].length; col++) {
				if (rawMaze[row][col] == 'S') {
					count++;
				}
			}
		}
		return (count < 1 || count > 1) ? false : true;
	}
    
	// this helper method finds the row and column of the starting cell
	private static int[] getRowCol(char[][] copyRawMaze) {
		int[] rowCol = new int[2];
		for (int row = 0; row < copyRawMaze.length; row++) {
			for (int col = 0; col < copyRawMaze[0].length; col++) {
				if (copyRawMaze[row][col] == 'S') {
					rowCol[0] = row;
					rowCol[1] = col;
				}
			}
		}
		return rowCol;
	}
    
    // this helper method counts the number of coins in the maze
    private static int numCoins(char[][] rawMaze) {
		int count = 0;
		for (int row = 0; row < rawMaze.length; row++) {
			for (int col = 0; col < rawMaze[0].length; col++) {
				if (rawMaze[row][col] == '$') {
					count++;
				}
			}
		}
		return count;
	}
    
    // this helper method checks if the board is rectangular (used from Mike Scott's helper method on Problem #6)
	private static boolean isRectangular(char[][] mat) {
		assert (mat != null) && (mat.length > 0) : "Violation of precondition: isRectangular";
		if (mat == null || mat.length == 0) {
			throw new IllegalArgumentException("Failed precondition: inbounds. The 2d array mat may not be null and must have at least 1 row.");
		}
		boolean correct = true;
		final int numCols = mat[0].length;
		int row = 0;
		while (correct && row < mat.length) {
			correct = (mat[row] != null) && (mat[row].length == numCols);
			row++;
		}
		return correct;
	}
}