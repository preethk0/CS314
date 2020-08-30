import java.util.Arrays;
import java.lang.Math;
//MathMatrix.java - CS314 Assignment 2

/*  Student information for assignment: Preeth Kanamangala
*
*  On my honor, Preeth Kanamangala, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: pk9297
*  email address: preeth.kanamangala@gmail.com
*  Unique section number: 50240
*  Number of slip days I am using:
*/

/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 */
public class MathMatrix {
    
	private int[][] arr;

    /**
     * create a MathMatrix with cells equal to the values in mat.
     * A "deep" copy of mat is made.
     * Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     * @param  mat  mat !=null, mat.length > 0, mat[0].length > 0,
     * mat is a rectangular matrix
     */
	public MathMatrix(int[][] mat) {
		if (mat == null || mat.length <= 0 || mat[0].length <= 0 || !rectangularMatrix(mat)) {
			throw new IllegalArgumentException("Pre-conditions are not met.");
		}

		arr = new int[mat.length][mat[0].length];

		for (int r = 0; r < mat.length; r++) {
			for (int c = 0; c < mat[0].length; c++) {
				arr[r][c] = mat[r][c];
			}
		}
	}

    /**
     * create a MathMatrix of the specified size with all cells set to the intialValue.
     * <br>pre: numRows > 0, numCols > 0
     * <br>post: create a matrix with numRows rows and numCols columns. 
     * All elements of this matrix equal initialVal.
     * In other words after this method has been called getVal(r,c) = initialVal 
     * for all valid r and c.
     * @param numRows numRows > 0
     * @param numCols numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
	public MathMatrix(int numRows, int numCols, int initialVal) {
		if (numRows <= 0 || numCols <= 0) {
			throw new IllegalArgumentException("Pre-conditions are not met.");
		}

		arr = new int[numRows][numCols];

		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numCols; c++) {
				arr[r][c] = initialVal;
			}
		}
	}

    /**
     * Get the number of rows.
     * @return the number of rows in this MathMatrix
     */
	public int getNumRows() {
		return arr.length;
	}

    /**
     * Get the number of columns.
     * @return the number of columns in this MathMatrix
     */
	public int getNumColumns() {
		return arr[0].length;
	}

    /**
     * get the value of a cell in this MathMatrix.
     * <br>pre: row  0 <= row < getNumRows(), col  0 <= col < getNumColumns()
     * @param  row  0 <= row < getNumRows()
     * @param  col  0 <= col < getNumColumns()
     * @return the value at the specified position
     */
	public int getVal(int row, int col) {
		if (row < 0 || row >= getNumRows() || col < 0 || col >= getNumColumns()) {
			throw new IllegalArgumentException("Pre-conditions are not met.");
		}
		return arr[row][col];
	}

    /**
     * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of adding this Matrix to rightHandSide.
     * The number of rows in the returned Matrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned Matrix is equal to the number of columns in this MathMatrix.
     */
	public MathMatrix add(MathMatrix rightHandSide) {
		if (rightHandSide.getNumRows() != getNumRows() || rightHandSide.getNumColumns() != getNumColumns()) {
			throw new IllegalArgumentException("Pre-conditions are not met.");
		}

		MathMatrix answerMatrix = new MathMatrix(getNumRows(), getNumColumns(), 0);

		for (int r = 0; r < getNumRows(); r++) {
			for (int c = 0; c < getNumColumns(); c++) {
				answerMatrix.arr[r][c] = getVal(r, c) + rightHandSide.getVal(r, c);
			}
		}
		return answerMatrix;
	}

    /**
     * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * <br>post: This method does not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(), rightHandSide.numCols() = getNumColumns()
     * @return a new MathMatrix that is the result of subtracting rightHandSide from this MathMatrix.
     * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned MathMatrix is equal to the number of columns in this MathMatrix.
     */
	public MathMatrix subtract(MathMatrix rightHandSide) {
		if (rightHandSide.getNumRows() != getNumRows() || rightHandSide.getNumColumns() != getNumColumns()) {
			throw new IllegalArgumentException("Pre-conditions are not met.");
		}

		MathMatrix answerMatrix = new MathMatrix(getNumRows(), getNumColumns(), 0);

		for (int r = 0; r < getNumRows(); r++) {
			for (int c = 0; c < getNumColumns(); c++) {
				answerMatrix.arr[r][c] = getVal(r, c) - rightHandSide.getVal(r, c);
			}
		}
		return answerMatrix;
	}

    /**
     * implements matrix multiplication, (this MathMatrix) * rightHandSide.
     * <br>pre: rightHandSide.getNumRows() = getNumColumns()
     * <br>post: This method should not alter the calling object or rightHandSide
     * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
     * @return a new MathMatrix that is the result of multiplying this MathMatrix and rightHandSide.
     * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
     * The number of columns in the returned MathMatrix is equal to the number of columns in rightHandSide.
     */
	public MathMatrix multiply(MathMatrix rightHandSide) {
		if (rightHandSide.getNumRows() != getNumColumns()) {
			throw new IllegalArgumentException("Pre-conditions are not met.");
		}

		MathMatrix answerMatrix = new MathMatrix(getNumRows(), rightHandSide.getNumColumns(), 0);
		// matchColumnsRows represents the fact that the columns of the original matrix
		// equals and changes as the rows of the rightHandSide matrix in this
		// algorithm do
		for (int r = 0; r < getNumRows(); r++) {
			for (int c = 0; c < rightHandSide.getNumColumns(); c++) {
				int sumValue = 0; // holds and assigns each value (which is a summation of a certain number of
									// multiplication expressions) in the resulting matrix
				for (int matchColumnsRows = 0; matchColumnsRows < getNumColumns(); matchColumnsRows++) {
					sumValue = sumValue + getVal(r, matchColumnsRows) * rightHandSide.getVal(matchColumnsRows, c);
				}
				answerMatrix.arr[r][c] = sumValue;
			}
		}
		return answerMatrix;
	}

