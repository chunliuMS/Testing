package EPI;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
/* 
 * Print out all the prime number between 0 to n. 
*/
				
public class Arrays6_AllPrimesToN extends TestCase {
		
	void printPrimes(int n) {		
		assertTrue(n >= 2);
		
		List<Integer> primes = new ArrayList<Integer>();
		
		boolean[] notPrimes = new boolean[n+1];
		notPrimes[0] = notPrimes[1] = true;
		
		for (int i = 2; i <= n; i++) {
			if(!notPrimes[i])
				primes.add(i);
			
			for (int j = i; j <= n; j += i)
				notPrimes[j] = true;
		}
		
		int num = 0;
		
		for (int v : primes) {
			System.out.print(String.valueOf(v) + " ");	
			num++;
			if (num % 50 == 0)
				System.out.println();
		}
	}
	
	
	public void testFun(){	
		printPrimes(1000);		
	}		
}