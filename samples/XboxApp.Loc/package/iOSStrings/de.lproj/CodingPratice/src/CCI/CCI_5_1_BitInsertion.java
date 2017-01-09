package CCI;

import junit.framework.TestCase;

//Given 32 bit number N, M, and 2 bit position, insert M to N in the from position i to j.
public class CCI_5_1_BitInsertion extends TestCase {
	
	void insertValue(int n, int m, int i, int j){
		print(n);
		print(m);
		
		int mask = ~0 << (j + 1) | ((1 << i) -1);
		int v = (n & mask) | ((m )& ~mask);
		
		print(v);		
	}
		
	void print(int k)
	{	
		System.out.println();
		System.out.println("The value is ");
		System.out.print( Integer.toString(k) + " -- " + Integer.toBinaryString(k));
	}			
		
	public void testFun(){
		insertValue(200, 10, 3, 6);
		insertValue(1456, 14, 3, 7);
		insertValue(6789, 1234, 6, 9);
	}
}
