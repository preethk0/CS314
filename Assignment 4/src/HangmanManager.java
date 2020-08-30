/*  Student information for assignment:
 *
 *  On my honor, Preeth Kanamangala, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Preeth Kanamangala
 *  email address: preeth.kanamangala@gmail.com
 *  UTEID: PK9297
 *  Section 5 digit ID: 50240
 *  Grader name: Amir
 *  Number of slip days used on this assignment: 1
 */

// add imports as necessary

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.*;

/**
 * Manages the details of EvilHangman. This class keeps
 * tracks of the possible words from a dictionary during
 * rounds of hangman, based on guesses so far.
 *
 */
public class HangmanManager {

    TreeMap<String, ArrayList<String>> patternToWordsMap = new TreeMap<String, ArrayList<String>>();
	ArrayList<String> wordList = new ArrayList<String>();
	ArrayList<String> wordListByLength = new ArrayList<String>();
	ArrayList<String> guessesMade = new ArrayList<String>();
	int numGuesses;
	int wordLen;
	String previousPattern;
	String difficulty;

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     * @param debugOn true if we should print out debugging to System.out.
     */
    public HangmanManager(Set<String> words, boolean debugOn) {
		if (words == null || words.size() < 1) {
			throw new IllegalArgumentException("Pre-conditions not met.");
		}

		Iterator<String> it = words.iterator();
		for (int i = 0; i < words.size(); i++) {
			wordList.add(it.next());
		}
	}

    /**
     * Create a new HangmanManager from the provided set of words and phrases. 
     * Debugging is off.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     */
    public HangmanManager(Set<String> words) {
    	if(words == null || words.size() < 1) {
    		throw new IllegalArgumentException("Pre-conditions not met.");
    	}
    	
    	Iterator<String> it = words.iterator();
    	for(int i = 0; i < words.size(); i++) {
    		wordList.add(it.next());
    	}
    }

    /**
     * Get the number of words in this HangmanManager of the given length.
     * pre: none
     * @param length The given length to check.
     * @return the number of words in the original Dictionary with the given length
     */
    public int numWords(int length) {
        int count = 0;
    	for(int i = 0; i < wordList.size(); i++) {
        	if(wordList.get(i).length() == length) {
        		count++;
        	}
        }
    	
    	return count;
    }

