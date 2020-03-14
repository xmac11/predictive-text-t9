package predictive.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import predictive.listDictionary.ListDictionary;
import predictive.mapDictionary.MapDictionary;
import predictive.prototype.PredictivePrototype;
import predictive.treeDictionary.TreeDictionary;

public class MyTests {

	private ListDictionary wordsListDictionary;
	private ListDictionary myListDictionary;
	private ListDictionary emptyListDictionary;

	private MapDictionary wordsMapDictionary;
	private MapDictionary myMapDictionary;
	private MapDictionary emptyMapDictionary;

	private TreeDictionary myTreeDictionary;
	private TreeDictionary emptyTreeDictionary;

	@Before
	public void setUp() throws Exception {
		wordsListDictionary = new ListDictionary("dictionaries/words");
		myListDictionary =  new ListDictionary("dictionaries/myDictionary.txt");
		emptyListDictionary =  new ListDictionary("dictionaries/emptyDictionary.txt");

		wordsMapDictionary = new MapDictionary("dictionaries/words");
		myMapDictionary = new MapDictionary("dictionaries/myDictionary.txt");
		emptyMapDictionary = new MapDictionary("dictionaries/emptyDictionary.txt");

		myTreeDictionary = new TreeDictionary("dictionaries/myTreeDictionary.txt");
		emptyTreeDictionary = new TreeDictionary("dictionaries/emptyDictionary.txt");
	}

	/********************
	 * Tests for Part 1 *
	 ********************/

	// test wordToSignature("home")
	@Test
	public void test1() {
		String expectedString = "4663"; //home
		String actualString = PredictivePrototype.wordToSignature("home");
		assertEquals(expectedString, actualString);
	}

	// test wordToSignature("Hello World! this is the input")
	@Test
	public void test2() {
		String expectedString = "43556 96753  8447 47 843 46788"; //Hello World! this is the input
		String actualString = PredictivePrototype.wordToSignature("Hello World! this is the input");
		assertEquals(expectedString, actualString);
	}

	// test wordToSignature("a4a;a")
	@Test
	public void test3() {
		String expectedString = "2 2 2"; 
		String actualString = PredictivePrototype.wordToSignature("a4a;a");
		assertEquals(expectedString, actualString);
	}

	// test empty word
	@Test
	public void test4() {
		String expectedString = ""; 
		String actualString = PredictivePrototype.wordToSignature("");
		assertEquals(expectedString, actualString);
	}

	// test signatureToWords
	@Test
	public void test5() {
		// test signatureToWords("329")
		Set<String> expectedSet = new HashSet<>();
		expectedSet.add("dbw");
		expectedSet.add("dax");
		expectedSet.add("daw");
		expectedSet.add("fax");
		expectedSet.add("day");
		expectedSet.add("fcy");
		expectedSet.add("fay");

		Set<String> actualSet = PredictivePrototype.signatureToWords("329");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords("7733428")
		expectedSet = new HashSet<>();
		expectedSet.add("predict");
		actualSet = PredictivePrototype.signatureToWords("7733428");
		assertEquals(expectedSet, actualSet);
	}

	// test signatureToWords when resulting word does not exist in the dictionary
	@Test
	public void test6() {
		Set<String> expectedSet = new HashSet<>();
		Set<String> actualSet = PredictivePrototype.signatureToWords("222222222222");
		assertEquals(expectedSet, actualSet);
	}

	// test signatureToWords with invalid input
	@Test
	public void test7() {
		Set<String> expectedSet = new HashSet<>();
		Set<String> actualSet = PredictivePrototype.signatureToWords("abc");
		assertEquals(expectedSet, actualSet);
	}

	// test myDictionary
	@Test
	public void test8() {
		PredictivePrototype.setFilename("dictionaries/myDictionary.txt");
		// test signatureToWords("364")
		Set<String> expectedSet = new HashSet<>();
		expectedSet.add("dog");
		expectedSet.add("doi");
		expectedSet.add("fog");

		Set<String> actualSet = PredictivePrototype.signatureToWords("364");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords("5282")
		expectedSet = new HashSet<>();
		expectedSet.add("java");
		actualSet = PredictivePrototype.signatureToWords("5282");
		assertEquals(expectedSet, actualSet);

		// test invalid input
		expectedSet = new HashSet<>();
		actualSet = PredictivePrototype.signatureToWords("abc");
		assertEquals(expectedSet, actualSet);

		// test invalid input
		actualSet = PredictivePrototype.signatureToWords("4;4");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords when resulting word does not exist in the dictionary
		actualSet = PredictivePrototype.signatureToWords("2222222222");
		assertEquals(expectedSet, actualSet);	
	}

