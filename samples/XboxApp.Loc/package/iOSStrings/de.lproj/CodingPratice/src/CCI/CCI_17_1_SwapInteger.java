
package CCI;

import junit.framework.TestCase;
//Write a function to swap 2 integers in place.
public class CCI_17_1_SwapInteger extends TestCase {
		
	void swap(int a, int b){
		System.out.print("(" + a + "," + b + ") --> ");
		a = a + b;
		b = a - b;
		a = a - b;
		
		System.out.println("(" + a + "," + b + ")");
	}
	
	void swap2(int a, int b){
		System.out.print("(" + a + "," + b + ") --> ");
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		
		System.out.println("(" + a + "," + b + ")");
	}
	
    public void testFun(){       	
    	swap(123, 345);
    	swap2(1035, 3459);
	}
}
