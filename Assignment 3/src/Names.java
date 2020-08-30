/*  Student information for assignment:
*
*  On my honor, <NAME>, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: PK9297
*  email address: preeth.kanamangala@gmail.com
*  Number of slip days I am using:
*/

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
* A collection of NameRecords. 
* Stores NameRecord objects and provides methods to select
* NameRecords based on various criteria.
*/
public class Names {
    
	private ArrayList<NameRecord> names;
    /**
     * Construct a new Names object based on the data source the Scanner 
     * sc is connected to. Assume the first two lines in the 
     * data source are the base year and number of decades to use.
     * Any lines without the correct number of decades are discarded 
     * and are not part of the resulting Names object. 
     * Any names with ranks of all 0 are discarded and not
     * part of the resulting Names object.
     * @param sc Is connected to a data file with baby names 
     * and positioned at the start of the data source.
     * @throws FileNotFoundException 
     */
	public Names(Scanner sc) throws FileNotFoundException {
		int baseDecade = sc.nextInt();
		int numDecades = sc.nextInt();
		sc.nextLine();
		names = new ArrayList<NameRecord>();

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			Scanner parseLine = new Scanner(line);
			parseLine.next();

			int count = 0;
			int countZero = 0; // this counts specifically how many 0 rank values there are

			while (parseLine.hasNextInt()) {
				count++;
				if (parseLine.nextInt() == 0) {
					countZero++;
				}
			}

			if (count == numDecades && countZero != numDecades) {
				NameRecord name = new NameRecord(line, baseDecade);
				names.add(name);
			}
			parseLine.close();
		}
	}

   /**
    * Returns an ArrayList of NameRecord objects that contain a 
    * given substring, ignoring case.  The names must be in sorted order based 
    * on name.
    * @param partialName != null, partialName.length() > 0
    * @return an ArrayList of NameRecords whose names contains
    * partialName. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
	public ArrayList<NameRecord> getMatches(String partialName) {
		if (partialName == null || partialName.length() <= 0) {
			throw new IllegalArgumentException("Pre-conditions are not met.");
		}

		ArrayList<NameRecord> matchList = new ArrayList<NameRecord>();
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).getName().toLowerCase().contains(partialName.toLowerCase())) {
				matchList.add(names.get(i));
			}
		}
		Collections.sort(matchList);
		return matchList;
	}

   /**
    * Returns an ArrayList of Strings of names that have been ranked in the
    * top 1000 or better for every decade. The Strings  must be in sorted 
    * order based on name. 
    * @return A list of the names that have been ranked in the top
    * 1000 or better in every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
   
	public ArrayList<String> rankedEveryDecade() {
		ArrayList<String> rankedEveryDecadeList = new ArrayList<String>();
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).topEveryDecade()) {
				rankedEveryDecadeList.add(names.get(i).getName());
			}
		}
		Collections.sort(rankedEveryDecadeList);
		return rankedEveryDecadeList;
	}

   /**
    * Returns an ArrayList of Strings of names that have been ranked in the 
    * top 1000 or better in exactly one decade. The Strings must be in sorted 
    * order based on name. 
    * @return A list of the names that have been ranked in the top
    * 1000 or better in exactly one decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
	public ArrayList<String> rankedOnlyOneDecade() {
		ArrayList<String> rankedOneDecadeList = new ArrayList<String>();
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).topOneDecade()) {
				rankedOneDecadeList.add(names.get(i).getName());
			}
		}
		Collections.sort(rankedOneDecadeList);
		return rankedOneDecadeList;
	}

   /**
    * Returns an ArrayList of Strings of names that have been getting more
    * popular every decade. The Strings  must be in sorted order based on name.
    * @return A list of the names that have been getting more popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
	public ArrayList<String> alwaysMorePopular() {
		ArrayList<String> alwaysMorePopularList = new ArrayList<String>();
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).morePopular()) {
				alwaysMorePopularList.add(names.get(i).getName());
			}
		}
		Collections.sort(alwaysMorePopularList);
		return alwaysMorePopularList;
	}

   /**
    * Returns an ArrayList of Strings of names that have been getting less
    * popular every decade. The Strings  must be in sorted order based on name.
    * @return A list of the names that have been getting less popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
	public ArrayList<String> alwaysLessPopular() {
		ArrayList<String> alwaysLessPopularList = new ArrayList<String>();
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).lessPopular()) {
				alwaysLessPopularList.add(names.get(i).getName());
			}
		}
		Collections.sort(alwaysLessPopularList);
		return alwaysLessPopularList;
	}
   
	/**
	* Returns a String that shows the average rank of a user inputed name length.
	* @return A String that shows the average rank of a user inputed name length.
	* The full database is searched for all names that have the same length as the user inputed name length,
	* and their topRankAverage() methods are averaged.
	* The max length of a name is first determined, and the user inputed name
	* length is rejected if it is greater than this or less than one. 
	*/	
	public String averageRankByLength(int userLength) {
		int maxLength = names.get(0).getName().length();
		for (int i = 0; i < names.size(); i++) {	// compares each name length to the max name length to determine true max name length
			if (names.get(i).getName().length() > maxLength) {
				maxLength = names.get(i).getName().length();
			}
		}
		String averageRankList;
		if (userLength > maxLength || userLength < 1) {
			averageRankList = "The data does not contain a name of length " + userLength + ".";
		} else {
			double sum = 0;
			double count = 0;
			for (int j = 0; j < names.size(); j++) {	// if the name of a NameRecord equals the userLength, then include the topRankAverage() in the average
				if (names.get(j).getName().length() == userLength) {
					sum = sum + names.get(j).topRankAverage();
					count++;
				}
			}
			averageRankList = "Name length of [" + userLength + "] has an average rank of " + sum / count + ".";
		}
		return averageRankList;
	}
   
   /**
    * Return the NameRecord in this Names object that matches the given String.
    * <br>
    * <tt>pre: name != null</tt>
    * @param name The name to search for.
    * @return The name record with the given name or null if no NameRecord in this Names
    * object contains the given name.
    */
	public NameRecord getName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("The parameter name cannot be null");
		}

		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).getName().equalsIgnoreCase(name)) {
				return names.get(i);
			}
		}
		return null;
	}
}