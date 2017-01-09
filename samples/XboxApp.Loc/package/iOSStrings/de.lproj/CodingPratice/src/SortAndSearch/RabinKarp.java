package SortAndSearch;
import static org.junit.Assert.*;
import org.junit.Test;


public class RabinKarp {		
	public class Algorithm {
		
		private static final int BASE = 13;
		public Algorithm(){				
		}
		
		public int search(String org, String s){
			char[] str = org.toCharArray();
			char[] sub = s.toCharArray();
			
			int size = sub.length;
			int hsub = hash(sub, 0, size);
			int hs = hash(str, 0, size);
			for (int i = 0; i < str.length - size; i++){
				if (hs == hsub){
					boolean match = true;
					for (int j = 0; j < size; j++){
						if (str[i  + j] != sub[j]){
							match = false;
							break;							
						}							
					}
					if (match)
						return i;
				}				
				hs = rollingHash(str, i, size, hs);
			}
			
			return -1;
		}
		
		private int hash(char[] str, int start, int length){
			int hash = 0;
			for (int i = start; i < start + length; i++ ){
				hash += BASE * str[i];
			}
			return hash;
		}
		
		private int rollingHash(char[] str, int start, int length, int oldHash){
			return oldHash + BASE * (str[start + length] - str[start]);
		}
				
	}	
	
	
	@Test
	public void test() {		
		Algorithm al = new Algorithm();
		int index = al.search("my first word", "first");
		assertTrue(index == 3);	
		
		assertTrue(al.search("my first word", "t w") == 7);
		
	}
}
