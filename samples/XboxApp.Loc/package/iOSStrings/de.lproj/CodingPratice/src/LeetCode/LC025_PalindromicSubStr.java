package LeetCode;

import junit.framework.TestCase;

//Given a string S, find the longest palindromic substring in S.

public class LC025_PalindromicSubStr extends TestCase {
	String preProcess(String s){
		StringBuffer sb = new StringBuffer("^#");
		for (int i = 0; i < s.length(); i++)
			sb.append(s.charAt(i)).append('#');
		sb.append('$');
		return sb.toString();
	}
	
	String longestPalindrome(String s){
		if (s == null || s.length() <= 1)
			return s;
		
		String T = preProcess(s);
		int n = T.length();
		int[] p = new int[n];
		
		int maxIndex = 0;		
		
		int c = 0; 
		int r = 0;
		
		for (int i = 1; i < n - 1; i++){
			int _i =  2 * c - i;
			p[i] = (r > i) ? Math.min(r - i, p[_i]) : 0;
			
			while(T.charAt(i + 1 + p[i]) == T.charAt(i - 1 - p[i])){
				p[i]++;
				if (p[i] > p[maxIndex])
					maxIndex = i;
			}
			
			if (i + p[i] > r){
				c = i;
				r = i + p[i];
			}		
		}
		
		int start = (maxIndex - 1 - p[maxIndex]) / 2;
		return s.substring(start, start + p[maxIndex]);
	}
	
	public void testFun(){
		System.out.println(longestPalindrome("babcbabcbaccba"));
		System.out.println(longestPalindrome("abaaba"));
	}	
}
