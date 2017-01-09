package EPI;

import junit.framework.TestCase;
/* 
 * Write the program to convert a string of integer from one base to another. 
 * For example 1101 of base 2 to d of base 16.
*/
				
public class Strings7_StringIntegerConvertion extends TestCase {
		
	String convert(String str, int baseFrom, int baseTo) {
		if (str == null || str.length() == 0 || baseFrom < 2 || baseTo < 2)
			return null;
		
		boolean isNegative = str.charAt(0) == '-';
		
		int v = 0;
		for (int i = isNegative ? 1 : 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c - '0' < baseFrom) {
				v = v * baseFrom + c - '0';
			}
			else
				return null;			
		}
		
		StringBuffer s = new StringBuffer();
		if (v == 0)
			return "0";
		
		while (v > 0) {
			int m = v % baseTo;
			if (m < 10)
				s.append((char) (m + '0'));
			else if (m < 16) {
				s.append((char)(m + 'a'));
			}
			
			v /= baseTo;
		}
		
		if (isNegative)
			s.append('-');
		
		return s.reverse().toString();
	}
	
	
	public void testFun(){	
		assertTrue(convert("-11010", 2, 10).equals("-26"));
	}		
}