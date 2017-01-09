package LeetCode;

import junit.framework.TestCase;

//Given an function to write a character, design a route to print out an unsigned long in decimal
public class LC001_PutLong extends TestCase {
	void putlong(long v, StringBuffer sb){
		if (v == 0)
			return;
		putlong(v / 10, sb);
		sb.append(v % 10);
	}
	
	void putlong(long v){
		StringBuffer sb = new StringBuffer(Long.toString(v) + " -- ");
		putlong(v, sb);
		System.out.println(sb.toString());
	}
	
	void print(long v) {
		if (v < 0) {
			System.out.print('-');
			v = -v;
		}
		
		if (v  >= 10) {
			print (v/10);
			print (v % 10);
			
		} else {
			System.out.print(v);
		}		
	}
	
	void putlong2(long n) {
		if (n >= 10) {
			putlong2(n / 10);
			System.out.print(n % 10);
		}
		else
			System.out.print(n % 10);			
	}
	
	public void testFun(){
		/*putlong(123456);
		putlong(45678888);
		print(-12345678);*/
		putlong2(12345);
	}
}
