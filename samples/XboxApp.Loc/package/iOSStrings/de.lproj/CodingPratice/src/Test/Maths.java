package Test;

import junit.framework.TestCase;

public class Maths extends TestCase {
	
	int countNumOf2(int n){
		if(n == 0)
			return 0;
		
		if (n < 0)
			n *= -1;
		
		int digit = 0;
		int num = 10;
		while (n > num){
			digit++;
			num *= 10;
		}
		
		int count = 0;
		for(int d = 0; d <= digit; d++)
			count += countNumOf2(n, d);
		
		return count;
	}
	
	int countNumOf2(int n, int digit){
		int powOf10 = 1;
		int d = digit;
		while(d-- > 0)
			powOf10 *= 10;	
		
		int nextPowerOf10 = powOf10 * 10;
		int down = n - n % nextPowerOf10;
		int up = down + nextPowerOf10;
		
		int v = (n % nextPowerOf10) / powOf10;
		if (v < 2)
			return down / 10;
		else if (v > 2)
			return up / 10;
		else
			return  down / 10 + 1 + n % powOf10;
		
	}
	
	public void testFun(){		
		assertTrue(countNumOf2(5) == 1);
    	assertTrue(countNumOf2(15) == 2);
    	assertTrue(countNumOf2(25) == 9); 
	}
}
