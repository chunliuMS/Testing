package StringAndArray;

import junit.framework.TestCase;

//Implement an algorithm to determine if a string has all unique characters. 
//What if you can not use additional data structure.
public class UniqueCharsInString extends TestCase {
	public boolean isUniqueChars(String str){
		if (str == null || str.length() == 0)
			return true;
		if (str.length() > 256)
			return false;
		
		boolean[] char_set = new boolean[256];
		
		for (int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			if (char_set[c])
				return false;
			char_set[c] = true;
		}
		
		return true;
	}
	public void testFun(){
		assertFalse(isUniqueChars("dec f ggg"));
		assertTrue(isUniqueChars("abcdefghijkl tuvwxyz"));
	}
}
