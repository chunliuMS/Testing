package EPI;
import junit.framework.TestCase;

/* Write a program that multiplies 2 nonnegative integers. The only operators you're allowed to use are: assignment, bitwise operators, equality checks.  
*/

public class PrimitiveType5_Multiply extends TestCase {
		
	long multiply(long a, long b) {
		if ( a <= 0 || b <= 0)
			return 0;
		
		long sum = 0;
		
		while (b != 0) {
			if ((b & 1)  != 0) {
				sum = add1(sum, a);
			}
			
			a <<= 1;
			b >>>= 1;
		}
		
		return sum;
	}
	
	long add(long a, long b) {
		long a1 = a; 
		long b1 = b;
		long carry = 0;
		long k = 1;
		long sum = 0;
		
		while (a1 > 0 || b1 > 0) {
			long ak = a & k;
			long bk = b & k;
			sum |= carry ^ ak ^ bk;
			carry = ak & bk | ak & carry | bk & carry;
			carry <<= 1;
			k <<= 1;
			
			a1 >>>= 1;
			b1 >>>= 1;			
		}
		
		return sum | carry;
	}
	
	long add1(long a, long b) {
		if (b == 0)
			return a;
		
		if (a == 0)
			return b;
		
		long sum = a ^ b;
		long carry = (a & b) << 1;
		
		return add1 (sum, carry);
	}
	
	
	public void testFun(){	
		assertTrue (110 * 77 == multiply(110L, 77L));	
		assertTrue(add1(123L, 345L)== 468L);
	}
	
}