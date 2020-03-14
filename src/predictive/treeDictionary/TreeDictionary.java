package predictive.treeDictionary;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import predictive.interfaces.Dictionary;
import predictive.prototype.PredictivePrototype;

/**
 * @author Charalampos Makrylakis
 * This class implements the Dictionary interface, i.e. the signatureToWords(String signature) method using a 
 * Tree Data Structure. 
 * Each TreeDictionary object consists of a set of words and 8 children (branches), as well as a boolean value
 * indicating whether the node is empty (i.e. its set of words is empty).
 */
public class TreeDictionary implements Dictionary {

	private Set<String> wordsSet;
	private TreeDictionary[] children;
	private boolean isEmpty;
	
	/**
	 * Constructor which takes a String path to the dictionary, reads it and stores it in a Tree Data Structure.
	 * Each node of the tree contains a collection of all the words that can possibly be created by the partial 
	 * signature along the path as well an Array of size 8 containing its subtrees.
	 * @param path, is the path to a dictionary as String.
	 */
	public TreeDictionary(String path) {	
		// root node with empty set
		this.wordsSet = new HashSet<>();
		this.children = new TreeDictionary[8];
		this.isEmpty = true;
		// create children for root node
		createChildren();

		try {
			Scanner scanner = new Scanner(new File(path));
			while(scanner.hasNextLine()) {
				String nextString = scanner.nextLine().toLowerCase(); // store the dictionary word in a variable
				if(PredictivePrototype.isValidWord(nextString)) { // if the dictionary word is valid
					String signature = PredictivePrototype.wordToSignature(nextString); // store its signature
					addToTree(signature, nextString); // recursively add signature and its substrings to the tree				
					this.isEmpty = false; // flag the tree as non-empty
				}
			}
			scanner.close();
		}
		catch(IOException e) {
			System.out.println("File not found");
		}
	}
	
	/**
	 * Method which loops through the children of a TreeDictionary object (which are null at 
	 * that point) and creates new TreeDictionary objects for each one of them, i.e. new children
	 * and an empty set of words.
	 */
	private void createChildren() {
		for(int i = 0; i < 8; i++) {
			children[i] = new TreeDictionary();
		}
	}
	
	/**
	 * Constructor which creates TreeDictionary objects by creating an empty array for the 
	 * children and an empty set for the words.
	 */
	private TreeDictionary() {
		this.children = new TreeDictionary[8];
		this.wordsSet = new HashSet<>();
		this.isEmpty = true;
	}
	
	/**
	 * Recursive helper method  which adds a (signature, word) pair to a Tree Data Structure.
	 * Each node of the tree contains a collection of all the words that can possibly be created by the partial 
	 * signature along the path, as well an Array of size 8 containing its subtrees.
	 * @param signature, is the signature of a word (and all its sub-signatures).
	 * @param word, is the word corresponding to a signature (and its sub-signatures).
	 */
	private void addToTree(String signature, String word) {
		// base case
		if(signature.isEmpty()) {
			return;
		}
		// general case
		else {
			String firstChar = signature.substring(0, 1); // first character of signature (2-9)
			int index = Integer.parseInt(firstChar) - 2; // index to be stored in the array (0-7) 

			// pointer to current node
			TreeDictionary currentNode = children[index];

			// create children for an empty node since a word will be added 
			if(currentNode.isEmpty) {
				currentNode.createChildren();
				currentNode.isEmpty = false;
			}
			currentNode.wordsSet.add(word); //add word to set
			

			// continue recursively for the sub tree, with the substring of the signature and the same word
			int lengthOfSignature = signature.length(); // length of signature
			String substring = signature.substring(1, lengthOfSignature);	
			currentNode.addToTree(substring, word);
		}		
	}
	
	/**
	 * Implementation of the signatureToWords(String signature) method of the Dictionary interface.
	 * Method which finds the possible words that could correspond to a given signature as well as
	 * all prefixes of words corresponding to the signature and returns them as a set.
	 * @param signature, is the signature required to be matched with words.
	 * @return a set of possible matching words and prefixes from the dictionary.
	 */
	@Override
	public Set<String> signatureToWords(String signature) {
		// if word is empty or the tree does not contain any words (empty dictionary file), return empty set
		if(signature.isEmpty() || this.isEmpty) {
			return new HashSet<>();
		}
		// else store in a set the whole words returned from the helper method
		Set<String> wholeWords = signatureToWordsHelper(signature);
		// return the trimmed words, i.e. with length equal to signature length
		return trimWords(wholeWords, signature.length()); 
	}	
	
	/**
	 * Recursive helper method which finds all the words that can possibly be created by the partial 
	 * signature along the path and returns them as a set.
	 * @param signature, is the signature required to be matched with words.
	 * @return a set of possible matching whole words from the dictionary.
	 */
	private Set<String> signatureToWordsHelper(String signature){
		
		if(PredictivePrototype.isNumericalSignature(signature)) { // if the signature consists of numbers
			// if sub-tree is empty (signature not corresponding to any words)
			if(this.isEmpty) {
				return new HashSet<>();
			}
			// base case 
			else if(signature.length() == 1) {
				int index = Integer.parseInt(signature) - 2;
				return children[index].wordsSet;
			}
			// general case
			else {
				String firstChar = signature.substring(0, 1); // first character of signature (2-9)
				int index = Integer.parseInt(firstChar) - 2; // index to be stored in the array (0-7) 
				
				// pointer to current node
				TreeDictionary currentNode = children[index];
				
				//continue recursively for the sub tree, with the substring of the signature
				int lengthOfSignature = signature.length(); // length of signature
				String substring = signature.substring(1, lengthOfSignature);							 
				return currentNode.signatureToWordsHelper(substring);			
			}
		}
		return new HashSet<>();
	}

	/**
	 * Helper method to return a set of trimmed words, i.e. the prefixes of the words
	 * with length equal to the length of the required signature.
	 * @param set, is the set of whole words required to be trimmed.
	 * @param length, is the length of the signature, therefore the length of the returned
	 * words in the set.
	 * @return a set of trimmed words, i.e. the words and prefixes of the words with length
	 * equal to the length of the required signature.
	 */
	private Set<String> trimWords(Set<String> set, int length){
		if(set.isEmpty()) {
			return set;
		}
		
		Set<String> trimSet = new HashSet<>();
		for(String s: set) {
			String trimmed = s.substring(0, length);
			trimSet.add(trimmed);
		}
		return trimSet;
	}
}
