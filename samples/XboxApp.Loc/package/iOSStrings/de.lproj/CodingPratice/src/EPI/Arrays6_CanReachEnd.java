package EPI;

import junit.framework.TestCase;
/* 
 * Write a program that takes an array of integers where a[i] denotes the maximum you can advance from the index i,
 * and returns whether it's possible to reach the end. 
*/
				
public class Arrays6_CanReachEnd extends TestCase {
		
	boolean canReachEnd(int [] a, int i) {
		if (a == null)
			return false;
		
		if (i >= a.length - 1)
			return true;
		
		int steps = a[i];
		boolean canReach = false;
		for (int j = 1; j <= steps; j++)
			canReach |= canReachEnd(a, i + j);		
		
		return canReach;	
	}
	
	boolean canReachEnd(int[] a) {
		if (a == null)
			return false;
		
		int furthest = 0;
		
		for(int i = 0; i <= furthest && furthest < a.length - 1; i++) {
			furthest = Math.max(furthest, i + a[i]);
		}
		
		return furthest >= a.length - 1;
	}
	
	public void testFun(){	
		assertTrue(canReachEnd(new int[] {3,3,1,0,2,0,1}, 0) == true);
		assertTrue(canReachEnd(new int[] {3,2,0,0,2,0,1}, 0) == false);
		
		assertTrue(canReachEnd(new int[] {3,3,1,0,2,0,1}) == true);
		assertTrue(canReachEnd(new int[] {3,2,0,0,2,0,1}) == false);
	}		
}