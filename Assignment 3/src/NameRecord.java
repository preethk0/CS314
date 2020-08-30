import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NameRecord implements Comparable<NameRecord> {
	private String name;
	private int baseDecade;
	private ArrayList<Integer> rank;
	private int numDecades;

	public NameRecord(String data, int baseDecade) throws FileNotFoundException {
		this.baseDecade = baseDecade;
		Scanner in = new Scanner(data);
		name = in.next();
		rank = new ArrayList<Integer>();

		while (in.hasNextInt()) {
			int rankValue = in.nextInt();
			if (rankValue == 0) {
				rank.add(1001);
			} else {
				rank.add(rankValue);
			}
		}

		numDecades = rank.size();
		in.close();
	}

	public int compareTo(NameRecord other) {
		return this.name.compareTo(other.name);
	}

	// a method to get the name for this NameRecord
	public String getName() {
		return name;
	}

	// a method that returns the base decade (decade of the first rank) for this NameRecord
	public int getBaseDecade() {
		return baseDecade;
	}

	// a method that returns the NameRecords rank for a given decade
	public int getRank(int decade) {
		return rank.get((decade - baseDecade) / 10);
	}

	// a method that returns an int for this NameRecord's best decade
	public int getBestDecade() {
		int bestRank = rank.get(numDecades - 1);
		int decade = numDecades - 1;

		for (int i = numDecades - 1; i >= 0; i--) {
			if (rank.get(i) < bestRank) {
				bestRank = rank.get(i);
				decade = i;
			}
		}
		return baseDecade + (decade * 10);
	}

	// a method that returns the number of decades this name has been ranked in the top 1000
	public int numTopDecades() {
		int count = 0;

		for (int i = 0; i < numDecades; i++) {
			if (rank.get(i) != 1001) {
				count++;
			}
		}
		return count;
	}

	// a method that returns true if this name has been ranked in the top 1000 in every decade
	public boolean topEveryDecade() {
		if (numTopDecades() == numDecades) {
			return true;
		}
		return false;
	}

	// a method that returns true if this name has been ranked in the top 1000 in only one decade
	public boolean topOneDecade() {
		if (numTopDecades() == 1) {
			return true;
		}
		return false;
	}

	// a method that returns true if this name has been getting more popular every decade in the time period covered
	public boolean morePopular() {
		for (int i = numDecades - 1; i > 0; i--) {
			if (rank.get(i) >= rank.get(i - 1)) {
				return false;
			}
		}
		return true;
	}

	// a method that returns true if this name has been getting less popular
	public boolean lessPopular() {
		for (int i = numDecades - 1; i > 0; i--) {
			if (rank.get(i) <= rank.get(i - 1)) {
				return false;
			}
		}
		return true;
	}

	// a method that returns the average of the ranks that are in the top 1000 for this NameRecord
	public double topRankAverage() {
		double sum = 0;
		double count = 0;
		for (int i = 0; i < numDecades; i++) {
			if (rank.get(i) != 1001) {
				sum = sum + rank.get(i);
				count++;
			}
		}
		return (sum / count);
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(name + "\n");
		int rankValue;

		for (int i = 0; i < rank.size(); i++) {
			if (rank.get(i) == 1001) {
				rankValue = 0;
			} else {
				rankValue = rank.get(i);
			}
			s.append(baseDecade + (i * 10) + ": " + rankValue + "\n");
		}

		return (s.toString());
	}
}