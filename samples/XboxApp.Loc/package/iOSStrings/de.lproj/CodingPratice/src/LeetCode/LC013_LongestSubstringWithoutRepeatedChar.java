package LeetCode;

import java.util.Hashtable;
import junit.framework.TestCase;

//Given a string, find the longest substring without repeating characters.
//for example, abcabcbb->abc. bbbbb->b.
public class LC013_LongestSubstringWithoutRepeatedChar extends TestCase {
	String find(String s){
		int n = s.length();
		int i = 0;
		int j = 0;
		int maxLength = 0;
		int length = 0;
		String subStr = "";
		int[] pos = new int[256];
		
		while (j < n){
			if (pos[s.charAt(j)] > 0){
				i = pos[s.charAt(j)];
				length = j - i + 1;
				pos = new int[256];
				for (int k = i; k <= j; k++){
					pos[s.charAt(k)] = k +1;
				}
			}
			else{
				pos[s.charAt(j)] = j + 1;
				length++;
				if (length > maxLength){
					maxLength = length;
					subStr = s.substring(i, j + 1);
				}
			}
			j++;			
		}

		return subStr;
	}
	
	String find2(String s){
		if( s == null || s.length() <=  1)
			return s;
		
		Hashtable<Character, Integer> charPosTable = new Hashtable<Character, Integer>();
		int startPos = 0; 
		int currPos = 0;
		int maxLength = 0;
		int maxStart = 0;
		int maxEnd = 0;
		
		while (currPos < s.length()){
			char c = s.charAt(currPos);
			if (charPosTable.containsKey(c)){
				int prevPos = charPosTable.get(c);
				for (int i = startPos; i < prevPos; i++){
					charPosTable.remove(s.charAt(i));
				}
				startPos = prevPos + 1;
				charPosTable.put(c, currPos);
			}
			else{
				charPosTable.put(c, currPos);
				if(charPosTable.size() > maxLength){
					maxStart = startPos;
					maxEnd = currPos;
					maxLength = currPos - startPos + 1;
				}
			}
			
			currPos++;
		}		
				
		return s.substring(maxStart, maxEnd +1);
	}
	
	public void testFun(){
		System.out.println(find2("abcabcbb"));
		System.out.println(find2("bbbbbbbcbabbaaaccdedfcc"));
		System.out.println(find2("bbef"));
	}	
}
