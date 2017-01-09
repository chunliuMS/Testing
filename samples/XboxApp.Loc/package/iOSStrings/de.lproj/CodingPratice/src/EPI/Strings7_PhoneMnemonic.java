package EPI;

import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;
/* 
 * Print out all the chars representatives of phone number. 
*/
				
public class Strings7_PhoneMnemonic extends TestCase {
	
	private static final String[] mapping = new String[] {"0",
														  "1",
														  "ABC",
														  "DEF",
														  "GHI",
														  "JKL",
														  "MNO",
														  "PQRS",
														  "TUV",
														  "WXYZ"};
		
	Set<String> populate(char[] number) {
		if (number == null || number.length <= 0)
			return null;
		
		Set<String> set = new HashSet<String> ();
		char[] pre = number.clone();
		
		populate(set, number, pre, 0);
		
		return set;
	}
	
	void populate(Set<String> set, char[] number, char [] pre, int index) {
		if (index == number.length) {
			set.add(String.valueOf(pre));
			return;
		}
		
		int d = number[index] - '0';
		String map = mapping[d];
		for (int i = 0; i < map.length(); i++) {
			pre[index] = map.charAt(i);
			populate(set, number, pre, index + 1);
		}
	}
	
	public void testFun(){	
		char[] number = {'1', '2', '2'};
		Set<String> set = populate(number);
		for (String v : set) {
			System.out.println(v);			
		}
	}		
}