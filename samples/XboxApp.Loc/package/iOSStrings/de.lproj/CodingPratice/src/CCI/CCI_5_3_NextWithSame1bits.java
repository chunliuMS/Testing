package CCI;

import junit.framework.TestCase;

//Given a positive integer, print the next smallest and the largest number that 
//have the same number of 1 bits in their binary presentation.
public class CCI_5_3_NextWithSame1bits extends TestCase {
	
	int getNext(int n){
		print(n);
		if (n <= 0)
			return -1;
		
		int m = n;
		int c0 = 0;
		int c1 = 0;
		while (m > 0 && (m & 1) == 0){
			c0++;
			m = m >> 1;
		}
		
		while (m > 0 && (m & 1) > 0 ){
			c1++;
			m = m >> 1;
		}
		
		if (c0 + c1 == 31)
			return -1;
		
		int p = c0 + c1;
		n = n  | (1 << p);
		n = n & ~(1 << p - 1);
		n = n | ((1 << (c1 - 1)) - 1);			
		
		print(n);
		return n;
	}	
	
	int getPrev(int n){
		print(n);
		if (n <= 0)
			return -1;
		
		int m = n;
		int c0 = 0;
		int c1 = 0;
		while (m > 0 && (m & 1) == 1){
			c1++;
			m = m >> 1;
		}
		
		if (m == 0)
			return -1;
		
		while (m > 0 && (m & 1) == 0 ){
			c0++;
			m = m >> 1;
		}
		
		int p = c0 + c1;
		n &= (~0) << (p + 1);
		int mask = (1 << (c1 + 1)) - 1;
		n |= mask << (c0 - 1);
		
		print(n);
		return n;
	}	
	
	void print(int k)
	{	
		System.out.println();
		System.out.println("The value is ");
		System.out.print( Integer.toString(k) + " -- " + Integer.toBinaryString(k));
	}			
		
	public void testFun(){
		getNext(999);
		getPrev(999);		
	}
}
