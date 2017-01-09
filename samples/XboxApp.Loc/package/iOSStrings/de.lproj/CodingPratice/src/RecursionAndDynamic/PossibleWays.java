package RecursionAndDynamic;

import junit.framework.TestCase;

//A child is running up a staircase with n steps and can hop 1, 2 or 3 steps at a time. 
//Implement a method to find the possible ways the child can run up the stairs.
public class PossibleWays extends TestCase {
	public long calculate(int n, long[] map){
		if (n < 0)
			return 0;
		else if (n == 0)
			return 1;
		else if (map[n] >  0)
			return map[n];
		else {
			map[n] = calculate(n - 1, map) + calculate(n - 2, map) + calculate(n - 3, map);
			return map[n];
		}		
	}
	
	public long getWays(int n){
		if (n <= 0)
			return 0;
		else{
			long[] map = new long[n+1];
			return calculate(n, map);
		}
	}
		
	public void testFun(){		
		for (int i = 0; i < 100; i++ )
			System.out.println("n = " + Integer.toString(i) + " total ways = " + getWays(i));
	}
}
