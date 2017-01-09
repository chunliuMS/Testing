
package CCI;

import junit.framework.TestCase;

//Write a function to compute the number of trailing 0s in n factorial.
public class CCI_17_4_0s_In_FactorialN extends TestCase {
		
	int getZerosInFactorial(int n){
		if (n < 0)
			return 0;
		
		int num = n;
		
		int count = 0;
		while (num >= 5){
			count += num / 5;
			num = num / 5;
		}
		
		System.out.println( n + " -->" + count);
		return count;
	}
	
		
    public void testFun(){    
    	getZerosInFactorial(5);
    	getZerosInFactorial(11);
    	getZerosInFactorial(15);
    	getZerosInFactorial(25);
    	getZerosInFactorial(250);
	}
}