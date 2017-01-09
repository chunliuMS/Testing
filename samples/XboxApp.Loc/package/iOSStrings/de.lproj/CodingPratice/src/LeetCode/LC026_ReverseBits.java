package LeetCode;

import junit.framework.TestCase;

//Reverse bits of an unsigned integer.

public class LC026_ReverseBits extends TestCase {
	int reverse(int n){
		System.out.print(Integer.toHexString(n));
		int low = 0x01;
		int hi = 1 << 31;
		
		for(int i = 0; i < 16; i++){
			int hiBit = n & hi;
			int lowBit = n & low;
			if ((hiBit > 0) != (lowBit > 0))
				n = n ^ (hi | low);
			low = low << 1;
			hi = hi >> 1;
		}
		
		System.out.print(" --> ");
		System.out.print(Integer.toHexString(n));
		System.out.println();
		
		return n;
	}
	
	
	public void testFun(){
		reverse((short)124);
		reverse((short)1809);
		reverse((short)7622);
	}	
}
