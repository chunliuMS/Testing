package LeetCode;

import junit.framework.TestCase;

//Match a string to a wild card pattern. "*" represents any char(s) and "?" any single char.
public class LC016_WildCardMatch extends TestCase {
	
	boolean match(String s, int si, String p, int pi){
		if (pi == p.length())
			return (si == s.length());
		if (si == s.length())
			return pi == p.length();
				
		if (p.charAt(pi) == '*'){
			while (si <= s.length()){
				if(match(s, si, p, pi + 1))
					return true;
				si++;
			}
			return false;
		}
		else if (p.charAt(pi) == '?' || s.charAt(si) == p.charAt(pi))
			return match(s, si + 1, p, pi + 1);
		else 
			return false;
	}
	
	boolean match(String s, String p){
		return match(s, 0, p, 0);
	}
	
	public void testFun(){
		assertFalse(match("accde", "*c"));
		assertTrue(match("bok", "*o*"));
	}	
}
