package LeetCode;

import junit.framework.TestCase;

//Double square problem m = a*a + b*b.
public class LC010_DoubleSquare extends TestCase {
	
	void doubleSquare(int m){
		int k =(int) Math.sqrt( m / 2.0 );
		for (int i = 0; i <= k; i++){
			double j = Math.sqrt((double)m - i*i);
			if (Math.abs(j - (int)j) < 0.01){
				int l = (int) j;
				if (l * l + i *i == m)
					System.out.println(Integer.toString(i)+ "," + Integer.toString(l));
			}
		}
	}
	
	public void testFun(){
		System.out.println("Double square for 1024:");
		doubleSquare(1024);
		System.out.println("Double square for 20000:");
		doubleSquare(20000);
	}	
}