    /**
     * Create and return a new Matrix that is a copy
     * of this matrix, but with all values multiplied by a scale
     * value.
     * <br>pre: none
     * <br>post: returns a new Matrix with all elements in this matrix 
     * multiplied by factor. 
     * In other words after this method has been called 
     * returned_matrix.getVal(r,c) = original_matrix.getVal(r, c) * factor
     * for all valid r and c.
     * @param factor the value to multiply every cell in this Matrix by.
     * @return a MathMatrix that is a copy of this MathMatrix, but with all
     * values in the result multiplied by factor.
     */
	public MathMatrix getScaledMatrix(int factor) {
		MathMatrix answerMatrix = new MathMatrix(getNumRows(), getNumColumns(), 0);

		for (int r = 0; r < getNumRows(); r++) {
			for (int c = 0; c < getNumColumns(); c++) {
				answerMatrix.arr[r][c] = getVal(r, c) * factor;
			}
		}
		return answerMatrix;
	}

    /**
     * accessor: get a transpose of this MathMatrix. 
     * This Matrix is not changed.
     * <br>pre: none
     * @return a transpose of this MathMatrix
     */
	public MathMatrix getTranspose() {
		MathMatrix answerMatrix = new MathMatrix(getNumColumns(), getNumRows(), 0);

		for (int r = 0; r < getNumRows(); r++) {
			for (int c = 0; c < getNumColumns(); c++) {
				answerMatrix.arr[c][r] = getVal(r, c);
			}
		}
		return answerMatrix;
	}

    /**
     * override equals.
     * @return true if rightHandSide is the same size as this MathMatrix and all values in the
     * two MathMatrix objects are the same, false otherwise
     */
	public boolean equals(Object rightHandSide) {
		boolean result = false;

		if (rightHandSide != null && this.getClass() == rightHandSide.getClass()) {
			MathMatrix otherMatrix = (MathMatrix) rightHandSide;
			result = equalsCheck(otherMatrix);
		}
		return result;
	}
    
	// this helper method gives the actual result of if the rightHandSide is the
	// same size and all values in these two MathMatrix objects are the same, given
	// that the othermatrix is not null and is of the same object type
	private boolean equalsCheck(MathMatrix otherMatrix) {
		if (otherMatrix.getNumRows() == getNumRows() && otherMatrix.getNumColumns() == getNumColumns()) {
			for (int r = 0; r < getNumRows(); r++) {
				for (int c = 0; c < getNumColumns(); c++) {
					if (getVal(r, c) != otherMatrix.getVal(r, c)) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

    /**
     * override toString.
     * @return a String with all elements of this MathMatrix. 
     * Each row is on a separate line.
     * Spacing based on longest element in this Matrix.
     */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int max = findMax();
		// the number of spaces between each element equals the number of characters of
		// the longest element in this matrix minus the number of characters of a
		// specific element plus the default one space
		for (int r = 0; r < getNumRows(); r++) {
			sb.append("|");
			for (int c = 0; c < getNumColumns(); c++) {
				int numSpaces = (max - (String.valueOf(arr[r][c]).length()) + 1);
				for (int i = 0; i < numSpaces; i++) {
					sb.append(" ");
				}
				sb.append(getVal(r, c));
			}
			sb.append("|\n");
		}
		return sb.toString();
	}

	// this helper method finds the number of characters of the longest element in
	// this matrix
	private int findMax() {
		int max = String.valueOf(arr[0][0]).length();

		for (int r = 0; r < getNumRows(); r++) {
			for (int c = 0; c < getNumColumns(); c++) {
				int compare = String.valueOf(arr[r][c]).length();
				if (compare > max) {
					max = compare;
				}
			}
		}
		return max;
	}

    /**
     * Return true if this MathMatrix is upper triangular. To
     * be upper triangular all elements below the main 
     * diagonal must be 0.<br>
     * pre: this is a square matrix. getNumRows() == getNumColumns()  
     * @return <tt>true</tt> if this MathMatrix is upper triangular,
     * <tt>false</tt> otherwise. 
     */
	public boolean isUpperTriangular() {
		if (getNumRows() != getNumColumns()) {
			throw new IllegalArgumentException("Pre-conditions are not met.");
		}
		if (getNumRows() == 1) { // takes care of 1 x 1 edge case
			return true;
		}

		int columnsToCheck = 1;

		for (int r = 1; r < getNumRows(); r++) {
			for (int c = 0; c < columnsToCheck; c++) {
				if (arr[r][c] != 0) {
					return false;
				}
			}
			columnsToCheck++; // accommodates for "stair-step" checking
		}
		return true;
	}

    // method to ensure mat is rectangular
    // pre: mat != null, mat has at least one row
    // return true if all rows in mat have the same
    // number of columns false otherwise.
	public static boolean rectangularMatrix(int[][] mat) {
		if (mat == null || mat.length == 0) {
			throw new IllegalArgumentException("Argument mat may not be null and must have at least one row. mat = " + Arrays.toString(mat));
		}
		
		boolean isRectangular = true;
		int row = 1;
		final int COLUMNS = mat[0].length;
		while (isRectangular && row < mat.length) {
			isRectangular = (mat[row].length == COLUMNS);
			row++;
		}
		return isRectangular;
	}
}