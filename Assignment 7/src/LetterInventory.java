/* CS 314 STUDENTS: FILL IN THIS HEADER AND THEN COPY AND PASTE IT TO YOUR
 * LetterInventory.java AND AnagramSolver.java CLASSES.
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

public class LetterInventory {
	int size;
	int[] letterInventory;
	static final int ALPHABETLENGTH = 26;
	static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz"; // this string is used to find the index of a letter in the letterInventory

	public LetterInventory(String phrase) {
		if (phrase == null) {
			throw new IllegalArgumentException("Pre-conditions are not satisfied.");
		}

		letterInventory = new int[ALPHABETLENGTH];
		phrase = phrase.toLowerCase();
		for (int index = 0; index < phrase.length(); index++) { // iterates through each char in the phrase
			char currChar = phrase.charAt(index);
			if (currChar >= 'a' && currChar <= 'z') {
				letterInventory[ALPHABET.indexOf(currChar)]++; // increment the frequency of the given char in the letterInventory
				size++;
			}
		}
	}
	
	public LetterInventory() { // default constructor
		letterInventory = new int[ALPHABETLENGTH];
	}
	
	public int get(char currChar) {
		currChar = Character.toLowerCase(currChar); // we must first convert the character to lower case
		if (currChar < 'a' || currChar > 'z') {
			throw new IllegalArgumentException("Pre-conditions are not satisfied.");
		}

		return letterInventory[ALPHABET.indexOf(currChar)];
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	public String toString() {
		String completeString = "";
		for (int letterIndex = 0; letterIndex < ALPHABETLENGTH; letterIndex++) { // goes through all letters
			for (int freqIndex = 0; freqIndex < letterInventory[letterIndex]; freqIndex++) { // prints each letter depending on its frequency in letterInventory
				completeString += ALPHABET.charAt(letterIndex);
			}
		}
		return completeString;
	}
	
	public LetterInventory add(LetterInventory other) {
		if (other == null) {
			throw new IllegalArgumentException("Pre-conditions are not satisfied.");
		}

		LetterInventory result = new LetterInventory();
		for (int letterIndex = 0; letterIndex < ALPHABETLENGTH; letterIndex++) {
			result.letterInventory[letterIndex] = this.letterInventory[letterIndex] + other.letterInventory[letterIndex]; // adds each index of letterInventory together
			result.size += result.letterInventory[letterIndex]; // adjusts size
		}

		return result;
	}
	
	public LetterInventory subtract(LetterInventory other) {
		if (other == null) {
			throw new IllegalArgumentException("Pre-conditions are not satisfied.");
		}

		LetterInventory result = new LetterInventory();
		for (int letterIndex = 0; letterIndex < ALPHABETLENGTH; letterIndex++) {
			if (this.letterInventory[letterIndex] - other.letterInventory[letterIndex] < 0) { // immediately return null when the resulting letter count is less than 0
				return null;
			}
			result.letterInventory[letterIndex] = this.letterInventory[letterIndex] - other.letterInventory[letterIndex]; // subtracts each index of letterInventory
			result.size += result.letterInventory[letterIndex]; // adjusts size
		}

		return result;
	}
	
	public boolean equals(Object other) {
		if (other == null) {
			throw new IllegalArgumentException("Pre-conditions are not satisfied.");
		}

		if (!(other instanceof LetterInventory)) { // immediately return false if the object isn't of type LetterInventory
			return false;
		}
		LetterInventory otherInventory = (LetterInventory) other; // cast it
		
		for (int letterIndex = 0; letterIndex < ALPHABETLENGTH; letterIndex++) {
			if (this.letterInventory[letterIndex] != otherInventory.letterInventory[letterIndex]) { // the moment a letter frequency doesn't match
				return false;
			}
		}
		return true;
	}
}