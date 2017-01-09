package EPI;
import junit.framework.TestCase;

/* Write a program that take a non-negative integer and return the closest integer with the same number of 1 bits.  
*/

public class PrimitiveType5_ClosestWithSame1Bits extends TestCase {
		
	int getClosedValueWithTheSameWeight(int v) {
		for (int i = 0; i < 63; i++) {
			if ((v & (1L <<i)) != (v & (1L << (i+ 1)))) {
				int mask = (1 << i) | (1 << i + 1);
				return v ^= mask;
			}
		}
		throw new IllegalArgumentException("all 0 or 1");
	}
	
	
	public void testFun(){	
		int v = 0b000110;
		System.out.println(Integer.toBinaryString(v));
		assertTrue(getClosedValueWithTheSameWeight(v) == 0b000101);		
	}
	
}