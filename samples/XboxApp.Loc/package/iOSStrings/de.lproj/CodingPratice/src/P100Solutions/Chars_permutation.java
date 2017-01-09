package P100Solutions;

import java.util.Vector;

import junit.framework.TestCase;

/* Questions: Please print all permutations of a given string. For example, 
 * print “abc”, “acb”, “bac”, “bca”, “cab”, and “cba” when given the input string “abc”.   
*/

public class Chars_permutation extends TestCase {
		
	void permutation(char[] ar) {		
		 if (ar== null || ar.length == 0)
			 return;
		 Vector<String> result = new Vector<String>();
		 getValues(ar, 0, result);
		 
		 print(result, ar);
	}
	
	void getValues(char[] a, int st, Vector<String> result) {
		if (st == a.length - 1) {
			result.add(String.valueOf(a));
			return;
		}
		
		getValues(a, st + 1, result);		
		for (int i = st + 1; i < a.length; i++) {
			exch(a, st, i);
			getValues(a, st + 1, result);
			exch(a, st, i);
		}
		
	}
	
	void exch(char[] a, int i, int j){
		char tem = a[j];
		a[j] = a[i];
		a[i] = tem;
	}
	
	void print(Vector<String> result, char[] a) {
		if (result.size() == 0)
			System.out.println("Empty permutation");
		else {
			System.out.println("\r\nThe result for   " + String.valueOf(a));
			for(String str : result) {
				System.out.print(str);
				System.out.print("\t");
			}
		}
	}
	 
	public void testFun(){	
		permutation(new char[]{'a'});
		permutation(new char[]{'a', 'b'});
		permutation(new char[]{'a', 'b', 'c'});
		permutation(new char[]{'a', 'b', 'c', 'd'});
		permutation(new char[]{'a', 'b', 'c', 'd', 'e'});
		permutation(new char[]{'a', 'b', 'c', 'd', 'e', 'f'});
		permutation(new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'});		
	}
	
}