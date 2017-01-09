package Maths;

import junit.framework.TestCase;

//Array [1, 9, 9] represents 199, write a methods to increase the value and return it.
public class IncreaseArrayValue extends TestCase {
	
	public int[] increase(int[] a){
		if(a == null || a.length == 0)
			return a;
		
		int carry = 1;
		for (int i = a.length - 1; i >= 0; i--){
			a[i] += carry;
			if (a[i] >= 10){
				a[i] %= 10;
				carry = 1;
			}
			else 
				return a;
		}
		
		int[] ret = new int[a.length + 1];
		for(int i = 0; i < a.length; i++){
			ret[i + 1] = a[i];
		}		

		ret[0] = carry;
		return ret;
	}
	
	void print(int[]a){
		StringBuffer sb = new StringBuffer("(");
		for(int v : a)
			sb.append(v).append(" ");
		sb.append(")   -----   (");
		
		int[] b = increase(a);
		for(int v : b)
			sb.append(v).append(" ");
		sb.append(")");
		
		System.out.println(sb.toString());
	}
	
		
	public void testFun(){
		int[] a1 = {1, 9, 9};
		print(a1);
		
		int[] a2 = {9, 9, 9};
		print(a2);
		
		int[] a3 = {1, 8, 9};
		print(a3);
	}
}
