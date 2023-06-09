package analyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Set;
import java.util.HashSet;

/**
 * A kind of {@link BaseAnalyser} that identifies whether words are present
 * within a specified dictionary.
 * 
 * @author mdixon
 */
public class DictionaryAnalyser extends BaseAnalyser {

	private Set<String> dictionary = new HashSet<String>();
	private Set<String> unknownWords = new HashSet<String>();
	private Set<String> knownWords = new HashSet<String>();
	
	/**
	 * Adds words contained with the specified file into the dictionary of known
	 * words.
	 * 
	 * Each individual word will be on a separate line within the file, and will
	 * not contain any spaces.
	 * 
	 * Any space before or after a word is trimmed prior to storage.
	 *  
	 * Any blank lines are ignored.
	 * 
	 * The words are always stored as lower-case, even if they are upper-case within
	 * the file.
	 * 
	 * note: this appends to the dictionary, with any existing content remaining.
	 * 
	 * @param filename the name of the file containing the words to be added to the dictionary.
	 * @throws IOException if the named file could not be found and loaded.
	 * this while loop assigns the nextLine attribute to the next line of the file 
	 * and then checks if the next line exists or isn't empty before going into the while loop
	 * if statement checks the length of the trimmed line if not larger than 0 the line was just blank and doesn't get added
	 */
	public void addToDictionary(String filename) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String nextLine = null;	

		while ((nextLine = reader.readLine()) != null) {
			
			nextLine = nextLine.trim();// this trim trims any lines of white spaces
			
			if(nextLine.length() > 0) {
				nextLine = nextLine.toLowerCase();
				dictionary.add(nextLine);
			}
		}
		reader.close();
	}
	
	@Override
	public void performAnalysis(String filename) throws IOException {

		// clears the two sets of any known or unknown words.
		knownWords.clear();
		unknownWords.clear();
		selectInputFile(filename);	// select the file to be analysed
		String nextWord = null;

		while ((nextWord = readNextWord()) != null) { // process all available words

			if(dictionary.contains(nextWord)) { // if true then record as a known word, otherwise record as an unknown word.
				knownWords.add(nextWord);
			}
			else {
				unknownWords.add(nextWord);
			}
		}
	}

	@Override
	public void generateReport(PrintStream out) {
		
		generateHeader(out);
	
		out.println("The dictionary word count is " + getDictionary().size());
		out.println("The number of words not present in the dictionary is " + getUnknownWords().size());
		out.println("The number of words present in the dictionary is " + getKnownWords().size());
	}

	/**
	 * Clears the current dictionary contents.
	 */
	public void clearDictionary() {

		dictionary.clear();
	}
	
	/**
	 * 
	 * @return the set of words that represents the dictionary of known words.
	 */
	public Set<String> getDictionary() {

		return dictionary;
	}

	/**
	 * 
	 * @return the set of known words found during the most recent analysis.
	 */
	public Set<String> getKnownWords() {

		return knownWords;
	}

	/**
	 * 
	 * @return the set of unknown words found during the most recent analysis.
	 */
	public Set<String> getUnknownWords() {

		return unknownWords;
	}

	/**
	 * Constructor
	 */
	public DictionaryAnalyser() {

		super("Dictionary Analyser", "checks for words which are present within a dictionary of known words");
	}
}
