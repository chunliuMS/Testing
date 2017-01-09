package EPI;

import junit.framework.TestCase;
/* 
 * Write a program that takes an array of integers which presents the stock prices at sequential time periods and 
 * returns the maximum profits possible for maximum of 2 transactions. 
*/
				
public class Arrays6_BuySellStockTwice extends TestCase {
		
	int calProfits(int [] a) {
		if (a == null || a.length == 0)
			return 0;
		
		//calculate forward
		int[] profits = new int[a.length];
		int minimum = a[0];
		profits[0] = 0;
		
		for (int i = 1; i < a.length; i++) {
			profits[i] = Math.max(profits[i-1], a[i] - minimum);
			minimum = Math.min(minimum, a[i]);
		}
		
		int maxProfit = 0;
		
		//calculate backward		
		int maximum = a[a.length - 1];
		int maxBackward = 0;
		for (int i = a.length -1; i > 0; i--) {
			maxBackward = Math.max(maxBackward, maximum - a[i]);
			maxProfit = Math.max(maxProfit, maxBackward + profits[i -1]);
			maximum = Math.max(maximum, a[i]);
		}		
		
		return maxProfit;		
	}
	
	
	public void testFun(){	
		assertTrue(calProfits(new int[] {12,11,13,9,12,8,14,13,15}) == 10);		
	}		
}