
package CCI;

import junit.framework.TestCase;

//Write a function to add 2 numbers without using + or any arithmetic operators.
public class CCI_18_1_AddInteger extends TestCase {
		
	int add(int a, int b){		
		if (b == 0)
			return a;
		int s = a ^ b;
		int c = (a & b) << 1;
		return add(s, c);
	}
		
    public void testFun(){       	
    	assertTrue(1234+3458 == add(1234,3458));
    	assertTrue(1234-3458 == add(1234,-3458));
    	assertTrue(-1234-3458 == add(-1234,-3458));
	}
}