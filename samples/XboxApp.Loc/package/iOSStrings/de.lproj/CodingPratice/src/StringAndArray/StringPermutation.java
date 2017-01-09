package StringAndArray;

import junit.framework.TestCase;

//Given 2 strings, write a method to decide if one is a permutation of the other. 
//What if you can not use additional data structure.
public class StringPermutation extends TestCase {
	public boolean isPermutation(String first, String second){
		if (first != null && second != null && first.length() == second.length()){
			int[] chars = new int[256];
			for (int i = 0; i < first.length(); i++){
				char c = first.charAt(i);
				chars[c]++;
			}
			for (int j = 0; j < second.length(); j++) {
				char c = second.charAt(j);
				chars[c]--;
				if (chars[c] < 0)
					return false;				
			}
			return true;
		}
		
		return false;
	}
	public void testFun(){
		assertTrue(isPermutation("this is the dog", "is this dog the"));
		assertTrue(isPermutation("this is the dog", "is thisdog the "));
		assertFalse(isPermutation("this is the dog", "is thisdig the "));
	}
}