    /**
     * Get for a new round of Hangman. Think of a round as a complete game of Hangman.
     * @param wordLen the length of the word to pick this time. numWords(wordLen) > 0
     * @param numGuesses the number of wrong guesses before the player loses the round. numGuesses >= 1
     * @param diff The difficulty for this round.
     */
    public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff) {
    	wordListByLength.clear();
    	patternToWordsMap.clear();
    	guessesMade.clear();
    	this.numGuesses = numGuesses;
    	this.wordLen = wordLen;
    	difficulty = diff.name();
    	
    	for(int i = 0; i < wordList.size(); i++) {
    		if(wordList.get(i).length() == wordLen) {
    			wordListByLength.add(wordList.get(i));
    		}
    	}

    	patternToWordsMap.put(getPattern(), wordListByLength);
    }

    /**
     * The number of words still possible (live) based on the guesses so far. Guesses will eliminate possible words.
     * @return the number of words that are still possibilities based on the original dictionary and the guesses so far.
     */
    public int numWordsCurrent() {
        return patternToWordsMap.get(getPattern()).size();
    }


    /**
     * Get the number of wrong guesses the user has left in this round (game) of Hangman.
     * @return the number of wrong guesses the user has left in this round (game) of Hangman.
     */
    public int getGuessesLeft() {
        return numGuesses - guessesMade.size();
    }

    /**
     * Return a String that contains the letters the user has guessed so far during this round.
     * The String is in alphabetical order. The String is in the form [let1, let2, let3, ... letN].
     * For example [a, c, e, s, t, z]
     * @return a String that contains the letters the user has guessed so far during this round.
     */
    public String getGuessesMade() {
        return guessesMade.toString();
    }

    /**
     * Check the status of a character.
     * @param guess The characater to check.
     * @return true if guess has been used or guessed this round of Hangman, false otherwise.
     */
    public boolean alreadyGuessed(char guess) {
    	String guessToString = Character.toString(guess);
    	if(getGuessesMade().contains(guessToString)) {
    		return true;
    	}
    	return false;
    }

    /**
     * Get the current pattern. The pattern contains '-''s for unrevealed (or guessed)
     * characters and the actual character for "correctly guessed" characters.
     * @return the current pattern.
     */
    public String getPattern() {    
    	if(patternToWordsMap.keySet().isEmpty() == true) {
        	StringBuilder sb = new StringBuilder();
        	for(int i = 0; i < wordLen; i++) {
        		sb.append("-");
        	}
        	previousPattern = sb.toString();
        	return previousPattern;
        }
        else {
        	int max = 0;
        	int secondMax = 0;
        	String maxPattern = previousPattern;
        	String secondMaxPattern = null;
        	for(String patt : patternToWordsMap.keySet()) {
        		if(patternToWordsMap.get(patt).size() > max) {
            		if(max != 0) {
                		secondMax = max;
                		secondMaxPattern = maxPattern;
            		}
            		max = patternToWordsMap.get(patt).size();
            		maxPattern = patt;
            	}
            	else if(patternToWordsMap.get(patt).size() == max) {
            		int count = 0;
            		int countMax = 0;
            		char[] pattArray = patt.toCharArray();
            		char[] maxArray = maxPattern.toCharArray();
            		for(int i = 0; i < wordLen; i++) {
            			if(pattArray[i] == '-') {
            				count++;
            			}
            			if(maxArray[i] == '-') {
            				countMax++;
            			}
            		}
            		if(count > countMax) {
                		secondMax = max;
                		secondMaxPattern = maxPattern;
                		max = patternToWordsMap.get(patt).size();
                		maxPattern = patt;
            		}
					else if (count == countMax) {
						if (patt.compareTo(maxPattern) < 0) {
							secondMax = max;
							secondMaxPattern = maxPattern;
							max = patternToWordsMap.get(patt).size();
							maxPattern = patt;
						} else {
							secondMax = patternToWordsMap.get(patt).size();
							secondMaxPattern = patt;
						}
					} else {
						secondMax = patternToWordsMap.get(patt).size();
						secondMaxPattern = patt;
					}
				} else if (patternToWordsMap.get(patt).size() > secondMax) {
					secondMax = patternToWordsMap.get(patt).size();
					secondMaxPattern = patt;
				}
			}
        	previousPattern = maxPattern;

        	boolean easy = difficulty.equals("EASY") && guessesMade.size() % 2 == 0;
        	boolean medium = difficulty.equals("MEDIUM") && guessesMade.size() % 4 == 0;
        	if(secondMaxPattern != null && guessesMade.size() != 0 && (easy || medium)) {
        		maxPattern = secondMaxPattern;
        	}
        	
            return maxPattern;
        }
    }
    
    // pre: !alreadyGuessed(ch)
    // post: return a tree map with the resulting patterns and the number of
    // words in each of the new patterns.
    // the return value is for testing and debugging purposes
    /**
     * Update the game status (pattern, wrong guesses, word list), based on the give
     * guess.
     * @param guess pre: !alreadyGuessed(ch), the current guessed character
     * @return return a tree map with the resulting patterns and the number of
     * words in each of the new patterns.
     * The return value is for testing and debugging purposes.
     */
    public TreeMap<String, Integer> makeGuess(char guess) {
    	resetActiveWords();
    	ArrayList<String> keyList = new ArrayList<String>(patternToWordsMap.keySet());
    	Iterator<String> it = keyList.iterator();
    	if(!alreadyGuessed(guess)) {
    		guessesMade.add(Character.toString(guess));
        	while(it.hasNext()) {
        		String patt = it.next();
               		int size = patternToWordsMap.get(patt).size();
            		for(int i = 0; i < size; i++) {
            			String curr = patternToWordsMap.get(patt).get(i);
                		StringBuilder sb = new StringBuilder(patt);
            			if(curr.contains(Character.toString(guess))) {
            				char[] currToChar = curr.toCharArray();
                			for(int index = 0; index < wordLen; index++) {
                    			if(currToChar[index] == guess) {
                    				sb.replace(index, index+1, Character.toString(guess));
                				}
                			}
                			if(patternToWordsMap.keySet().contains(sb.toString())) {
                				patternToWordsMap.get(sb.toString()).add(curr);
                			}
                			else {
                				patternToWordsMap.put(sb.toString(), new ArrayList<String>());
                				patternToWordsMap.get(sb.toString()).add(curr);
                			}
                			patternToWordsMap.get(patt).remove(i);
                			i--;
                			size--;
            			}
            		}
        	}
		}
        return returnMap();
    }

    // this method removes all patterns in the map other than the current pattern in preparation for new patterns based on next user guess
    public void resetActiveWords() {
    	String currPatt = getPattern();
    	ArrayList<String> keyList = new ArrayList<String>(patternToWordsMap.keySet());
    	Iterator<String> it = keyList.iterator();
    	while(it.hasNext()) {
    		String patt = it.next();
    		if(!patt.equals(currPatt)) {
    			patternToWordsMap.remove(patt);
    		}
    	}
    }
    
    // this method simply converts the wordMap into a map that has the number of words in a given pattern mapped to each string pattern
    public TreeMap<String, Integer> returnMap() {
    	TreeMap<String, Integer> returnMap = new TreeMap<String, Integer>();
    	for(String pattern : patternToWordsMap.keySet()) {
    		if(patternToWordsMap.get(pattern).size() != 0) {
    			returnMap.put(pattern, patternToWordsMap.get(pattern).size());
    		}
    	}
    	return returnMap;
    }

    /**
     * Return the secret word this HangmanManager finally ended up picking for this round.
     * If there are multiple possible words left one is selected at random.
     * <br> pre: numWordsCurrent() > 0
     * @return return the secret word the manager picked.
     */
    public String getSecretWord() {
    	Random r = new Random();
    	int index = r.nextInt(patternToWordsMap.get(getPattern()).size());
        return (patternToWordsMap.get(getPattern())).get(index);
    }
}