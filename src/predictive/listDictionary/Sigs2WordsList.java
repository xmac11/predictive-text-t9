package predictive.listDictionary;

/**
 * @author Charalampos Makrylakis
 * This class contains a main method which calls the signatureToWords(String signature) method.
 * 
 * The execution times of Sigs2WordsList and Sigs2WordsProto when compared for 1 input, the times are:
 * 
 * Sigs2WordsList:	 0.019s
 * Sigs2WordsProto:  0.484s
 * 
 * ***************************************************************************************
 * 
 * When tested for a small number of inputs, for example 5 inputs, the times are:
 * 
 * Sigs2WordsList:	 0.021s
 * Sigs2WordsProto:  1.671s
 *  
 * ***************************************************************************************
 * 
 * The execution times of Sigs2WordsList and Sigs2WordsProto were compared for an input of about 500 signatures,
 * giving the following results:
 * 
 * Sigs2WordsList:	 1.778s
 * Sigs2WordsProto:  136.202s = 2m16s
 * 
 * The reason is that in the Prototype, the same procedure, i.e. scanning the dictionary is followed for every 
 * input and therefore the total time increases with respect to the number of inputs.
 * 
 * Whereas, in the List case, the ArrayList is created by scanning the dictionary only once and all subsequent
 * searches are performed on the sorted ArrayList in logarithmic time through binary search. 
 * 
 * As a result, the time difference between Sigs2WordsList and Sigs2WordsProto becomes more and more noticeable 
 * as the number of inputs increases.
 */
public class Sigs2WordsList {
	public static void main(String[] args) {	
		// no arguments provided
		if(args.length == 0) {
			System.out.println("No signature provided");
		}
		else {
			ListDictionary dictionaryObject = new ListDictionary("dictionaries/words"); // create dictionaryObject for the "words" dictionary
			long start = System.currentTimeMillis();
			for(int i = 0; i < args.length; i++) {			
				System.out.println(args[i] + " : " + dictionaryObject.signatureToWords(args[i]));
			}
			long end =  System.currentTimeMillis();
			System.out.println("Time elapsed: " + (end - start)/1000.0 + "s");
		}
	}
}
