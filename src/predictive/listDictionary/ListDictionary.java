package predictive.listDictionary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import predictive.interfaces.Dictionary;
import predictive.prototype.PredictivePrototype;

/**
 * @author Charalampos Makrylakis
 * This class implements the Dictionary interface, i.e. the
 * signatureToWords(String signature) method storing WordSig
 * objects, i.e. (word, signature) pairs in an ArrayList.
 */
public class ListDictionary implements Dictionary {

	private List<WordSig> dictionaryList;

	/**
	 * Constructor which takes a String path to the dictionary, reads it 
	 * and stores it in an ArrayList consisting of WordSig objects, i.e. 
	 * (word, signature) pairs.
	 * After the ArrayList is created, it is sorted as indicated in the
	 * WordSig class by the compareTo method, i.e it is sorted by the signature.
	 * @param path, is the path to a dictionary as String.
	 */
	public ListDictionary(String path) {
		this.dictionaryList = new ArrayList<WordSig>();	
		
		try {
			Scanner scanner = new Scanner(new File(path));
			while(scanner.hasNextLine()) {
				String nextString = scanner.nextLine(); // store the dictionary word in a variable
				if(PredictivePrototype.isValidWord(nextString)) { // if the dictionary word is valid
					WordSig wordSigObject = new WordSig(nextString, // create (word, signature) pair object
							PredictivePrototype.wordToSignature(nextString)); 	
					dictionaryList.add(wordSigObject); // add pair object to the ArrayList
				}
			}
			scanner.close();
		}
		catch(IOException e) {
			System.out.println("File not found");
		}
		Collections.sort(dictionaryList); // sort the ArrayList (by the signatures) as defined in the compareTo method
	}

	/**
	 * Helper method which adds a word of the dictionary to setOfPossibleWords.
	 * @param set, is the set to which the word will be added.
	 * @param index, is the index of the WordSig object in the ArrayList, as int.
	 */
	private void addPossibleWord(Set<String> set, int index) {
		String dictionaryWord = dictionaryList.get(index).getWords().toLowerCase();
		set.add(dictionaryWord);
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
		
		Set<String> setOfPossibleWords = new HashSet<>();
		
		if(PredictivePrototype.isNumericalSignature(signature)) { // if the signature consists of numbers
			// perform binary search in the dictionaryList searching for objects containing the required signature
			// store index of first object found
			int index = Collections.binarySearch(dictionaryList, new WordSig(null, signature));  
			int initialIndex = index; // store a copy of this index for efficiency of the second while loop
			if(index >= 0) { // index is >=0 only when an object is found, otherwise it is <0
				addPossibleWord(setOfPossibleWords, index--); // add the word of the object found and then decrement index by one

				// the list is sorted, therefore objects with the same signature are next to each other
				// scan the list to the left as long as the signatures match, and add the corresponding words to the set
				while(index >= 0 && dictionaryList.get(index).getSignature().equals(signature)) {
					addPossibleWord(setOfPossibleWords, index--);
				}
				// scan the list to the right as long as the signatures match, and add the corresponding words to the set
				while(initialIndex < dictionaryList.size() && dictionaryList.get(initialIndex).getSignature().equals(signature)) {
					addPossibleWord(setOfPossibleWords, initialIndex++);
				}	
			}			
		}				
		return setOfPossibleWords;
	}
}
