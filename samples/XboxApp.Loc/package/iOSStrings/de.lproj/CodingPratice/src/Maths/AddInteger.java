package Maths;

import junit.framework.TestCase;

//implement an algorithm to add 2 numbers without using + or any arithmetic operations.
public class AddInteger extends TestCase {
	
	public int add(int a, int b){
		if (b == 0)
			return a;
		
		if (a == 0)
			return b;
		
		int sum = a ^ b;
		int carry = (a & b) << 1;
		return add(sum, carry);
	}
	
		
	public void testFun(){
		assertTrue(add( 12, 11) == 23);
		assertTrue(add( 1, 11) == 12);
		assertTrue(add( 111, 0) == 111);
	}
}
