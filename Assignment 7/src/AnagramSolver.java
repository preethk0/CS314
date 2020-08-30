/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, Preeth Kanamangala, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: PK9297
 *  email address: preeth.kanamangala@gmail.com
 *  Grader name: Amir
 *  Number of slip days I am using: 1
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AnagramSolver {

	HashMap<String, LetterInventory> dictionaryMap; // the list of all words in the dictionary mapped to each letterInventory of the word

    /*
     * pre: list != null
     * @param list Contains the words to form anagrams from.
     */
	public AnagramSolver(Set<String> dictionary) {
		if (dictionary == null) {
			throw new IllegalArgumentException("Pre-conditions are not satisfied.");
		}

		dictionaryMap = new HashMap<String, LetterInventory>();
		for (String word : dictionary) { // for each word in the dictionary
			dictionaryMap.put(word, new LetterInventory(word)); // map its letterInventory to it
		}
	}

    /*
     * pre: maxWords >= 0, s != null, s contains at least one 
     * English letter.
     * Return a list of anagrams that can be formed from s with
     * no more than maxWords, unless maxWords is 0 in which case
     * there is no limit on the number of words in the anagram
     */
	public List<List<String>> getAnagrams(String s, int maxWords) {
		if (maxWords < 0 || s == null || !containsLetter(s)) {
			throw new IllegalArgumentException("Pre-conditions are not satisfied.");
		}

		LetterInventory currPhrase = new LetterInventory(s); // currPhrase is the letterInventory of the phrase we want to make anagrams from
		HashMap<String, LetterInventory> validDictionary = new HashMap<String, LetterInventory>();
		for (String key : dictionaryMap.keySet()) { // for each word in the dictionary
			if (currPhrase.subtract(dictionaryMap.get(key)) != null) { // if the word is able to be in an anagram of the current phrase
				validDictionary.put(key, dictionaryMap.get(key)); // add it into the map of VALID dictionary words
			}
		}

		List<List<String>> totalAnagrams = new ArrayList<List<String>>(); // this list of lists stores all anagrams formed
		List<String> validWords = new ArrayList<String>(validDictionary.keySet()); // this list stores all keys (words) from the map of VALID dictionary words
		int maxLengthWord = getMax(validWords); // the max length word in our list of valid words
		anagramHelper(maxLengthWord, validWords, currPhrase, maxWords, new ArrayList<String>(), totalAnagrams, 0);
		Collections.sort(totalAnagrams, new AnagramComparator()); // we sort our total list of anagrams before returning
		return totalAnagrams;
	}

	// this helper method returns the max length of a word in a list
	private int getMax(List<String> words) {
		int max = Integer.MIN_VALUE;
		for (String key : words) {
			max = Math.max(max, dictionaryMap.get(key).size());
		}
		return max;
	}
    
	// this helper method is what we actually call recursively
	private void anagramHelper(int maxLengthWord, List<String> validWords, LetterInventory currPhrase, int maxWords, ArrayList<String> currAnagrams, List<List<String>> totalAnagrams, int anagramSize) {
		if (anagramSize == currPhrase.size()) { // BASE CASE if the number of letters in our current anagram equals the number in the phrase
			List<String> temp = new ArrayList<String>(currAnagrams); // copy it over
			Collections.sort(temp); // sort without our own comparator
			totalAnagrams.add(temp); // add this anagram to our total list
		} else if (anagramSize < currPhrase.size()) { // only if the number of letters in our current anagram is LESS than the number in the phrase
			for (int index = 0; index < validWords.size(); index++) { // we iterate through the words in our VALID dictionary
				String word = validWords.get(index);
				currAnagrams.add(word); // add a word to our current anagram
				anagramSize += word.length();
				boolean hasPossibleWords = anagramSize + maxLengthWord * (maxWords - currAnagrams.size()) >= currPhrase.size(); // checks to see if it is POSSIBLE to reach the size of the current phrase with the words we have
				if (maxWords == 0 || currAnagrams.size() <= maxWords && hasPossibleWords) {
					List<String> tempValidWords = trimKeySet(validWords, currPhrase, currAnagrams, index); // we have to trim our valid word list first
					int newMaxLengthWord = getMax(tempValidWords); // we get our new max from this trimmed list
					anagramHelper(newMaxLengthWord, tempValidWords, currPhrase, maxWords, currAnagrams, totalAnagrams, anagramSize); // recursive step
				}
				anagramSize -= word.length();
				currAnagrams.remove(currAnagrams.size() - 1); // we remove a word from our current anagram list to backtrack and try other possibilities
			}
		}
	}
	
	// this comparator class sorts our total list of anagrams by size then lexicographically
	private class AnagramComparator implements Comparator<List<String>> {
		public int compare(List<String> a1, List<String> a2) {
			if (a1.size() != a2.size()) { // if their sizes aren't the same then we sort by size
				return a1.size() - a2.size();
			} else { // if their sizes are the same then we sort lexicographically
				for (int i = 0; i < a1.size(); i++) {
					if (a1.get(i).compareTo(a2.get(i)) != 0) {
						return a1.get(i).compareTo(a2.get(i));
					}
				}
				return 0;
			}
		}
	}
	
	// this helper method trims our list of valid words from the dictionary based on what words we've already tried
	private List<String> trimKeySet(List<String> validWords, LetterInventory currPhrase, ArrayList<String> currAnagrams, int index) {
		List<String> tempValidWords = new ArrayList<String>();
		for (int i = index; i < validWords.size(); i++) {
			currAnagrams.add(validWords.get(i)); // we add a word
			if (possibleAnagram(currAnagrams, currPhrase)) { // add it to our new valid word list if it can be a possible anagram
				tempValidWords.add(validWords.get(i));
			}
			currAnagrams.remove(currAnagrams.size() - 1); // then remove to try more words
		}

		return tempValidWords;
	}

	// this helper method checks if a provided list of words can be a possible anagram
	private boolean possibleAnagram(ArrayList<String> currList, LetterInventory currPhrase) {
		LetterInventory result = new LetterInventory(currList.toString());
		return currPhrase.subtract(result) != null; // if the letters in each letter inventory don't line up then its not possible
	}

	// this helper method checks if a given string contains at least one letter
	private boolean containsLetter(String s) {
		s = s.toLowerCase();
		for (int index = 0; index < s.length(); index++) {
			if (s.charAt(index) >= 'a' && s.charAt(index) <= 'z') {
				return true;
			}
		}
		return false;
	}
}