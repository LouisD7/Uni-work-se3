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

	// TODO::Part2 add missing attributes (use UML model to identify these)
	Set<String> dictionary = new HashSet<String>();
	Set<String> unknownWords = new HashSet<String>();
	Set<String> knownWords = new HashSet<String>();

	////////////////////////////////////////////////////////////////////

	
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
	 */
	public void addToDictionary(String filename) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(filename));

		String nextLine = null;	// TODO:Part2 read the next line from the file

		while ((nextLine = reader.readLine())!= null) {

			// TODO:Part2 process and store each word
			nextLine.trim();
			if(nextLine.length() > 0) {
				nextLine.toLowerCase();
				System.out.println(nextLine);
				dictionary.add(nextLine);
			}
		}

		reader.close();
	}
	
	@Override
	public void performAnalysis(String filename) throws IOException {

		// TODO:Part2 clear existing known and unknown word collections.
		
		selectInputFile(filename);	// select the file to be analysed

		String nextWord = readNextWord();

		// process all available words
		while (nextWord != null) {

			// TODO:Part2 identify whether next word is within the dictionary
			// if it is then record as a known word, otherwise record as an unknown word.
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

		// TODO:Part2 clear the dictionary contents
		dictionary.clear();
	}
	
	/**
	 * 
	 * @return the set of words that represents the dictionary of known words.
	 */
	public Set<String> getDictionary() {

		return dictionary;	// TODO:Part2 return appropriate attribute
	}

	/**
	 * 
	 * @return the set of known words found during the most recent analysis.
	 */
	public Set<String> getKnownWords() {

		return knownWords;	// TODO:Part2 return appropriate attribute
	}

	/**
	 * 
	 * @return the set of unknown words found during the most recent analysis.
	 */
	public Set<String> getUnknownWords() {

		return unknownWords;	// TODO:Part2 return appropriate attribute
	}

	//////////////////////////////////////////////////////////////////

	/**
	 * Constructor
	 */
	public DictionaryAnalyser() {

		super("Dictionary Analyser", "checks for words which are present within a dictionary of known words");
	}
}
