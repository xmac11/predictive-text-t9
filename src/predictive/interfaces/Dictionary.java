package predictive.interfaces;

import java.util.Set;

/**
 * @author Charalampos Makrylakis
 * 
 * Interface Dictionary provides only the skeleton of a class.
 * It contains the signatureToWords(String signature) method which
 * finds the possible words that could correspond to a given 
 * signature and returns them as a set.
 */
public interface Dictionary {
	public Set<String> signatureToWords(String signature);
}
