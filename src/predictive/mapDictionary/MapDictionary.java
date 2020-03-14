package predictive.mapDictionary;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import predictive.interfaces.Dictionary;
import predictive.prototype.PredictivePrototype;

/**
 * @author Charalampos Makrylakis
 * This class implements the Dictionary interface, i.e. the signatureToWords(String signature) 
 * method using a HashMap.
 * A HashMap consists of keys and values corresponding to each key. In our implementation, we
 * map String signatures to sets of words.
 * HashMaps are faster than HashTables and TreeMaps, so it is the preferred data structure in 
 * this case, since we are interested in simply retrieving values (sets) from keys (signatures),
 * which can be achieved in constant time O(1) by a HashMap.
 */
public class MapDictionary implements Dictionary {

	private Map<String, Set<String>> signatureToWordsMap;
	
	/**
	 * Constructor which takes a String path to the dictionary, reads it 
	 * and stores it in a HashMap of (signature, setOfWords).
	 * @param path, is the path to a dictionary as String.
	 */
	public MapDictionary(String path) {		
		this.signatureToWordsMap = new HashMap<>();
		
		try {
			Scanner scanner = new Scanner(new File(path));
			while(scanner.hasNextLine()) {
				String nextString = scanner.nextLine().toLowerCase(); // store the dictionary word in a variable
				if(PredictivePrototype.isValidWord(nextString)) { // if the dictionary word is valid
					String signature = PredictivePrototype.wordToSignature(nextString); // store its signature
					addToHashMap(signature, nextString); //add nextString to the set of words corresponding to the signature
				}
			}
			scanner.close();
		}
		catch(IOException e) {
			System.out.println("File not found");
		}
	}
	
	/**
	 * Helper method  which adds a (signature, word) pair to HashMap of (signature, setOfWords)
	 * @param signature, is the key of the HashMap.
	 * @param word, is the value to be added to the set of words corresponding to the signature.
	 */
	private void addToHashMap(String signature, String word) {		
		// if the key signature already exists
		if(signatureToWordsMap.containsKey(signature)) {
			signatureToWordsMap.get(signature).add(word); // add word to corresponding set
		}
		// else create a new set, add the word and put it in the HashMap
		else {
			Set<String> setOfPossibleWords = new HashSet<>();
			setOfPossibleWords.add(word);
			signatureToWordsMap.put(signature, setOfPossibleWords);
		}
	}
	
	/**
	 * Implementation of the signatureToWords(String signature) method of the Dictionary interface.
	 * Method which finds the possible words that could correspond to a given signature and returns 
	 * them as a set.
	 * @param signature, is the signature required to be matched with words.
	 * @return a set of possible matching words from the dictionary.
	 */
	@Override
	public Set<String> signatureToWords(String signature) {
	
		if(PredictivePrototype.isNumericalSignature(signature)) { // if the signature consists of numbers
			// return the set of words corresponding the this signature, or an empty set if the signature does not exist
			Set<String> result = signatureToWordsMap.get(signature);
			return result == null ? new HashSet<>() : result; 
		}
		return new HashSet<>(); 
	}
}
