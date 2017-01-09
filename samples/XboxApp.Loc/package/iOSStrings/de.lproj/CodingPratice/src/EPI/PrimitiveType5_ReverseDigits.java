package EPI;

import junit.framework.TestCase;

/* Reverse a integer digits. 314 --> 413.  
*/

public class PrimitiveType5_ReverseDigits extends TestCase {

	long reverse(long x) {
		long result = 0;
		boolean isNegative = x < 0;
		if (isNegative)
			x = -x;

		while (x > 0) {
			long d = x % 10;
			result *= 10;
			result += d;

			x /= 10;

		}

		return isNegative ? -result : result;
	}

	public void testFun() {
		assertTrue(reverse(12304) == 40321L);
		assertTrue(reverse(-1232304) == -4032321L);
	}

}