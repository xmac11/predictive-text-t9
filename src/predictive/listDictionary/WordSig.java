package predictive.listDictionary;

/**
 * @author Charalampos Makrylakis
 * 
 * This class pairs the numeric signatures with words.
 * It implements the Comparable interface.
 */
public class WordSig implements Comparable<WordSig> {
	
	private String word;
	private String signature;

	/**
	 * Constructor which creates (word, signature) pairs.
	 * @param words, as String
	 * @param signature, is the signature corresponding to word, as String.
	 */
	public WordSig(String words, String signature) {
		this.word = words;
		this.signature = signature;
	}	
	
	/**
	 * getter for the words
	 */
	public String getWord() {
		return word;
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
		if (this.signature.length() > ws.signature.length())
			return 1;
		if (this.signature.length() < ws.signature.length())
			return -1;

		// if (this.signature.length()==other.signature.length())
		return this.signature.compareTo(ws.signature);
	}
	
	/*
	 * Various compareTo methods.
	 */

	/*public int compareToBad(WordSig other) {
		// will not be in numerical order
		return this.signature.compareTo(other.signature);
	}

	public int compareToo(WordSig other) {
		java.math.BigInteger thisSig = new java.math.BigInteger(this.signature);
		java.math.BigInteger otherSig = new java.math.BigInteger(other.signature);

		return thisSig.compareTo(otherSig);
	}

	public int compareToïï(WordSig other) {
		Double thisSig = Double.parseDouble(signature);
		Double otherSig = Double.parseDouble(other.signature);

		return thisSig.compareTo(otherSig);
	}*/
}
