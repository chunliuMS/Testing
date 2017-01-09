package EPI;
import junit.framework.TestCase;
/* 
 * Write a function to swap bit in an integer.  
*/

public class PrimitiveType5_SwapBits extends TestCase {
		
	long swap(long v, int i, int j) {
		if (i >= 0 && i < 64 && j >= 0 && j < 64) {
			if ((v & (1 << i)) != (v & (1 << j))) {
				v ^= (1 << i) | (1 << j);
			}
		}
		
		return v;
	}
	
	
	public void testFun(){	
		long v = 0b100011;
		assertTrue(swap(v, 1, 2) == 0b100101);		
	}
		
}