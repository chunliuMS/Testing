package EPI;
import junit.framework.TestCase;

/* Given 2 positive integers, compute their quotient, using only the addition, subtraction, and shifting operations.  
*/

public class PrimitiveType5_Divide extends TestCase {
	
	long divide(long x, long y) {
		if ( x <= 0 || x <= 0 || x < y)
			return 0;
		
		int bits = 8 * 8;
		long yp = y << bits;
		long result = 0;
		
		while (x >= y) {
			while (x < yp) {
				yp >>>= 1;
				bits --;
			}
			
			result += 1L << bits;
			x -= yp;			
		}
		
		
		return result;
	}
	
		
	public void testFun(){	
		assertTrue(123L / 33L == divide(123L, 33L));
		assertTrue(123445L / 3344L == divide(123445L, 3344L));		
	}		
	
}