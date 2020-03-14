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