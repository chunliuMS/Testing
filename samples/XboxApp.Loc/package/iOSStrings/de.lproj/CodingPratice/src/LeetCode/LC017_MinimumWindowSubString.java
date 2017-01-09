package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import junit.framework.TestCase;

//Given a set T of characters and a string S, find the minimum widow in S which will contain 
//all the characters in T 
public class LC017_MinimumWindowSubString extends TestCase {
			
	/*
	 * Two pointers: one for window_left and one for window_right
	 * As moving to the right, we know which char is in T,
	 * store the index into an array so that left point can directly
	 * jump to next spot.
	 */
	public String minWindow(String S, String T) {
	    int tt = T.length();
	    // set up a hashmap for T and also keep track of occurance
	    HashMap<Character, Integer> needFind = new HashMap<Character, Integer>();
	    HashMap<Character, Integer> hasFound = new HashMap<Character, Integer>();
	    for (int i=0; i<tt; ++i) {
	        hasFound.put(T.charAt(i), 0);
	        if (needFind.containsKey(T.charAt(i))) {
	            needFind.put(T.charAt(i), needFind.get(T.charAt(i))+1);
	        } else {
	            needFind.put(T.charAt(i), 1);
	        }
	    }
	    // sliding window as needed
	    int right = 0, found = 0, left = 0;
	    ArrayList<Integer> nexts = new ArrayList<Integer>();
	    String window = "";
	    int winSize = Integer.MAX_VALUE;
	    while (right < S.length()) {
	        char c = S.charAt(right);
	        if (!needFind.containsKey(c)) {  // not a match
	            ++right;
	            continue;
	        }

	        nexts.add(right);
	        ++right;
	        hasFound.put(c, hasFound.get(c)+1);
	        if (hasFound.get(c) <= needFind.get(c))  ++found;

	        if (found >= tt) { // got a window
	            // move left?
	            char ll = S.charAt(nexts.get(left));
	            while (hasFound.get(ll) > needFind.get(ll)) {
	                hasFound.put(ll, hasFound.get(ll)-1);
	                ++left;
	                ll = S.charAt(nexts.get(left));
	            }
	            // smaller window?
	            if (right - nexts.get(left) < winSize) {
	                winSize = right - nexts.get(left);
	                window = S.substring(nexts.get(left), right);
	            }
	        }
	    }
	    System.out.println(window);
	    return window;	
	}	
	
	public String Find(String S, String T){
		if (S == null || T == null || S.length() == 0 || T.length() == 0)
			return null;
		
		Hashtable<Character, Integer> needsToFind = new Hashtable<Character, Integer>();
		Hashtable<Character, Integer> founded = new Hashtable<Character, Integer> ();
		for(int i = 0; i < T.length(); i++){
			char c = T.charAt(i);
			founded.put(c, 0);
			if (needsToFind.containsKey(c))
				needsToFind.put(c, needsToFind.get(c) + 1);
			else
				needsToFind.put(c, 1);
		}
		
		int totalFound = 0;
		int minLength = - 1;
		String minString = null;
		int left = 0;
		ArrayList<Integer> charPositions = new ArrayList<Integer>();
		
		for(int i = 0; i < S.length(); i++){
			char c = S.charAt(i);
			if(founded.contains(c)){
				charPositions.add(i);
				founded.put(c, founded.get(c) + 1);
				if(founded.get(c) <= needsToFind.get(c)){
					totalFound ++;
					if (totalFound == T.length()){ //found;
						minLength = i - left + 1;
						minString = S.substring(left, i + 1);
					}						
				}
				else{
					char cc = S.charAt(left);
					while (founded.get(cc) > needsToFind.get(cc)){
						charPositions.remove(0);
						left = charPositions.get(0);
						founded.put(cc, founded.get(cc) - 1);
						if (i - left + 1 < minLength){
							minLength = i - left + 1;
							minString = S.substring(left, i + 1);
						}
						
						cc = S.charAt(left);
					}					
				}
			}
		}
		
		return minString;
	}
	
	public void testFun(){
		minWindow("adobecodebanc", "abc");
	}	
}
