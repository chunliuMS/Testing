package EPI;
import junit.framework.TestCase;


/* The parity of a binary word is 1 if the number of 1s is odd, otherwise it's 0.
 * Write a function to compute the parity of a long integer.  
*/


public class PrimitiveType5_BitParity extends TestCase {
		
	
	int getParity(long v) {
		int result = 0;
		while (v > 0) {			
			result ^= (v & 1);
			v >>>= 1;
		}
		return result;
	}
	
	int getParity2(long v) {
		int result = 0;
		while (v > 0){
			result ^= 1;			
			v &= v - 1; //set the last 1 bit to 0.
		}
		
		return result;
	}
	
	int getParity3(long v) {		
		v ^= v >>> 32;
		v ^= v >>> 16;
		v ^= v >>> 8;
		v ^= v >>> 4;
		v ^= v >>> 2;
		v ^= v >>> 1;
		
		return (int) (v & 1);
	}
	
	public void testFun(){	
		long v = 0b1000111000111;
		assertTrue(getParity(v) == 1);
		assertTrue(getParity2(v) == 1);
		assertTrue(getParity3(v) == 1);
		
		v = 0b111111111111111111110;
		
		assertTrue(getParity(v) == 0);
		assertTrue(getParity2(v) == 0);
		assertTrue(getParity3(v) == 0);
		
	}

	
}