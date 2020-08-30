import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID: pk9297
 *  email address: preeth.kanamangala@gmail.com
 *  Grader name: Amir
 *  Number of slip days I am using:
 */

/* CS314 Students. Put your experiment results and
 * answers to questions here.

ADD [1000 x 1000] elapsed time: 1.116824526 seconds.
ADD [2000 x 2000] elapsed time: 5.507335649 seconds.
ADD [4000 x 4000] elapsed time: 22.954191326 seconds.
MULTIPLY [250 x 250] elapsed time: 1.221763143 seconds.
MULTIPLY [500 x 500] elapsed time: 18.66074636 seconds.
MULTIPLY [1000 x 1000] elapsed time: 155.261172284 seconds.

1. I expect the add method to take 91.817 seconds if I doubled the dimension size of the MathMatrix objects again. 
2. The Big O of the add operation given by two N by N matrices should be O(N^2). The timing data doesn't exactly support this (the N is higher) but it is close.
3. I expect the multiply method to 1242.089 seconds if I doubled the dimension size of the MathMatrix objects again .
4. The Big O of the multiply operation given two N by N matrices should be O(N^3). The timing data only kind of supports this for 500 -> 1000.
5. I can create around a 27296 x 27296 matrix before my program runs out of heap memory. Note that my laptop has 12 GB of RAM.
   The amount of memory my program is allocated based on the largest possible matrix object it can create successfully is around 2.98 GB.
 */

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

    /**
     * main method that runs simple test on the MathMatrix class
     *
     * @param args not used
     */
    public static void main(String[] args) {
		int[][] data3 = { { 5, 4, 3 }, { 2, 1, 0 } };
		int[][] data4 = { { 1, 2, 3 }, { 4, 5, 6 } };
		int[][] e2;

		// test 1, addition
		MathMatrix mat1 = new MathMatrix(data3);
		MathMatrix mat2 = new MathMatrix(data4);
		MathMatrix mat3 = mat1.add(mat2);
		e2 = new int[][] { { 6, 6, 6 }, { 6, 6, 6 } };
		printTestResult(get2DArray(mat3), e2, 1, "add method. Testing mat3 correct result.");

		int[][] data5 = { { 4, -6 }, { 7, 3 }, { 3, -1 } };
		int[][] data6 = { { 9, 1 }, { 4, -8 }, { 13, 5 } };

		// test 2, addition
		mat1 = new MathMatrix(data5);
		mat2 = new MathMatrix(data6);
		mat3 = mat1.add(mat2);
		e2 = new int[][] { { 13, -5 }, { 11, -5 }, { 16, 4 } };
		printTestResult(get2DArray(mat3), e2, 2, "add method. Testing mat3 correct result.");

		// test 3, subtraction
		mat1 = new MathMatrix(data3);
		mat2 = new MathMatrix(data4);
		mat3 = mat1.subtract(mat2);
		e2 = new int[][] { { 4, 2, 0 }, { -2, -4, -6 } };
		printTestResult(get2DArray(mat3), e2, 3, "subtract method. Testing mat3 correct result.");

		// test 4, subtraction
		mat1 = new MathMatrix(data5);
		mat2 = new MathMatrix(data6);
		mat3 = mat1.subtract(mat2);
		e2 = new int[][] { { -5, -7 }, { 3, 11 }, { -10, -6 } };
		printTestResult(get2DArray(mat3), e2, 4, "subtract method. Testing mat3 correct result.");

		// test 5, getNumRows
		System.out.print("Test number 5 tests the getNumRows method. Testing mat3 correct result.. ");
		if (mat3.getNumRows() == 3) {
			System.out.println("The test passed");
		} else {
			System.out.println("The test failed");
		}

		// test 6, getNumRows
		mat1 = new MathMatrix(data3);
		System.out.print("Test number 6 tests the getNumRows method. Testing mat1 correction result.. ");
		if (mat1.getNumRows() == 2) {
			System.out.println("The test passed");
		} else {
			System.out.println("The test failed");
		}

		// test 7, getNumColumns
		System.out.print("Test number 7 tests the getNumColumns method. Testing mat3 correct result.. ");
		if (mat3.getNumColumns() == 2) {
			System.out.println("The test passed");
		} else {
			System.out.println("The test failed");
		}

		// test 8, getNumColumns
		mat1 = new MathMatrix(data3);
		System.out.print("Test number 8 tests the getNumColumns method. Testing mat1 correct result.. ");
		if (mat1.getNumColumns() == 3) {
			System.out.println("The test passed");
		} else {
			System.out.println("The test failed");
		}

		// test 9, getVal
		System.out.print("Test number 9 tests the getVal method. Testing mat3 correct result.. ");
		if (mat3.getVal(1, 1) == 11) {
			System.out.println("The test passed");
		} else {
			System.out.println("The test failed");
		}

		// test 10, getVal
		System.out.print("Test number 10 tests the getVal method. Testing mat1 correct result.. ");
		if (mat1.getVal(0, 2) == 3) {
			System.out.println("The test passed");
		} else {
			System.out.println("The test failed");
		}

		int[][] data7 = { { 5, 4, 3 }, { 2, 1, 0 } };
		int[][] data8 = { { 1, 2 }, { 3, 4 }, { 5, 6 } };

		// test 11, multiplication
		mat1 = new MathMatrix(data7);
		mat2 = new MathMatrix(data8);
		mat3 = mat1.multiply(mat2);
		e2 = new int[][] { { 32, 44 }, { 5, 8 } };
		printTestResult(get2DArray(mat3), e2, 11, "multiply method. Testing mat3 correct result.");

		// test 12, multiplication
		mat1 = new MathMatrix(data4);
		mat2 = new MathMatrix(data6);
		mat3 = mat1.multiply(mat2);
		e2 = new int[][] { { 56, 0 }, { 134, -6 } };
		printTestResult(get2DArray(mat3), e2, 12, "multiply method. Testing mat3 correct result.");

		// test 13, scaled matrix
		mat3 = mat1.getScaledMatrix(3);
		e2 = new int[][] { { 3, 6, 9 }, { 12, 15, 18 } };
		printTestResult(get2DArray(mat3), e2, 13, "scaled matrix method. Testing mat3 correct result.");

		// test 14, scaled matrix
		mat3 = mat2.getScaledMatrix(2);
		e2 = new int[][] { { 18, 2 }, { 8, -16 }, { 26, 10 } };
		printTestResult(get2DArray(mat3), e2, 14, "scaled matrix method. Testing mat3 correct result.");

		// test 15, transpose
		mat3 = mat1.getTranspose();
		e2 = new int[][] { { 1, 4 }, { 2, 5 }, { 3, 6 } };
		printTestResult(get2DArray(mat3), e2, 15, "transpose matrix method. Testing mat3 correct result.");

		// test 16, transpose
		mat3 = mat2.getTranspose();
		e2 = new int[][] { { 9, 4, 13 }, { 1, -8, 5 } };
		printTestResult(get2DArray(mat3), e2, 16, "transpose matrix method. Testing mat3 correct result.");

		// test 17, toString
		String expected1 = "| 1 2 3|\n| 4 5 6|\n";
		System.out.print("Test number 17 tests the toString method. Testing mat1 correct result.. ");
		if (mat1.toString().equals(expected1)) {
			System.out.println("The test passed");
		} else {
			System.out.println("The test failed");
		}

		// test 18, toString
		expected1 = "|  9  1|\n|  4 -8|\n| 13  5|\n";
		System.out.print("Test number 18 tests the toString method. Testing mat2 correct result.. ");
		if (mat2.toString().equals(expected1)) {
			System.out.println("The test passed");
		} else {
			System.out.println("The test failed");
		}

		// test 19, upperTriangular
		int[][] data1 = { { 3, -50, 9, 0 }, { 0, 12, 11, 2 }, { 0, 0, -5, 6 }, { 0, 0, 0, -10 } };
		mat1 = new MathMatrix(data1);
		System.out.print("Test number 19 tests the upperTriangular method. Testing mat1 correct result.. ");
		if (mat1.isUpperTriangular()) {
			System.out.println("The test passed");
		} else {
			System.out.println("The test failed");
		}

		// test 20, upperTriangular
		data1 = new int[][] { { 9, 1, 2, 0 }, { 0, 5, 1, 7 }, { 0, 0, -5, -3 }, { 1, 0, 0, 4 } };
		mat1 = new MathMatrix(data1);
		System.out.print("Test number 20 tests the upperTriangular method. Testing mat1 correct result.. ");
		if (!mat1.isUpperTriangular()) {
			System.out.println("The test passed");
		} else {
			System.out.println("The test failed");
		}

		// test 21, equals
		data1 = new int[][] { { 3, -50, 9, 0 }, { 0, 12, 11, 2 }, { 0, 0, -5, 6 }, { 0, 0, 0, -10 } };
		mat1 = new MathMatrix(data1);
		int[][] data2 = { { 3, -50, 9, 0 }, { 0, 12, 11, 2 }, { 1, 0, -5, 6 }, { 0, 0, 0, -10 } };
		MathMatrix mat4 = new MathMatrix(data2);
		System.out.print("Test number 21 tests the equals method. Testing mat1 correct result.. ");
		if (!mat1.equals(mat4)) {
			System.out.println("The test passed");
		} else {
			System.out.println("The test failed");
		}

		// test 22, equals
		data1 = new int[][] { { 3, -50, 9, 0 }, { 0, 33, 11, 2 }, { 1, -2, -5, 6 }, { 0, 23, 0, -10 } };
		mat1 = new MathMatrix(data1);
		data2 = new int[][] { { 3, -50, 9, 0 }, { 0, 33, 11, 2 }, { 1, -2, -5, 6 }, { 0, 23, 0, -10 } };
		mat4 = new MathMatrix(data2);
		System.out.print("Test number 22 tests the equals method. Testing mat1 correct result.. ");
		if (mat1.equals(mat4)) {
			System.out.println("The test passed");
		} else {
			System.out.println("The test failed");
		}
   
		//EXPERIMENT 1
		Random rand = new Random();
		Stopwatch s = new Stopwatch();
		
		int dimensions = 1000;
		for (int i = 0; i < 3; i++) {
			MathMatrix test1 = createMat(rand, dimensions, dimensions, 1000);
			MathMatrix test2 = createMat(rand, dimensions, dimensions, 1000);
			s.start();
			for (int j = 0; j < 1000; j++) {
				test1.add(test2);
			}
			s.stop();
			System.out.println("ADD [" + dimensions + " x " + dimensions + "] " + s.toString());
			dimensions = dimensions * 2;
		}
		
		//EXPERIMENT 2
		dimensions = 250;
		for (int i = 0; i < 3; i++) {
			MathMatrix test1 = createMat(rand, dimensions, dimensions, 1000);
			MathMatrix test2 = createMat(rand, dimensions, dimensions, 1000);
			s.start();
			for (int j = 0; j < 100; j++) {
				test1.multiply(test2);
			}
			s.stop();
			System.out.println("MULTIPLY [" + dimensions + " x " + dimensions + "] " + s.toString());
			dimensions = dimensions * 2;
		}

    }

    // method that sums elements of mat, may overflow int!
    // pre: mat != null
	private static int sumVals(MathMatrix mat) {
		if (mat == null) {
			throw new IllegalArgumentException("mat may not be null");
		}
		int result = 0;
		final int ROWS = mat.getNumRows();
		final int COLS = mat.getNumColumns();
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				result += mat.getVal(r, c); // likely to overflow, but can still do simple check
			}
		}
		return result;
	}

    // create a matrix with random values
    // pre: rows > 0, cols > 0, randNumGen != null
	public static MathMatrix createMat(Random randNumGen, int rows, int cols, final int LIMIT) {

		if (randNumGen == null) {
			throw new IllegalArgumentException("randomNumGen variable may no be null");
		} else if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException(
					"rows and columns must be greater than 0. " + "rows: " + rows + ", cols: " + cols);
		}

		int[][] temp = new int[rows][cols];
		final int SUB = LIMIT / 4;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
			}
		}

		return new MathMatrix(temp);
	}

	private static void printTestResult(int[][] data1, int[][] data2, int testNum, String testingWhat) {
		System.out.print("Test number " + testNum + " tests the " + testingWhat + ". The test ");
		String result = equals(data1, data2) ? "passed" : "failed";
		System.out.println(result);
	}

    // pre: m != null, m is at least 1 by 1 in size
    // return a 2d array of ints the same size as m and with 
    // the same elements
	private static int[][] get2DArray(MathMatrix m) {
		// check precondition
		if ((m == null) || (m.getNumRows() == 0) || (m.getNumColumns() == 0)) {
			throw new IllegalArgumentException("Violation of precondition: get2DArray");
		}

		int[][] result = new int[m.getNumRows()][m.getNumColumns()];
		for (int r = 0; r < result.length; r++) {
			for (int c = 0; c < result[0].length; c++) {
				result[r][c] = m.getVal(r, c);
			}
		}
		return result;
	}

    // pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1 matrices
    //      data1 and data2 are rectangular matrices
    // post: return true if data1 and data2 are the same size and all elements are
    //      the same
	private static boolean equals(int[][] data1, int[][] data2) {
		// check precondition
		if ((data1 == null) || (data1.length == 0) || (data1[0].length == 0) || !rectangularMatrix(data1)
				|| (data2 == null) || (data2.length == 0) || (data2[0].length == 0) || !rectangularMatrix(data2)) {
			throw new IllegalArgumentException("Violation of precondition: equals check on 2d arrays of ints");
		}
		boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
		int row = 0;
		while (result && row < data1.length) {
			int col = 0;
			while (result && col < data1[0].length) {
				result = (data1[row][col] == data2[row][col]);
				col++;
			}
			row++;
		}

		return result;
	}


    // method to ensure mat is rectangular
    // pre: mat != null, mat is at least 1 by 1
	private static boolean rectangularMatrix(int[][] mat) {
		if (mat == null || mat.length == 0 || mat[0].length == 0) {
			throw new IllegalArgumentException("Violation of precondition: Parameter mat may not be null and must be at least 1 by 1");
		}
		return MathMatrix.rectangularMatrix(mat);
	}
}