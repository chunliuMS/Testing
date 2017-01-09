package CCI;

import junit.framework.TestCase;

//Implement an algorithm to determine if a string has all unique characters.
public class CCI_01_1_UniqueChars extends TestCase {
	boolean areAllUniqueCharacters(String str){
		if (str == null)
			return true;
		
		if (str.length() > 256)
			return false;
		
		boolean[] chars = new boolean[255];
		for (int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			if (chars[c] == true)
				return false;
			else
				chars[c] = true;
		}
		return true;
	}
	
	public void testFun(){
		assertTrue(areAllUniqueCharacters("abcdefg"));
		assertFalse(areAllUniqueCharacters("abcdefcg"));
		assertFalse(areAllUniqueCharacters("aabcdefg"));
		assertFalse(areAllUniqueCharacters("abcaefg"));
		assertTrue(areAllUniqueCharacters("a"));
		assertFalse(areAllUniqueCharacters("abcdefa"));
		assertTrue(areAllUniqueCharacters(""));
		assertTrue(areAllUniqueCharacters(null));
	}
}
