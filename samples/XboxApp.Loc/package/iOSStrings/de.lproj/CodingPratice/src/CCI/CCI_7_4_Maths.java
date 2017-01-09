package CCI;

import junit.framework.TestCase;

//Write methods to implement multiply, subtract, and divide operation for integers.
//Use only the add operator.
public class CCI_7_4_Maths extends TestCase {
	
    int negate(int a){
    	if (a == 0)
    		return 0;
    	int s = (a > 0) ? -1 : 1;
    	int r = 0;
    	while (a != 0){
    		a += s;
    		r += s;
    	}
    	
    	return r;
    }
    
    int abs(int a){
    	if (a >= 0)
    		return a;
    	else
    		return negate(a);
    }
    
	int multiply(int a, int b){	
		int absa = abs(a);
		int absb = abs(b);
		
		int i = (absa >= absb) ? absa : absb;
		int j = (absa >= absb) ? absb : absa;
		
		int sum = 0;
		for (int k = 0; k <= j; k++){
			sum += i;
		}
		
		if (a > 0 && b < 0 || a < 0 && b > 0)
			sum = negate(sum);
		
    	return sum;
	}
    
    int subtract(int a, int b){
    	return a + negate(b);
    }
    
    int divide(int a, int b){
    	if (b == 0)
    		throw new java.lang.ArithmeticException("divided by 0");
    	int absa = abs(a);
		int absb = abs(b);
		
		int x = 0;
		while(absa > absb){
			absa = subtract(absa, absb);
			x++;
		}
		
		if (a > 0 && b < 0 || a < 0 && b > 0)
			x = negate(x);
		
    	return x;
    }  
    
    double power(double a, int b){
    	double v = Math.pow(a, b);
    	System.out.println("value == " + Double.toString(v));
    	
    	if (b == 0)
    		return 1;
    	double sum = a;
    	int k = abs(b);
    	for (int i = 1; i < k; i++) {
    		sum *= a;
    	}
    	
    	if (b > 0)
    		return sum;
    	else {
    		if (sum != 0)
    			return 1/sum;
    		else 
    			throw new java.lang.ArithmeticException("divided by 0");
    	}    
    	
    }
    
    double power2(double a, int b){
    	if (b == 0)
    		return 1;
    	if (b < 0){
    		double r = power2(a, -b);
    		if (r != 0)
    			return 1 / r;
    		else
    			return Double.POSITIVE_INFINITY;
    	}
    	else
    		return a * power2(a, --b);    	
    }
		
	public void testFun(){
		assertTrue(negate(-345) == 345);
		assertTrue(negate(1345) == -1345);
		assertTrue(subtract(1223, -3456) == 1223 + 3456);
		assertTrue(subtract(1223, 3456) == 1223 - 3456);
		assertTrue(divide(-34599, 2344) == -34599 / 2344);
		
		assertTrue(Math.abs(power(23.23, 6) - Math.pow(23.23, 6)) < 0.01);
		assertTrue(Math.abs(power2(23.23, -6) - Math.pow(23.23, -6)) < 0.01);
		assertTrue(Math.abs(power(23.23, 0) - Math.pow(23.23, 0)) < 0.01);
		assertTrue(Math.abs(power2(23.23, -1) - Math.pow(23.23, -1)) < 0.01);
		assertTrue(Math.abs(power(0, 6) - Math.pow(0, 6)) < 0.01);		
	}
}
