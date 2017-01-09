package EPI;

import junit.framework.TestCase;
/* 
 * Convert integer to string and verse versa. 
*/
				
public class Strings7_BaseConversion extends TestCase {
		
	String toString(int v) {
		boolean isNegative = v < 0;
		if (isNegative)
			v *= -1;
		if (v == 0)
			return "0";
		
		StringBuffer s = new StringBuffer();
		while (v > 0) {
			int m = v % 10;
			s.insert(0, (char) ('0' + m));
			v /= 10;
		}
		
		if (isNegative)
			s.insert(0, '-');
		
		return s.toString();
	}
	
	int toInteger(String s) {
		if (s == null || s.length() == 0)
			return 0;
		
		boolean isNegative = false;
		
		if (s.charAt(0) == '-') {
			if (s.length() > 1)
				isNegative = true;
			else 
				return 0;
		}
		
		int v = 0;
		
		for (int i = isNegative ? 1 : 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9')
				v = v * 10 + (c - '0');
			else
				return 0;				
		}
		
		return isNegative ? -v : v;
	}
	
	
	public void testFun(){	
		assertTrue(toInteger("-123450") == -123450);
		assertTrue(toString(-12345505).equals("-12345505"));
	}		
}