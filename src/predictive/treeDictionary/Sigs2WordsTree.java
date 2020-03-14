package predictive.treeDictionary;

/**
 * @author Charalampos Makrylakis
 * This class contains a main method which calls the signatureToWords(String signature) method.
 * 
 * The execution times of Sigs2WordsList and Sigs2WordsMap when compared for 500 inputs, the times are:
 * 
 * Sigs2WordsList:	1.995s
 * Sigs2WordsMap:	1.967s
 * 
 * For 1000 inputs:
 * 
 * Sigs2WordsList:	4.278s
 * Sigs2WordsMap:	3.748s
 * 
 * For 1500 inputs:
 * 
 * Sigs2WordsList:	6.532s
 * Sigs2WordsMap:	5.712s
 * 
 * Sigs2WordsList searches in logarithmic time (binary search), whereas Sigs2WordsMap searches in
 * constant time O(1). Time difference becomes more and more noticeable as the number of inputs 
 * increases.
 * 
 * ***************************************************************************************
 * 
 * The execution times of Sigs2WordsMap and Sigs2WordsTree when compared for 500 inputs, the times are:
 * 
 * Sigs2WordsMap:	1.967s
 * Sigs2WordsTree:	2.502s
 * 
 * For 1000 inputs:
 * 
 * Sigs2WordsMap:	3.748s
 * Sigs2WordsTree:	4.738s
 * 
 * For 1500 inputs:
 * 
 * Sigs2WordsMap:	5.712s
 * Sigs2WordsTree:	6.431s
 * 
 * Sigs2WordsMap searches in constant time O(1), as mentioned earlier, whereas Sigs2WordsTree
 * depends on the length of the largest string stored in the tree, i.e. O(n) where n is the
 * length of the largest string. As a result, if the signature searched corresponds to a very long
 * word, it will require considerably more time than Sigs2WordsMap. 
 */
public class Sigs2WordsTree {
	public static void main(String[] args) {
		// no arguments provided
		if(args.length == 0) {
			System.out.println("No signature provided");
		}
		else {
			TreeDictionary dictionaryObject = new TreeDictionary("dictionaries/words"); // create dictionaryObject for the "words" dictionary
			long start = System.currentTimeMillis();
			for(int i = 0; i < args.length; i++) {			
				System.out.println(args[i] + " : " + dictionaryObject.signatureToWords(args[i]));
			}
			long end =  System.currentTimeMillis();
			System.out.println("Time elapsed: " + (end - start)/1000.0 + "s");
		}
	}
}
