package P100Solutions;

import junit.framework.TestCase;

/*There are n houses built in a line, each of which contains some value in it. 
 * A thief is going to steal the maximal value in these houses, 
 * but he cannot steal in two adjacent houses because the owner of a stolen house will tell his two neighbors on the left and right side. 
 * What is the maximal stolen value?  
For example, if there are four houses with values {6, 1, 2, 7}, the maximal stolen value is 13 when the first and fourth houses are stolen  
*/

public class Maximum_stolen_goods extends TestCase {
	
	int maxGoods(int [] a) {
		if (a == null || a.length == 0)
			return 0;
		
		return getMax(a, 0);
	}
		
	 int getMax(int[] a, int pos) {
		 if (pos == a.length - 1)
			 return a[a.length -1];
		 if (pos >= a.length)
			 return 0;
		 
		 return Math.max(a[pos] + getMax(a, pos + 2), getMax(a, pos + 1));
	}
	 
	public void testFun(){	
		assertTrue(maxGoods(new int[] {6, 1, 2, 7}) == 13);
	}
	
}