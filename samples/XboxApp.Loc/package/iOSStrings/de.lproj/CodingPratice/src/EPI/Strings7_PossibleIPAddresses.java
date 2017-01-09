package EPI;

import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;
/* 
 * Print out all the chars representatives of phone number. 
*/
				
public class Strings7_PossibleIPAddresses extends TestCase {
	
		
	Set<String> possibleIpAddress(String str) {
		if (str == null || str.length() < 4)
			return null;
		
		Set<String> set = new HashSet<String> ();
		String[] nodes = new String[3];
		
		getIpAddress(set, str, nodes, 0, 0);
		
		return set;
	}
	
	void getIpAddress(Set<String> set, String str, String[] nodes, int sp, int index) {
		if (index == 3) {
			String ip = str.substring(sp);
			if (ip.length() > 0 && isValid(ip)) {
				set.add(nodes[0] + '.' + nodes[1] + "." + nodes[2] + "." + ip);
				return;
			}
		}
		else {
			for (int i = 0; i < 3; i++) {
				int ii = sp + i;
				if (ii < str.length()) {
					String ip = str.substring(sp, sp + i + 1);
					if (isValid(ip)) {
						nodes[index] = ip;
						getIpAddress(set, str, nodes, sp + i + 1, index + 1);
					}
				}
			}
		}
		
	}
	
	boolean isValid(String ip) {
		if (ip.length() > 3)
			return false;
		
		if (ip.startsWith("0") && ip.length() > 1)
			return false;
		
		int value = Integer.parseInt(ip);
		return value < 255 && value >= 0;
	}
	
	public void testFun(){	
		Set<String> set = possibleIpAddress("19216011");
		for (String v : set) {
			System.out.println(v);			
		}
	}		
}