package EPI;

import junit.framework.TestCase;
/* 
 * Write a program that takes an array of integers which presents the stock prices at sequential time periods and 
 * returns the maximum profits possible. 
*/
				
public class Arrays6_BuySellStockOnce extends TestCase {
		
	int calProfits(int [] a) {
		if (a == null || a.length == 0)
			return 0;
		
		int profits = 0;
		int minimum = a[0];
		
		for (int i = 1; i < a.length; i++) {
			if (a[i] > minimum) {
				int pro = a[i] - minimum;
				if (pro > profits)	
					profits = pro;
			} else if (a[i] < minimum) {
				minimum = a[i];
			}			
		}		
		
		return profits;		
	}
	
	
	public void testFun(){	
		assertTrue(calProfits(new int[] {310,315,275,295,260,270,290,230,255,250}) == 30);		
	}		
}