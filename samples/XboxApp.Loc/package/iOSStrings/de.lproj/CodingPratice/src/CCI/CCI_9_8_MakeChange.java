package CCI;

import junit.framework.TestCase; 

//Given an infinite number of quarters(25 cents), dimes(10 cents), nickels(5 cents) and pennies(1 cent), 
//write code to calculate the number of ways to representing n cents.
public class CCI_9_8_MakeChange extends TestCase {	
    public int makeChange(int n, int change){
    	int next = 0;
    	if(change == 25)
    		next = 10;
    	else if (change == 10)
    		next = 5;
    	else if (change == 5)
    		next = 1;
    	else
    		return 1;
    	
    	int ways = 0;
    	for (int i = 0; i * change <= n; i++){
    		ways += makeChange(n - i * change, next);
    	}
    	
    	return ways;
    }
    
    public void testFun(){
    	System.out.println(5 + " -- " + makeChange(5, 25));
    	System.out.println(10 + " -- " + makeChange(10, 25));
    	System.out.println(100 + " -- " + makeChange(100, 25));
	}
}
