
package CCI;

import junit.framework.TestCase;

//Write a method to count how may 2s from 0 to n.
public class CCI_18_4_Count2sFrom0ToN extends TestCase {
		
	int count2(int n, int d){		
		int powerOf10 = (int) Math.pow(10, d);
		int nextPowerOf10 = powerOf10 * 10;
		int right = n % powerOf10;
		
		int down = n - n % nextPowerOf10;
		int up = down + nextPowerOf10;
		
		int digit = (n / powerOf10) % 10;
		if (digit < 2)
			return down / 10;
		else if (digit == 2)
			return down / 10 + right  + 1;
		else
			return up / 10;
	}
	
	int count2(int n){
		int count = 0;
		int len = String.valueOf(n).length();
		for (int d = 0; d < len; d++)
			count += count2(n, d);
		return count;
	}
		
    public void testFun(){       	
    	assertTrue(count2(5) == 1);
    	assertTrue(count2(15) == 2);
    	assertTrue(count2(25) == 9);    	
	}
}