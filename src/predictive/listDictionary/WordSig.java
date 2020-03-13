package predictive.listDictionary;

/**
 * @author Charalampos Makrylakis
 * 
 * This class pairs the numeric signatures with words.
 * It implements the Comparable interface.
 */
public class WordSig implements Comparable<WordSig> {
	
	private String words;
	private String signature;

	/**
	 * Constructor which creates (word, signature) pairs.
	 * @param words, as String
	 * @param signature, is the signature corresponding to word, as String.
	 */
	public WordSig(String words, String signature) {
		this.words = words;
		this.signature = signature;
	}	
	
	/**
	 * getter for the words
	 */
	public String getWords() {
		return words;
	}
	 
	/**
	 * getter for the signature
	 */
	public String getSignature() {
		return signature;
	}
	
	/**
	 * Implementation of the compareTo method for WordSig objects
	 * @param ws, WordSig object
	 * @return -1 if "this" signature is less than the ws object signature,
	 * 1 if "this" signature is greater than the ws object signature,
	 * 0 if they are equal
	 */
	@Override
	public int compareTo(WordSig ws) {
		return this.signature.compareTo(ws.signature);
	}
}
