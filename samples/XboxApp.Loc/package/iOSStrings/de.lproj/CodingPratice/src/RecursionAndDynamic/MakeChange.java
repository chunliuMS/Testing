package RecursionAndDynamic;

import junit.framework.TestCase;

//Given an infinite number of quarters (25 cents), dimes (10 cents), nickels(5 cents) and pennies (1 cent),
//write code to calculate the number of ways of representing n cents.
public class MakeChange extends TestCase {
	private int makeChange(int n, int denom){
		int nextDenom = 0;
		switch(denom){
		case 25:
			nextDenom = 10;
			break;
		case 10:
			nextDenom = 5;
			break;
		case 5:
			nextDenom = 1;
			break;
		case 1:
			return 1;		
		}
		
		int ways = 0;
		for(int i = 0; i * denom <= n; i++){
			ways += makeChange( n - i*denom, nextDenom);
		}
		
		return ways;
	}	
	
	int findWays(int sum, int[] changes, int index){
		if (sum == 0)
			return 1;
				
		int ways = 0;
		if (index == changes.length - 1) {//last one.
			if (sum % changes[index] == 0)
				return 1;
			else
				return 0;
		}
		else
		{
			for (int i = 0; i <= sum /changes[index]; i++)
			ways += findWays(sum - i * changes[index], changes, index + 1);
		}
		
		return ways;
	}
	
		
	public void testFun(){	
		System.out.println(makeChange(1, 25));
		System.out.println(makeChange(5, 25));
		System.out.println(makeChange(10, 25));
		System.out.println(makeChange(25, 25));
		System.out.println(makeChange(50, 25));
		System.out.println(makeChange(100, 25));
		
		int[] changes = {25, 10, 5, 2};
		System.out.println(findWays(10, changes, 0));
		System.out.println(findWays(17, changes, 0));		
	}
}
