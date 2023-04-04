package analyser;

/**
 * Stores result information related to the analysis of text.
 * 
 * @author mdixon
 */
public class AnalysisResult {

	// TODO::Part1 add missing attributes (use UML model to identify these)

	private int totalChars, wordCount, resetCount;
	private String longestWord = "", shortestWord = "", lastWord = "";
	
	////////////////////////////////////////////////////////////

	/**
	 * Records a word, using the information given to calculate analysis results.
	 * 
	 * Any whitespace is trimmed from either side of the word prior to it being
	 * recorded.
	 * 
	 * @param word the word to be recorded (null or empty words are ignored).
	 */
	public void recordWord(String word) {
		
		
		if(word != null) {
			//this trims the passed in word to stop any string with just spaces from getting through the next if statement
			word = word.trim(); 
			if(word != "") {
				lastWord = word;
				wordCount++;
				if(word.length() > longestWord.length()) {
					longestWord = word;
				}
				//the second check allows the shortestWord element to be changed to the first word passed through the analyser
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

		return lastWord; // TODO:Part1 return correct attribute
	}

	/**
	 * Calculates and returns the average length of all recorded words.
	 * 
	 * @return the average length of all recorded words. This will be 0.0 if no
	 *         words have been recorded.
	 */
	public double getAveWordLength() {

		//This if statement checks if word count is 0 and returns 0.0 if true
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
