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

import java.util.HashSet;

/**
 * Tester class for the methods in Recursive.java
 * @author scottm
 *
 */

public class RecursiveTester {

    // run the tests
    public static void main(String[] args) throws Exception {
        studentTests();
    }

    // generate a char[][] given the raw string
    // pre: rawMaze != null, rawMaze.length() % rows == 0
    private static char[][] makeMaze(String rawMaze, int rows) {
        if (rawMaze == null || rawMaze.length() % rows != 0) {
            throw new IllegalArgumentException("Violation of precondition in makeMaze."
                            + "Either raw data is null or left over values: ");
        }
        int cols = rawMaze.length() / rows;
        char[][] result = new char[rows][cols];
        int rawIndex = 0;
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
                result[r][c] = rawMaze.charAt(rawIndex);
                rawIndex++;
            }
        }
        return result;
    }

    // pre: r != null
    // post: run student test
    private static void studentTests() throws Exception {
        // CS314 students put your tests here
		// test 1: getBinary
		String expected = "0";
		String actual = Recursive.getBinary(0);
		printDebugStuff(expected.equals(actual), "binary");

		// test 2: getBinary
		expected = "-101";
		actual = Recursive.getBinary(-5);
		printDebugStuff(expected.equals(actual), "binary");

		// test 3: reverseString
		expected = "nekcihc";
		actual = Recursive.revString("chicken");
		printDebugStuff(expected.equals(actual), "reverseString");

		// test 4: reverseString
		expected = "bb aa";
		actual = Recursive.revString("aa bb");
		printDebugStuff(expected.equals(actual), "reverseString");

		// test 5: nextIsDouble
		int expectedInt = 3;
		int[] data = { -1, -2, 0, 0, 1, 3, 6 };
		int actualInt = Recursive.nextIsDouble(data);
		printDebugStuff(expectedInt == actualInt, "nextIsDouble");

		// test 6: nextIsDouble
		expectedInt = 3;
		data = new int[] { 0, 2, 4, 8, 16};
		actualInt = Recursive.nextIsDouble(data);
		printDebugStuff(expectedInt == actualInt, "nextIsDouble");

		// test 7: recursiveMnemonics
		HashSet<String> expectedWords = new HashSet<>();
		String[] words = { "0" };
		for (String w : words)
			expectedWords.add(w);
		HashSet<String> actualWords = new HashSet<>(Recursive.listMnemonics("0"));
		printDebugStuff(expectedWords.equals(actualWords), "recursiveMnemonics");

		// test 8: recursiveMnemonics
		expectedWords.clear();
		words = new String[] { "1W", "1X", "1Y", "1Z" };
		for (String w : words)
			expectedWords.add(w);
		actualWords = new HashSet<>(Recursive.listMnemonics("19"));
		printDebugStuff(expectedWords.equals(actualWords), "recursiveMnemonics");

		// test 9: canFlowOffMap
		int[][] map = { { 1, 0, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		printDebugStuff(!Recursive.canFlowOffMap(map, 1, 1), "canFlowOffMap");

		// test 10: canFlowOffMap
		map = new int[][] { { 4, 3, 8, 5, 2 }, 
							{ 0, 4, 4, 4, 8 }, 
							{ 2, 4, 3, 1, 7 }, 
							{ 5, 1, 2, 0, 9 },
							{ 7, 0, 2, 8, 1 } };
		printDebugStuff(Recursive.canFlowOffMap(map, 1, 2), "canFlowOffMap");

		// test 11: minDifference
		int[] abilities = { 1, 2 };
		boolean passed = Recursive.minDifference(2, abilities) == 1;
		printDebugStuff(passed, "minDifference");

		// test 12: minDifference
		abilities = new int[] { -10, 5, -10, -5, -10 };
		passed = Recursive.minDifference(2, abilities) == 0;
		printDebugStuff(passed, "minDifference");

		// test 13: canEscapeMaze
		String rawMaze = "ES$$$G$";
		char[][] maze = makeMaze(rawMaze, 1);
		passed = Recursive.canEscapeMaze(maze) == 2;
		printDebugStuff(passed, "canEscapeMaze");

		// test 14: canEscapeMaze
		rawMaze = "*EYYSY$*";
		maze = makeMaze(rawMaze, 1);
		passed = Recursive.canEscapeMaze(maze) == 1;
		printDebugStuff(passed, "canEscapeMaze");
	}

	private static void printDebugStuff(boolean passed, String test) {
		if (passed) {
			System.out.println(test + " test PASSED");
		} else {
			System.out.println(test + " test FAILED");
		}
	}
}