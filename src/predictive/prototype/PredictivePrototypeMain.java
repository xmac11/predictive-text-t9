package predictive.prototype;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Charalampos Makrylakis
 * This class contains the wordToSignature(String word) method and the
 * signatureToWords(String signature) method.
 */
public class PredictivePrototypeMain {

	private static final HashMap<Character, Character> CHARtoDIGITmap = 
			new HashMap<Character, Character>(){{
					put('a', '2'); put('b', '2'); put('c', '2');
					put('d', '3'); put('e', '3'); put('f', '3');
					put('g', '4'); put('h', '4'); put('i', '4');
					put('j', '5'); put('k', '5'); put('l', '5');
					put('m', '6'); put('n', '6'); put('o', '6');
					put('p', '7'); put('q', '7'); put('r', '7'); put('s', '7');
					put('t', '8'); put('u', '8'); put('v', '8');
					put('w', '9'); put('x', '9'); put('y', '9'); put('z', '9');
			}};
	
	//private static Set<String> setOfPossibleWords;
	private static String filename = "words";
	
	/**
	 * Helper method which evaluates whether a character is alphabetic.
	 * Loops through the HashMap. If the character is found (as the key)
	 * returns true, otherwise returns false.
	 * @param c, character of type char.
	 * @return true if the character is alphabetic, false otherwise.
	 */
	static boolean isAlphabetic(char c) {
		for(Entry<Character, Character> e: CHARtoDIGITmap.entrySet()) {
			if(e.getKey() == c) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Helper method which evaluates whether a word is alphabetic.
	 * Loops through each character of the word and checks whether
	 * it is alphabetic.
	 * @param word of type String.
	 * @return true if the word is alphabetic, false otherwise.
	 */
	static boolean isValidWord(String word) {
		word = word.toLowerCase();
		for(int i = 0; i < word.length(); i++) {
			if(!isAlphabetic(word.charAt(i))) {
				return false;
			}			
		}
		return true;
	}
	
	/**
	 * setter for the filename of the dictionary
	 * @param the new filename of type String.
	 */
	public static void setFilename(String filename) {
		PredictivePrototypeMain.filename = filename;
	}
	
	/**
	 * Method which takes a word and returns a numeric signature.
	 * 
	 * This is done using the StringBuffer class rather than a String.
	 * The reason is that a String is immutable, meaning that for every modification a 
	 * new String object is created. In addition, it is slower and consumes more memory. 
	 * Whereas, StringBuffer can be easily modified, is faster and consumes less memory.
	 * Since this method loops through every character of the word and appends the 
	 * corresponding number at the end of the result, therefore constantly modifying it,
	 * it is preferable to use the StringBuffer. 
	 * @param word, of type String.
	 * @return the numeric signature of the word, as a String.
	 */
	public static String wordToSignature(String word) {
		word = word.toLowerCase(); // convert word to lower-case
		StringBuffer stringBuffer = new StringBuffer();

		for(int i = 0; i < word.length(); i++) { // loop through each character
			char c = word.charAt(i);
			if(isAlphabetic(c)) { // if character is alphabetic
				stringBuffer.append(CHARtoDIGITmap.get(c)); //append numeric value
			}
			else {
				stringBuffer.append(' '); // else append a space
			}
		}
		return stringBuffer.toString();
	}

	/**
	 * Helper method  which checks whether a character is a numerical value.
	 * @param c, character of type c.
	 * @return true if the character is numerical, false otherwise.
	 */
	static boolean isNumericalCharacter(char c) {  
		try {  
			Integer.parseInt(String.valueOf(c));
		}  
		catch(NumberFormatException e) {  
			return false;  
		}  
		return true;  
	}
	
	/**
	 * Helper method  which checks whether String represents
	 * a numerical value, by checking each character.
	 * @param s, of type String.
	 * @return true if the String is numerical, false otherwise.
	 */
	static boolean isNumericalSignature(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(!isNumericalCharacter(s.charAt(i))) {
				return false;
			}
		}
		return true;
	} 
	
	/**
	 * Method which takes the given numeric signature and returns a set of 
	 * possible matching words from the dictionary.
	 * The returned list does not have duplicates and each word is in lower-case.
	 * 
	 * In this method, the dictionary is not stored within the program.
	 * This would be inefficient in terms of memory and time since it would require 
	 * to subsequently search the whole data structure once again in order to find 
	 * the words with matching signatures.
	 * @param signature, of type String.
	 * @return a set of possible matching words from the dictionary.
	 */
	public static Set<String> signatureToWords(String signature) {		
		
		Set<String> setOfPossibleWords =  new HashSet<>();	
		
		if(isNumericalSignature(signature)) { // if the signature contains only numbers
			try{		
				Scanner scanner = new Scanner(new File(filename));
				while(scanner.hasNextLine()) {
					String nextString = scanner.nextLine(); // store the dictionary word in a variable
					// if the dictionary word is valid and its signature matches the required signature
					if(isValidWord(nextString) && wordToSignature(nextString).equals(signature)) {
						setOfPossibleWords.add(nextString.toLowerCase()); // add the dictionary word to set
					}
				}
				scanner.close();
			}
			catch(IOException e) {
				System.out.println("File not found");
			}
		}	
		Set<String> tset = new TreeSet<>(setOfPossibleWords);
		return tset;
	}
	
	public static void main(String[] args) {
		//createCharMaps();
		//System.out.println(wordToSignature("don't"));
		//System.out.println(isValidWord("hOme!"));
		//System.out.println(isValidWord("ho;e"));
		System.out.println(signatureToWords("329"));	
		
		/*System.out.println(isAlphabetic(';'));
		System.out.println(isValidWord("ho;e"));*/
	}
}
