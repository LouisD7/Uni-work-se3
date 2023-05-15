package analyser;

/**
 * Stores result information related to the analysis of text.
 * 
 * @author mdixon
 */
public class AnalysisResult {

	// attributes for the class that hold the int and strings
	private int totalChars, wordCount, resetCount;
	private String longestWord = "", shortestWord = "", lastWord = "";
	
	/**
	 * Records a word, using the information given to calculate analysis results.
	 * 
	 * Any whitespace is trimmed from either side of the word prior to it being
	 * recorded.
	 * 
	 * @param word the word to be recorded (null or empty words are ignored).
	 * 
	 * The two if statements check to see which word is the longest and shortest and add
	 * them to the respective variable
	 */
	public void recordWord(String word) {
		
		if(word != null) {
			word = word.trim(); //this trims the passed in word to stop any string with just spaces from getting through the next if statement
			if(word != "") {
				lastWord = word;
				wordCount++;
				if(word.length() > longestWord.length()) {
					longestWord = word;
				}
				if(word.length() < shortestWord.length() || shortestWord == "") {
					shortestWord = word;
				}
				totalChars = totalChars + word.length();
			}
			
		}
		System.out.println("Error not a valid string");
	}

	/**
	 * @return total number of characters recorded.
	 */
	public int getTotalChars() {
		
		return totalChars;
	}

	/**
	 * @return total number of words recorded.
	 */
	public int getWordCount() {
		
		return wordCount;
	}

	/**
	 * @return the number of times {@link #reset()} has been called.
	 */
	public int getResetCount() {

		return resetCount;
	}

	/**
	 * Gets the longest word recorded.
	 * 
	 * note: If multiple longest recorded words contain the same number of
	 * characters, then the first one recorded is returned.
	 * 
	 * @return the longest recorded word
	 */
	public String getLongestWord() {

		return longestWord; 
	}

	/**
	 * Gets the shortest word recorded.
	 * 
	 * note: If multiple shortest recorded words contain the same number of
	 * characters, then the first one recorded is returned.
	 * 
	 * @return the shortest recorded word
	 */
	public String getShortestWord() {

		return shortestWord;
	}

	/**
	 * Gets the most recently recorded word.
	 * 
	 * @return the most recently recorded word.
	 */
	public String getLastWord() {

		return lastWord; 
	}

	/**
	 * Calculates and returns the average length of all recorded words.
	 * 
	 * @return the average length of all recorded words. This will be 0.0 if no
	 *         words have been recorded.
	 *         
	 * The if statement checks if word count is 0 and returns 0.0 if true
	 */
	public double getAveWordLength() {

		if(wordCount == 0) {
			return 0.0;
		}
		else {
			return ((double)totalChars / wordCount);
		}
	}
	
	/**
	 * Resets the elements of the object by setting them to their original value and increments the reset count by 1
	 */
	public void reset() {
		
		totalChars = 0;
		wordCount = 0;
		longestWord = "";
		shortestWord = "";
		lastWord = "";
		resetCount++;
	}

}
