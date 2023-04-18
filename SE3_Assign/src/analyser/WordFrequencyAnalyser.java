package analyser;

import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashMap;

/**
 * A kind of {@link BaseAnalyser} that counts the number of unique word
 * occurrences within the text.
 * 
 * @author mdixon
 */
public class WordFrequencyAnalyser extends BaseAnalyser  {

	/**
	 * Here i used a linked hash map to record the order the elements were added into the
	 * hashmap as this allows the condition for the if there are multiple words with the most occurrences
	 * the first most recorded word is returned.
	 */
	LinkedHashMap<String, Integer> wordCount = new LinkedHashMap<>();
	//////////////////////////////////////////////////////////////////

	@Override
	public void performAnalysis(String filename) throws IOException {

		// clear the word count
		wordCount.clear();
		selectInputFile(filename);	// select the file to be analysed

		String nextWord = null;

		// process all available words this for loop goes through the provided file until there's no words left and is null
		while ((nextWord = readNextWord())!= null) {
			
			//check if next word known, if so increment the occurrence count, otherwise add with a count of 1
			if(wordCount.containsKey(nextWord)) {
				wordCount.put(nextWord, wordCount.get(nextWord)+ 1);
			}
			else
				wordCount.put(nextWord, 1);
		}
	}

	@Override
	public void generateReport(PrintStream out) {
		
		generateHeader(out);
	
		out.println("Most popular word is '" + getMostPopularWord() + "' with an occurrence count of "  +getMostPopularWordCount());
		out.println("Least popular word is '" + getLeastPopularWord() + "'  with an occurrence count of "  +getLeastPopularWordCount());
		out.println("Unique word count is " + getUniqueWordCount());
	}
	
	/**
	 * Gets the most popular word of the most recent analysis.
	 * 
	 * If multiple words have the same number of occurrences, then the first of these recorded should be returned.
	 * 
	 * @return the most popular word of the most recent analysis, this will be an
	 *         empty string if an analysis is yet to be performed.
	 */
	public String getMostPopularWord() {

		int max = 0;
		int currentCount = 0;
		String word = "";
		
		
		// TODO:Part3 find the most popular word and return
		/**
		 *  A for each loop that goes through each key string in the hashmap and check to see if wordCount is the highest recorded so
		 *  at the end of the loop the word with highest count is returned. 
		 *  This function is the same for get least popular word but obviously work inversely
		 */
		for(String s : wordCount.keySet()) {
			currentCount = wordCount.get(s);
			/**
			 * this if statement in most of the getter methods check to if the count is bigger than currently recorded max.
			 * Since the condition is > and not >= this means that if multiple words have the highest count the first one
			 * recorded in the linked hash map is recorded as the most popular word
			 */
			if(currentCount > max) {
				max = currentCount;
				word = s;
			}
		}
		return word;
	}

	/**
	 * Gets the number of times the most popular word(s) appeared within the most recent analysis.
	 * 
	 * @return the number of times the most popular word(s) appeared, 0 if an analysis is yet to be performed.
	 */
	public int getMostPopularWordCount() {

		int max = 0;
		
		// TODO:Part3 find the most popular word count and return
		/**
		 *  A for each loop that goes through each value in the hashmap and check to see if it is the highest recorded value so far
		 *  at the end of the loop the highest count is returned. This function is the same for get least word count but obviously work inversely
		 */
		for(Integer i : wordCount.values()) {
			if(i > max) {
				max = i;
			}
		}

		return max;
	}
	
	/**
	 * Gets the least popular word of the most recent analysis.
	 * 
	 * If multiple words have the same least number of occurrences, then the first of these recorded should be returned.
	 * 
	 * @return the least popular word of the most recent analysis, this will be an
	 *         empty string if an analysis is yet to be performed.
	 */
	public String getLeastPopularWord() {

		// find the least popular word
		int min = Integer.MAX_VALUE;
		int currentCount = 0;
		String word = "";

		// TODO:Part3 find the least popular word and return
		for(String s : wordCount.keySet()) {
			currentCount = wordCount.get(s);
			if(currentCount < min) {
				min = currentCount;
				word = s;
			}
		}
		return word;
	}
	
	/**
	 * Gets the number of times the least popular word(s) appeared within the most recent analysis
	 * 
	 * @return the number of times the most least word(s) appeared, 0 if an analysis is yet to be performed.
	 */
	public int getLeastPopularWordCount() {

		// find the least popular word
		int min = Integer.MAX_VALUE;
		int result = 0;

		// TODO:Part3 find the least popular word count and return
		for(Integer i : wordCount.values()) {
			if(i < min) {
				min = i;
				result = min;
			}
		}
		return result;
	}
	
	/**
	 * Gets the number of unique words within the analysed text.
	 * 
	 * @return the number of unique words analysed.
	 */
	public int getUniqueWordCount() {

		return wordCount.size();	// TODO:Part3 return number of entries within the word count map
	}
	
	/**
	 * Gets the number of times the given word occurred in the most recent analysis.
	 * 
	 * @param word the word for which the occurrence count is required.
	 * @return the number of times the given word appeared, 0 if it did not ever appear.
	 */
	public int getCountOf(String word) {
		
		// TODO:Part3 lookup the word and return its count
		try {
			return wordCount.get(word);
		}
		catch(java.lang.NullPointerException e){
			return 0;
		}
		
	}
	

	/**
	 * Constructor
	 */
	public WordFrequencyAnalyser() {

		super("Word Frequency Analyser", "counts the number of unique word occurrences within the text");
	}

}
