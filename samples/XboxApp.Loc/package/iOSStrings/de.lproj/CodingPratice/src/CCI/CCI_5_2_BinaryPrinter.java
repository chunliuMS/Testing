package CCI;

import junit.framework.TestCase;

//Print a binary representation of a real number between 0 and 1.
public class CCI_5_2_BinaryPrinter extends TestCase {
	
	void convert(double m ){
		double n = m;
		if(n > 1.0 || n < 0) {
			System.out.println("Failed to convert number:" + n);
			return;
		}
		StringBuffer sb = new StringBuffer(".");
		while(n > 0 && sb.length() <= 32){
			n *= 2;
			if (n >= 1)
			{
				sb.append(1);
				n -= 1;
			}
			else
				sb.append(0);
		}
		System.out.println("Converted number:" + m + " to " + sb.toString());	
	}
		
	
		
	public void testFun(){
		convert(0.34567);
		convert(0.30045);
		convert(1.34);
		convert(0.9999876655);
	}
}
