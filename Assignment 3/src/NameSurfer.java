/*
 * Student information for assignment: Replace <NAME> in the following with your
 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone. 
 * 
 * On my honor, Preeth Kanamangala, this programming assignment is my own work 
 * and I have not provided this code
 * to any other student. 
 * UTEID: PK9297
 * email address: preeth.kanamangala@gmail.com
 * Number of slip days I am using:
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NameSurfer {

    public static final String NAME_FILE = "names.txt";

    // constants for menu choices
    public static final int SEARCH = 1;
    public static final int ONE_NAME = 2;
    public static final int APPEAR_ONCE = 3;
    public static final int APPEAR_ALWAYS = 4;
    public static final int MORE_POPULAR = 5;
    public static final int LESS_POPULAR = 6;
    public static final int RANK_BY_LENGTH = 7;
    public static final int QUIT = 8;

    // CS314 students, explain your menu option 7 here:
    // My menu 7 option takes the average rank (only counting those in the top 1000) of a name and averages
    // it with those of a same length. The user is then able to input a name length and the average rank of all
    // names in the data with that particular length is displayed. To do this, a topRankAverage method was created
    // in the NameRecord class. It simply averages all ranks (that are in the top 1000) for a given NameRecord.
    // Then, an averageRankByLength method was created in the Names class to determine the max name length and to
    // average the topRankAverages of NameRecords that had a name of the same length as the user input (which had to be less than max name length).
    // The user is then able to see how name length is, on average, correlated with popularity.

    // CS314 students, Explain your interesting search / trend here:
    // My interesting trend is that there are a total of 5 variations of "tim" (tim, timmie, timmothy, timothy, and timmy, 
    // observed by using menu option 1) and each of these variations have their most popular decade as 1960 (weird, right?).
    // Not to mention, when observing these variations using menu option 2, we see that only "timothy" has survived in the
    // top 1000 by year 2000 while the others have gone outside the top 1000. This is particularly interesting, as I have two close friends
    // who are named "timothy" but BOTH go by "tim" (one of the variations that have faded outside the top 1000). This brings
    // up the consideration that although "timothy" is the only variation that survived in the top 1000 by 2000, it may not be the only
    // real case considering that many people named "timothy" PREFERRED to be called "tim".

	public static void simpleTest() throws FileNotFoundException {
    	// my test code for NameRecord class
    	// toString() test
		String mattRawData = "Matt 456 534 788 1000 729 414 292 372 651 0 0";
		int baseDecade = 1900;
		NameRecord mattRecord = new NameRecord(mattRawData, baseDecade);
		String expected = "Matt\n1900: 456\n1910: 534\n1920: 788\n1930: 1000\n1940: 729\n1950: 414\n1960: 292\n1970: 372\n1980: 651\n1990: 0\n2000: 0\n";
		String actual = mattRecord.toString();
		System.out.println("expected string:\n" + expected);
		System.out.println("actual string:\n" + actual);
		if (expected.equals(actual)) {
			System.out.println("passed Matt toString test.");
		} else {
			System.out.println("FAILED Matt toString test.");
		}
		
		// getName() test
		expected = "Matt";
		actual = mattRecord.getName();
		System.out.println("expected string:\n" + expected);
		System.out.println("actual string:\n" + actual);
		if (expected.equals(actual)) {
			System.out.println("passed Matt getName test.");
		} else {
			System.out.println("FAILED Matt getName test.");
		}
		
		// getBaseDecade() test
		int expectedNum = 1900;
		int actualNum = mattRecord.getBaseDecade();
		System.out.println("expected int:\n" + expectedNum);
		System.out.println("actual int:\n" + actualNum);
		if (expectedNum == actualNum) {
			System.out.println("passed Matt getBaseDecade test.");
		} else {
			System.out.println("FAILED Matt getBaseDecade test.");
		}
		
		// getRank(int decade) test
		expectedNum = 1000;
		actualNum = mattRecord.getRank(1930);
		System.out.println("expected int:\n" + expectedNum);
		System.out.println("actual int:\n" + actualNum);
		if (expectedNum == actualNum) {
			System.out.println("passed Matt getRank test.");
		} else {
			System.out.println("FAILED Matt getRank test.");
		}
		// getBestDecade() test
		expectedNum = 1960;
		actualNum = mattRecord.getBestDecade();
		System.out.println("expected int:\n" + expectedNum);
		System.out.println("actual int:\n" + actualNum);
		if (expectedNum == actualNum) {
			System.out.println("passed Matt getBestDecade test.");
		} else {
			System.out.println("FAILED Matt getBestDecade test.");
		}
		
		// numTopDecades() test
		expectedNum = 9;
		actualNum = mattRecord.numTopDecades();
		System.out.println("expected int:\n" + expectedNum);
		System.out.println("actual int:\n" + actualNum);
		if (expectedNum == actualNum) {
			System.out.println("passed Matt numTopDecades test.");
		} else {
			System.out.println("FAILED Matt numTopDecades test.");
		}
		
		// topEveryDay() test
		boolean expectedBool = false;
		boolean actualBool = mattRecord.topEveryDecade();
		System.out.println("expected int:\n" + expectedBool);
		System.out.println("actual int:\n" + actualBool);
		if (expectedBool == actualBool) {
			System.out.println("passed Matt topEveryDecade test.");
		} else {
			System.out.println("FAILED Matt topEveryDecade test.");
		}
		
		// topOneDecade() test
		expectedBool = false;
		actualBool = mattRecord.topOneDecade();
		System.out.println("expected int:\n" + expectedBool);
		System.out.println("actual int:\n" + actualBool);
		if (expectedBool == actualBool) {
			System.out.println("passed Matt topOneDecade test.");
		} else {
			System.out.println("FAILED Matt topOneDecade test.");
		}
		
		// morePopular() test
		expectedBool = false;
		actualBool = mattRecord.morePopular();
		System.out.println("expected int:\n" + expectedBool);
		System.out.println("actual int:\n" + actualBool);
		if (expectedBool == actualBool) {
			System.out.println("passed Matt morePopular test.");
		} else {
			System.out.println("FAILED Matt morePopular test.");
		}
		
		// lessPopular() test
		expectedBool = false;
		actualBool = mattRecord.lessPopular();
		System.out.println("expected int:\n" + expectedBool);
		System.out.println("actual int:\n" + actualBool);
		if (expectedBool == actualBool) {
			System.out.println("passed Matt lessPopular test.");
		} else {
			System.out.println("FAILED Matt lessPopular test.");
		}
        
		// some getName() Tests

		Names names = new Names(getFileScannerForNames(NAME_FILE));
		String[] testNames = { "Isabelle", "isabelle", "sel", "X1178", "ZZ", "via", "kelly" };
		boolean[] expectNull = { false, false, true, true, true, true, false };
		for (int i = 0; i < testNames.length; i++) {
			performGetNameTest(names, testNames[i], expectNull[i]);
		}
    }

	private static void performGetNameTest(Names names, String name, boolean expectNull) {
		System.out.println("Performing test for this name: " + name);
		if (expectNull) {
			System.out.println("Expected return value is null");
		} else {
			System.out.println("Expected return value is not null");
		}
		NameRecord result = names.getName(name);
		if ((expectNull && result == null) || (!expectNull && result != null)) {
			System.out.println("PASSED TEST.");
		} else {
			System.out.println("Failed test");
		}
	}

	// main method. Driver for the whole program
	public static void main(String[] args) throws FileNotFoundException {
		simpleTest();
		Scanner fileScanner = getFileScannerForNames(NAME_FILE);
		Names namesDatabase = new Names(fileScanner);
		fileScanner.close();
		runOptions(namesDatabase);
	}

    // pre: namesDatabase != null
    // ask user for options to perform on the given Names object.
    // Creates a Scanner connected to System.in.
	private static void runOptions(Names namesDatabase) {
		if (namesDatabase == null) {
			throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
		}
		
		Scanner keyboard = new Scanner(System.in);
		int choice;
		
		do {
			showMenu();
			choice = getChoice(keyboard);
			if (choice == SEARCH) {
				search(namesDatabase, keyboard);
			} else if (choice == ONE_NAME) {
				oneName(namesDatabase, keyboard);
			} else if (choice == APPEAR_ONCE) {
				appearOnce(namesDatabase);
			} else if (choice == APPEAR_ALWAYS) {
				appearAlways(namesDatabase);
			} else if (choice == MORE_POPULAR) {
				morePopular(namesDatabase);
			} else if (choice == LESS_POPULAR) {
				lessPopular(namesDatabase);
			} else if (choice == RANK_BY_LENGTH) {
				rankByLength(namesDatabase, keyboard);
			} else {
				System.out.println("\n\nGoodbye.");
			}
		} while (choice != QUIT);
		keyboard.close();
	}

    // pre: fileName != null
    // create a Scanner and return connected to a File with the given name.
	private static Scanner getFileScannerForNames(String fileName) {
		if (fileName == null) {
			throw new IllegalArgumentException("The parameter fileName cannot be null");
		}
		
		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Problem reading the data file. Returning null for Scanner object. Problems likely to occur." + e);
		}
		return sc;
	}

    // method that shows average rank of a user specified length of name
    // pre: n != null, keyboard != null and is connected to System.in
    // post: print out the average rank of a user specified length of name
	private static void rankByLength(Names n, Scanner keyboard) {
		if (n == null || keyboard == null) {
			throw new IllegalArgumentException("The parameters cannot be null");
		}
		
		System.out.print("Enter the name length you would like to view the average for: ");
		int userLength = keyboard.nextInt();
		
		System.out.println("\n" + n.averageRankByLength(userLength));
	}
    
    // method that shows names that have been getting less popular every decade
    // pre: n != null
    // post: print out names that have been getting less popular every decade
	private static void lessPopular(Names n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter n cannot be null");
		}

		int size = n.alwaysLessPopular().size();
		System.out.println(size + " names are less popular in every decade.");
		for (int i = 0; i < size; i++) {
			System.out.println(n.alwaysLessPopular().get(i));
		}
	}
    
    // method that shows names that have been getting more popular every decade
    // pre: n != null
    // post: print out names that have been getting more popular every decade
	private static void morePopular(Names n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter n cannot be null");
		}

		int size = n.alwaysMorePopular().size();
		System.out.println(size + " names are more popular in every decade.");
		for (int i = 0; i < size; i++) {
			System.out.println(n.alwaysMorePopular().get(i));
		}
	}
    
    // method that shows names that have appeared in ever decade
    // pre: n != null
    // post: print out names that have appeared in ever decade
	private static void appearAlways(Names n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter n cannot be null");
		}

		int size = n.rankedEveryDecade().size();
		System.out.println(size + " names appear in every decade. The names are: ");
		for (int i = 0; i < size; i++) {
			System.out.println(n.rankedEveryDecade().get(i));
		}
	}

	// method that shows names that have appeared in only one decade
	// pre: n != null
	// post: print out names that have appeared in only one decade
	private static void appearOnce(Names n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter n cannot be null");
		}

		int size = n.rankedOnlyOneDecade().size();
		System.out.println(size + " names appear in exactly one decade. The names are: ");
		for (int i = 0; i < size; i++) {
			System.out.println(n.rankedOnlyOneDecade().get(i));
		}
	}

    // method that shows data for one name, or states that name has never been
    // ranked
    // pre: n != null, keyboard != null and is connected to System.in
    // post: print out the data for n or a message that n has never been in the
    // top 1000 for any decade
    private static void oneName(Names n, Scanner keyboard) {
		if (n == null || keyboard == null) {
			throw new IllegalArgumentException("The parameters cannot be null");
		}

		System.out.print("Enter a name: ");
		String input = keyboard.next();

		if (n.getName(input) == null) {
			System.out.println("\n" + input + " does not appear in any decade.");
		} else {
			System.out.println("\n" + n.getName(input).toString());
		}
	}

    // method that shows all names that contain a substring from the user
    // and the decade they were most popular in
    // pre: n != null, keyboard != null and is connected to System.in
    // post: show the correct data
	private static void search(Names n, Scanner keyboard) {
		if (n == null || keyboard == null) {
			throw new IllegalArgumentException("The parameters cannot be null");
		}

		System.out.print("Enter a partial name: ");
		String input = keyboard.next();

		int size = n.getMatches(input).size();
		System.out.println("\nThere are " + size + " matches for " + input + ".\n");
		if (size > 0) {
			System.out.println("The matches with their highest ranking decade are: ");
			for (int i = 0; i < size; i++) {
				System.out.println(n.getMatches(input).get(i).getName() + " " + n.getMatches(input).get(i).getBestDecade());
			}
		}
	}

    // get choice from the user
    // keyboard != null and is connected to System.in
    // return an int that is >= SEARCH and <= QUIT
	private static int getChoice(Scanner keyboard) {
		if (keyboard == null) {
			throw new IllegalArgumentException("The parameter keyboard cannot be null");
		}
		int choice = getInt(keyboard, "Enter choice: ");
		keyboard.nextLine();
		while (choice < SEARCH || choice > QUIT) {
			System.out.println("\n" + choice + " is not a valid choice");
			choice = getInt(keyboard, "Enter choice: ");
			keyboard.nextLine();
		}
		return choice;
	}

    // ensure an int is entered from the keyboard
    // pre: s != null and is connected to System.in
	private static int getInt(Scanner s, String prompt) {
		if (s == null) {
			throw new IllegalArgumentException("The parameter s cannot be null");
		}
		
		System.out.print(prompt);
		while (!s.hasNextInt()) {
			s.next();
			System.out.println("That was not an int.");
			System.out.print(prompt);
		}
		return s.nextInt();
	}

    // show the user the menu
	private static void showMenu() {
		System.out.println("\nOptions:");
		System.out.println("Enter " + SEARCH + " to search for names.");
		System.out.println("Enter " + ONE_NAME + " to display data for one name.");
		System.out.println("Enter " + APPEAR_ONCE + " to display all names that appear in only one decade.");
		System.out.println("Enter " + APPEAR_ALWAYS + " to display all names that appear in all decades.");
		System.out.println("Enter " + MORE_POPULAR + " to display all names that are more popular in every decade.");
		System.out.println("Enter " + LESS_POPULAR + " to display all names that are more popular in every decade.");
		System.out.println("Enter " + RANK_BY_LENGTH + " to display the average rank of each name length.");
		System.out.println("Enter " + QUIT + " to quit.\n");
	}
}