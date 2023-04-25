package analyser;

import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashMap;

/**
 * A kind of {@link BaseAnalyser} that counts the number of unique individual
 * character occurrences within the text.
 * 
 * @author mdixon
 */
public class CharFrequencyAnalyser extends BaseAnalyser {

	// TODO:Part4 add missing attributes (see UML model).
	/**
	* Another use of linked hash map so the code can record the order at which characters
	* were recorded to fill the requirment of the first most recorded character being returned as the most recorded
	*/
	private LinkedHashMap<Character, Integer> charCounts = new LinkedHashMap<>();
	private int vowelCount = 0, singleCharCount = 0;
	//////////////////////////////////////////////////////////////////

	@Override
	public void performAnalysis(String filename) throws IOException {

		// TODO:Part4 clear map contents and re-init other attributes.
		// Whenever this method is called it resets the map and the counts
		charCounts.clear();
		vowelCount = 0;
		singleCharCount = 0;
		

		selectInputFile(filename); // select the file to be analysed

		String nextWord = null;

		// process all available words
		while ((nextWord = readNextWord())!= null) {

			// TODO:Part4 extract each character from the next word, and add to the occurrence map
			// TODO:Part4 check if each character is a vowel, if so increment correct attribute
			//reset i to zero each time we go through each character in a string.
			int i = 0;
			/**
			* This while loop goes through each character in the string and uses the if statements to check if
			* the character is a vowel and either adds it to the map or increments the value attached to the character
			* if it already exist in the map
			*/
			while(i < nextWord.length()) {
				Character nextChar = nextWord.charAt(i);
				if(nextChar == 'a' || nextChar == 'e' || nextChar == 'i' || nextChar == 'o' || nextChar == 'u' ) {
					vowelCount++;
				}
				if(charCounts.containsKey(nextChar)) {
					charCounts.put(nextChar, charCounts.get(nextChar)+ 1);
				}
				else
					charCounts.put(nextChar, 1);
				//increment the count of next character at the end of the loop to not change the position while doing the checks
				i++;
			}
			//this if statement checks the length of the word if its equal to 1 then it must be a single character
			if(nextWord.length() == 1) {
				singleCharCount++;
			}
		}
			// TODO:Part4 increment attribute that counts single character words (if appropriate).
	}

	@Override
	public void generateReport(PrintStream out) {

		generateHeader(out);

		out.println("Most popular character is '" + getMostPopularChar() + "' with an occurrence count of "
				+ getMostPopularCharCount());
		out.println("Unique character count is " + getUniqueCharCount());
	}

	/**
	 * Gets the most popular character of the most recent analysis.
	 * 
	 * If multiple characters have the same number of occurrences, then the first of
	 * these recorded should be returned.
	 * 
	 * @return the most popular character of the most recent analysis, this will be
	 *         null an analysis is yet to be performed.
	 */
	public Character getMostPopularChar() {

		// find the most popular character
		Character character = null;
		int max = 0;
		int currentCount = 0;

		// TODO:Part4 if highest occurrence count so far, record the character.
		/**
		* this for each loop goes through each character key in the map and uses the if statement to check
		* if the current character count is bigger than the recorded maximum and if so sets the max to the current count
		* then the characrer is set by whichever character had the highest count
		*/
		for(Character a : charCounts.keySet()) {
			currentCount = charCounts.get(a);
			// The > is used here instead of => so the first highest recorded word is recorded as max 
			// instead of any over count that reaches the same number
			if(currentCount > max) {
				max = currentCount;
				character = a;
			}
		}
		return character;
	}

	/**
	 * Gets the number of times the most popular character(s) appeared within the
	 * most recent analysis.
	 * 
	 * @return the number of times the most popular character(s) appeared, 0 if an
	 *         analysis is yet to be performed.
	 */
	public int getMostPopularCharCount() {

		// TODO:Part4 find the most popular character count
		int max = 0;
		int currentCount = 0;

		// TODO:Part4 if highest occurrence count so far, record the character.
		//this method works the same as getMostPopularChar but instead only records and return the integer value from the map
		for(Character a : charCounts.keySet()) {
			currentCount = charCounts.get(a);
			
			if(currentCount > max) {
				max = currentCount;
			}
		}
		return max;
	}

	/**
	 * Gets the number of unique characters within the analysed text.
	 * 
	 * @return the number of unique characters analysed.
	 */
	public int getUniqueCharCount() {

		return charCounts.size(); // TODO:Part4 return size of the map
	}

	/**
	 * Gets the total number of characters which are vowels within the analysed
	 * text.
	 * 
	 * @return the total number of characters which are vowels
	 */
	public int getVowelCount() {

		return vowelCount; // TODO:Part4 return appropriate attribute
	}

	/**
	 * Gets the total number of characters which are not vowels within the analysed
	 * text.
	 * 
	 * @return the total number of characters which are not vowels
	 */
	public int getNonVowelCount() {

		// TODO:Part4 calc result and return (hint: can use getResult().getTotalChars() to get total char count).
		//taking the vowel count away from the totatl character count leaves us with the total no vowel characters
		return getResult().getTotalChars() - vowelCount;
	}

	/**
	 * Gets the total number of single character words present within the analysed
	 * text.
	 * 
	 * @return the total number of single character words
	 */
	public int getSingleCharacterWordCount() {

		return singleCharCount; // TODO:Part4 return appropriate attribute
	}

	/**
	 * Gets the total number of multi-character words present within the analysed
	 * text.
	 * 
	 * @return the total number of multi-character words
	 */
	public int getMultiCharacterWordCount() {

		// TODO:Part4 calc result and return (hint: can use getResult().getWordCount() to get total word count).
		//taking the single character word count from the word count leaves us with the multi character count
		return getResult().getWordCount() - singleCharCount;
	}

	/**
	 * Gets the number of times the given character occurred in the most recent
	 * analysis.
	 * 
	 * @param character the character for which the occurrence count is required.
	 * @return the number of times the given character appeared, 0 if it did not
	 *         ever appear.
	 */
	public int getCountOf(Character character) {
		
		// This if statement checks if the character key exist in the map before reutrning its value and if not returns a 0 instead
		if(charCounts.containsKey(character)) {
			return charCounts.get(character);
		}
		else
			return 0; // TODO:Part4 lookup the character in the map and return the associated count value.
	}

	/**
	 * Constructor
	 */
	public CharFrequencyAnalyser() {

		super("Character Frequency Analyser", "counts the number of unique character occurrences within the text");
	}

}
