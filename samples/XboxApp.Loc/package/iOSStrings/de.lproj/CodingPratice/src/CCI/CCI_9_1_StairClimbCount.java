package CCI;

import junit.framework.TestCase;

//A child is running up a staircase with n steps, and can hop either 1 step, 2 steps and 3 steps at a time.
//Implements a method to count how many possible ways the child can run up the stairs.
public class CCI_9_1_StairClimbCount extends TestCase {
	
    int countWays(int n){    	
    	if (n < 0)
    		return 0;
    	else if (n == 0)
    		return 1;
    	else 
    		return countWays(n -1) + countWays(n - 2) + countWays(n - 3);    		
    }
    
    int countWays(int n, int[] map){
    	if (n < 0 )
    		return 0;
    	else if (n == 0)
    		return 1;
    	else if (map[n] > 0)
    		return map[n];    	
    	else{
    		map[n] = countWays(n - 1, map) + countWays(n - 2, map) + countWays(n-3, map);
    		return map[n];
    	}
    		
    }
		
	public void testFun(){
		System.out.println(5 + " -- " + countWays(5));	
		System.out.println(15 + " -- " + countWays(15));
		long st = System.currentTimeMillis();
		System.out.println(32 + " -- " + countWays(32));
		long et = System.currentTimeMillis();		
		System.out.println("Time used to calculate 32 = " + (et - st));
		
		int[] map = new int[30];
		System.out.println(5 + " -- " + countWays(5, map));	
		System.out.println(15 + " -- " + countWays(15, map));
		map = new int[60];
		st = System.currentTimeMillis();
		System.out.println(32 + " -- " + countWays(32, map));
		et = System.currentTimeMillis();
		System.out.println("Time used to calculate 32 = " + (et - st));
	}
}
