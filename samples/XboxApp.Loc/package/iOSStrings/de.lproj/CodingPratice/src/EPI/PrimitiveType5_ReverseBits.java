package EPI;
import junit.framework.TestCase;

/* Reverse bits in an integer.  
*/

public class PrimitiveType5_ReverseBits extends TestCase {
		
	int reverse(int v) {
		int i = 0; 
		int j = 15;
		while (i < j) {
			if ((v & (1 << i)) != ( v & (1 << j))) {
				int mask = (1 << i) | (1 << j);
				v ^= mask;				
			}
			i++;
			j--;		
		}
		return v;
	}
	
	
	public void testFun(){	
		int v = 0b000110;
		System.out.println(Integer.toBinaryString(v));
		assertTrue(reverse(v) == 0b0110000000000000);		
	}
	
}