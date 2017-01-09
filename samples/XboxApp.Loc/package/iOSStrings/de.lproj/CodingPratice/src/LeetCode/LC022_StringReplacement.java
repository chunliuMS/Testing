package LeetCode;

import junit.framework.TestCase;


public class LC022_StringReplacement extends TestCase {
	
	boolean Match(char[] s, int si, char[] p){
		if (si + p.length  > s.length)
			return false;
		
		for (int i = 0; i < p.length; i++)			
			if(s[i + si] != p[i])
				return false;
		return true;
	}
	
	String Replace(String s, String p){
		if (p == null || s == null || s.length() < p.length())
			return s;
		
		char[] sa = s.toCharArray();
		char[] pa = p.toCharArray();
		
		int fast = 0; 
		int slow = 0;
		
		while(fast < sa.length - 1){
			boolean matched = false;
			while(Match(sa, fast, pa)){
				matched = true;
				fast += pa.length;
			}
			if (matched)
				sa[slow++] = 'X';
			else if (slow != fast)
				sa[slow++] = sa[fast++];
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(sa, 0, slow);
		return sb.toString();
	}
	
	public void testFun(){
		System.out.println(Replace("abcdeffdfegabcabc", "abc"));
	}	
}