	// test emptyDictionary
	@Test
	public void test9() {
		PredictivePrototype.setFilename("emptyDictionary.txt");
		Set<String> expectedSet = new HashSet<>();
		Set<String> actualSet = PredictivePrototype.signatureToWords("364");
		assertEquals(expectedSet, actualSet);	
	}

	/********************
	 * Tests for Part 2 *
	 ********************/
	// test "words" dictionary
	@Test
	public void test10() {
		// test signatureToWords("329")
		Set<String> expectedSet = new HashSet<>();
		expectedSet.add("dbw");
		expectedSet.add("dax");
		expectedSet.add("daw");
		expectedSet.add("fax");
		expectedSet.add("day");
		expectedSet.add("fcy");
		expectedSet.add("fay");

		Set<String> actualSet = wordsListDictionary.signatureToWords("329");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords("7733428")
		expectedSet = new HashSet<>();
		expectedSet.add("predict");
		actualSet = wordsListDictionary.signatureToWords("7733428");
		assertEquals(expectedSet, actualSet);

		// test base case (signature == 2)
		expectedSet = new HashSet<>();
		expectedSet.add("a");
		expectedSet.add("b");
		expectedSet.add("c");
		actualSet = wordsListDictionary.signatureToWords("2");
		assertEquals(expectedSet, actualSet);

		// test base case (signature == 99999827)
		expectedSet = new HashSet<>();
		expectedSet.add("zyzzyvas");
		actualSet = wordsListDictionary.signatureToWords("99999827");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords when resulting word does not exist in the dictionary
		expectedSet = new HashSet<>();
		actualSet = wordsListDictionary.signatureToWords("222222222222");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords with invalid input
		actualSet = wordsListDictionary.signatureToWords("abc");
		assertEquals(expectedSet, actualSet);

		// test empty word
		actualSet = wordsListDictionary.signatureToWords("");
		assertEquals(expectedSet, actualSet);
	}

	// test myDictionary
	@Test
	public void test11() {
		// test signatureToWords("364")
		Set<String> expectedSet = new HashSet<>();
		expectedSet.add("dog");
		expectedSet.add("doi");
		expectedSet.add("fog");

		Set<String> actualSet = myListDictionary.signatureToWords("364");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords("5282")
		expectedSet = new HashSet<>();
		expectedSet.add("java");
		actualSet = myListDictionary.signatureToWords("5282");
		assertEquals(expectedSet, actualSet);

		// test base case (signature == 2)
		expectedSet = new HashSet<>();
		expectedSet.add("a");
		expectedSet.add("b");
		expectedSet.add("c");
		actualSet = myListDictionary.signatureToWords("2");
		assertEquals(expectedSet, actualSet);

		// test base case (signature == 9)
		expectedSet = new HashSet<>();
		expectedSet.add("w");
		expectedSet.add("x");
		expectedSet.add("y");
		expectedSet.add("z");
		actualSet = myListDictionary.signatureToWords("9");
		assertEquals(expectedSet, actualSet);

		// test invalid input
		expectedSet = new HashSet<>();
		actualSet = myListDictionary.signatureToWords("abc");
		assertEquals(expectedSet, actualSet);

		// test invalid input
		actualSet = myListDictionary.signatureToWords("4;4");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords when resulting word does not exist in the dictionary
		actualSet = myListDictionary.signatureToWords("2222222222");
		assertEquals(expectedSet, actualSet);

		// test empty word
		actualSet = myListDictionary.signatureToWords("");
		assertEquals(expectedSet, actualSet);
	}

	// test emptyDictionary
	@Test
	public void test12() {
		Set<String> expectedSet = new HashSet<>();
		Set<String> actualSet = emptyListDictionary.signatureToWords("364");
		assertEquals(expectedSet, actualSet);
	}

