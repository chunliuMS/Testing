package EPI;
import junit.framework.TestCase;

/* Given 2 positive integers, compute their quotient, using only the addition, subtraction, and shifting operations.  
*/

public class PrimitiveType5_Exponentiation extends TestCase {
		
	double power(double x, long y) {
		double result = 1.0d;
		if (y < 0) {
			y = -1 * y;
			x = 1.0d / x;
		}
		
		while (y > 0) {
			if ((y & 1L) > 0){
				result *= x;
			}
			
			y >>>= 1;
			x *= x;
		}
		
		return result;
	}
	
		
	public void testFun(){	
		assertTrue(Math.abs(325.7d * 325.7d * 325.7d - power(325.7d, 3L)) < 0.1d);		
	}

}