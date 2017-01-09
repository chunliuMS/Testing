
package CCI;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

import junit.framework.TestCase;

//Given a list of words, find the longest word made by other words in the list.
public class CCI_18_7_LongestWordMadeByWords extends TestCase {
	
	class LengthComparator implements Comparator<String>{		
		 public int compare(String s1, String s2){
			 return s2.length() - s1.length();
		 }
	}	
		
	String find(String[] words){		
		Arrays.sort(words, new LengthComparator());
		HashSet<String> dic = new HashSet<String>();
		for (String word : words)
			dic.add(word);
		
		for (int i = 0; i < words.length; i++){
			if (madeFromWords(words[i], dic, true))
				return words[i];
		}
		
		return null;
	}
	
	boolean madeFromWords(String word, HashSet<String> dic, boolean initialCall){
		if (!initialCall && dic.contains(word))
				return true;
			
		for(int i = 1; i < word.length() - 1; i++){
			String first = word.substring(0, i);
			if (dic.contains(first) && madeFromWords(word.substring(i), dic, false))
				return true;
		}
		return false;
	}
	
    public void testFun(){       	
    	String[] a = {"ilovethisfgame", "takeiteasy", "i", "love", "this", "game", "take", "it", "easy"};
    	assertTrue(find(a).equals("takeiteasy"));
	}
}