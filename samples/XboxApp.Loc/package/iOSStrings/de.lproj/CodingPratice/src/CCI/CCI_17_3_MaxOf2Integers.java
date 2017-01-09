
package CCI;

import junit.framework.TestCase;

//Write a function to finds the maximum of 2 numbers without using if-else or any other comparison operator.
public class CCI_17_3_MaxOf2Integers extends TestCase {
		
	int max(int a, int b){
		int k = a - b;
		k = k >> 31;
		k = k & 0x01;
		
		int j = 1 - k;
		
		return a * j + b * k;
	}	
		
    public void testFun(){    
    	assertTrue(max(1, 5) == 5);
    	assertTrue(max(31, 34) == 34);
    	assertTrue(max(-20, 5) == 5);
    	assertTrue(max(-20, -15) == -15);
	}
}