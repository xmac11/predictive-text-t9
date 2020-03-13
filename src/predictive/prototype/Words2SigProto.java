package predictive.prototype;

/**
 * @author Charalampos Makrylakis
 * This class contains a main method which calls the wordToSignature(String word) method.
 */
public class Words2SigProto {
	public static void main(String[] args) {
		// no arguments provided
		if(args.length == 0) {
			System.out.println("No words provided");
		}
		else {
			for(int i = 0; i < args.length; i++) {
				System.out.print(PredictivePrototype.wordToSignature(args[i]) + " ");
			}
		}
	}
}