	/********************
	 * Tests for Part 3 *
	 ********************/

	// test "words" dictionary
	@Test
	public void test13() {
		// test signatureToWords("329")
		Set<String> expectedSet = new HashSet<>();
		expectedSet.add("dbw");
		expectedSet.add("dax");
		expectedSet.add("daw");
		expectedSet.add("fax");
		expectedSet.add("day");
		expectedSet.add("fcy");
		expectedSet.add("fay");

		Set<String> actualSet = wordsMapDictionary.signatureToWords("329");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords("7733428")
		expectedSet = new HashSet<>();
		expectedSet.add("predict");
		actualSet = wordsMapDictionary.signatureToWords("7733428");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords when resulting word does not exist in the dictionary
		expectedSet = new HashSet<>();
		actualSet = wordsMapDictionary.signatureToWords("222222222222");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords with invalid input
		actualSet = wordsMapDictionary.signatureToWords("abc");
		assertEquals(expectedSet, actualSet);

		// test empty word
		actualSet = wordsMapDictionary.signatureToWords("");
		assertEquals(expectedSet, actualSet);
	}

	// test myDictionary
	@Test
	public void test14() {
		// test signatureToWords("364")
		Set<String> expectedSet = new HashSet<>();
		expectedSet.add("dog");
		expectedSet.add("doi");
		expectedSet.add("fog");

		Set<String> actualSet = myMapDictionary.signatureToWords("364");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords("5282")
		expectedSet = new HashSet<>();
		expectedSet.add("java");
		actualSet = myMapDictionary.signatureToWords("5282");
		assertEquals(expectedSet, actualSet);

		// test invalid input
		expectedSet = new HashSet<>();
		actualSet = myMapDictionary.signatureToWords("abc");
		assertEquals(expectedSet, actualSet);

		// test invalid input
		actualSet = myMapDictionary.signatureToWords("4;4");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords when resulting word does not exist in the dictionary
		actualSet = myMapDictionary.signatureToWords("2222222222");
		assertEquals(expectedSet, actualSet);

		// test empty word
		actualSet = wordsMapDictionary.signatureToWords("");
		assertEquals(expectedSet, actualSet);
	}

	// test emptyDictionary
	@Test
	public void test15() {
		Set<String> expectedSet = new HashSet<>();
		Set<String> actualSet = emptyMapDictionary.signatureToWords("364");
		assertEquals(expectedSet, actualSet);
	}

	/********************
	 * Tests for Part 4 *
	 ********************/
	@Test
	public void test16() {
		// test signatureToWords("364")
		Set<String> expectedSet = new HashSet<>();
		expectedSet.add("dog");
		expectedSet.add("doi");
		expectedSet.add("fog");

		Set<String> actualSet = myTreeDictionary.signatureToWords("364");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords("36")
		expectedSet = new HashSet<>();
		expectedSet.add("do");
		expectedSet.add("fo");

		actualSet = myTreeDictionary.signatureToWords("36");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords("26")
		expectedSet = new HashSet<>();
		expectedSet.add("an");

		actualSet = myTreeDictionary.signatureToWords("26");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords("6")
		expectedSet = new HashSet<>();
		expectedSet.add("n");

		actualSet = myTreeDictionary.signatureToWords("6");
		assertEquals(expectedSet, actualSet);

		// test invalid input
		expectedSet = new HashSet<>();
		actualSet = myTreeDictionary.signatureToWords("abc");
		assertEquals(expectedSet, actualSet);

		// test invalid input
		actualSet = myTreeDictionary.signatureToWords("4;4");
		assertEquals(expectedSet, actualSet);

		// test signatureToWords when resulting word does not exist in the dictionary
		actualSet = myTreeDictionary.signatureToWords("2222222222");
		assertEquals(expectedSet, actualSet);

		// test empty word
		actualSet = myTreeDictionary.signatureToWords("");
		assertEquals(expectedSet, actualSet);
	}

	// test emptyDictionary
	@Test
	public void test17() {
		Set<String> expectedSet = new HashSet<>();
		Set<String> actualSet = emptyTreeDictionary.signatureToWords("364");
		assertEquals(expectedSet, actualSet);
	}
}
