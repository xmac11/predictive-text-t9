# predictive-text-t9
A command-line T9 implementation with Java's Collections Framework for the Software Workshop course at the University of Birmingham

### Description
A command-line program for converting words to their numeric signatures and vice versa. For example, the numeric string `43556` is referred to as a *signature* of the world `hello`. However, a given numeric-signature may correspond to more than one word. For example, entering the numeric signature `4663` produces the words `gone` and `home` in many dictionaries. Predictive text technology is possible by restricting available words to those in a dictionary.
<br/>

Initially a prototype was created where the dictionary was not stored in the program by implementing two static methods: 
* `static String wordToSignature(String word)`  
* `static Set<String> signatureToWords(String signature)`  

Then, three different implementations of the instance method `Set<String> signatureToWords(String signature)`
were created using a `List`, a `Map` and a custom recursive `Tree` data structure.  
In this tree, each node has up to eight branches, one for each number (2-9) that is allowed in a signature. Each path of the tree (from the root to a node) represents a signature or part of a signature. Each node of the tree stores a collection of all the words that can possibly match the partial signature along the path. This means that every word that has a prefix corresponding to the partial signature appears in
the collection. For example, if the dictionary has the words `a`, `ant` and `any`, then the words at nodes corresponding to paths would be as follows: 
* at node 2, we have a, ant and any,
* at node 2; 6, we have ant and any.
* at node 2; 6; 8, we have only ant.

### How to run the program
Clone, compile and run the program from a terminal passing any number of arguments.  
The dictionary read is the `words` file. Add any new words desired in a new line. 

```bash
git clone https://github.com/xmac11/predictive-text-t9.git
cd predictive-text-t9/
```

<details>
	<summary>Prototype</summary>
  
  ```bash
  javac src/predictive/prototype/*.java
  ```
##### Convert signature to word:

  ```bash
  java -cp src/ predictive/prototype/Sigs2WordsProto 4663 329
  ```
###### Output:
  ```bash
  4663 : [hood, ione, ioof, good, hond, inne, gond, hone, hoof, gone, goof, home, gome]
  329 : [dbw, dax, daw, fax, day, fcy, fay]
  Time elapsed: {x.xxx}s
  ```

##### Convert word to signature:
  ```bash
  java -cp src/ predictive/prototype/Words2SigProto home dog java
  ```
###### Output:
  ```bash
  4663 364 5282
  ```
</details>

<details>
	<summary>List</summary>
  
  ```
  javac src/predictive/interfaces/Dictionary.java
  javac -cp src/ src/predictive/listDictionary/*.java
  java -cp src/ predictive/listDictionary/Sigs2WordsList 4663 329
  ```
</details>

<details>
	<summary>Map</summary>
  
  ```
  javac src/predictive/interfaces/Dictionary.java
  javac -cp src/ src/predictive/mapDictionary/*.java
  java -cp src/ predictive/mapDictionary/Sigs2WordsMap 4663 329
  ```
</details>

<details>
	<summary>Tree</summary>
  
  ```
  javac src/predictive/interfaces/Dictionary.java
  javac -cp src/ src/predictive/treeDictionary/*.java
  java -cp src/ predictive/treeDictionary/Sigs2WordsTree 4663 329
  ```
</details>







