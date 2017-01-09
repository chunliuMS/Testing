package StringAndArray;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;

import org.junit.Test;

//Given a list of words, write a program to find the longest word made of other words in the list
public class LongestWordMadeByWords {
	class LengthComparator implements Comparator<String>{
		@Override
		public int compare(String o1, String o2) {
			return o2.length() - o1.length();
		}		
	}
	public String find(String[] a)
	{
		Arrays.sort(a, new LengthComparator());
		Hashtable<String, Boolean>ht = new Hashtable<String, Boolean>();
		for(String s: a){
			ht.put(s, true);
		}
		for (int i = 0; i < a.length; i++){
			if (madeFromWords(a[i],true, ht)){
				return a[i];
			}
		}
		return null;
	}
	
	private boolean madeFromWords(String s, boolean original, Hashtable<String, Boolean>ht){
		if (!original && ht.containsKey(s)){
			return ht.get(s);
		}
			
		for(int i = 1; i < s.length() - 1; i++){
			String left = s.substring(0, i);
			if (madeFromWords(left, false, ht)){
				String right = s.substring(i);
				if (madeFromWords(right, false, ht)){
					if (!ht.contains(right))
						ht.put(right, true);
					return true;
				}
				else{
					ht.put(right, false);
				}
			}
			else
				ht.put(left, false);
			
		}
		return false;
	}
			
	@Test
	public void test() {
		String[] a = {"this", "is", "the", "longest", "word", "I", "like", "game", "Ilikethisgame", "thisissthelongestword"};							
		String s = find(a);
		
		System.out.print(s);
		
	}
}
