package predictive.prototype;

/**
 * @author Charalampos Makrylakis
 * This class contains a main method which calls the signatureToWords(String signature) method.
 */
public class Sigs2WordsProto {
	public static void main(String[] args) {
		// no arguments provided
		if(args.length == 0) {
			System.out.println("No signature provided");
		}
		else {
			long start = System.currentTimeMillis();
			for(int i = 0; i < args.length; i++) {			
				System.out.println(args[i] + " : " + PredictivePrototype.signatureToWords(args[i]));
			}
			long end =  System.currentTimeMillis();
			System.out.println("Time elapsed: " + (end - start)/1000.0 + "s");
		}
	}

}
