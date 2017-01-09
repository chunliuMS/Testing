package EPI;
import junit.framework.TestCase;
/* 
 * Write a program which takes as input an array of digits encoding a decimal number D and update the array to represent D + 1.
 * For example, if the input is {1, 2, 9, 9}, the output should be {1, 3, 0, 0}. 
*/
				
public class Arrays6_IncrementArbitraryPrecisionInteger extends TestCase {
		
	int[] increase(int[] a) {
		if (a == null || a.length == 0)
			return a;
		
		int carry = 0;
		a[a.length - 1] = a[a.length - 1] + 1;
		
		for (int i = a.length - 1; i >= 0; i--) {
			if (carry > 0 || a[i] >= 10) {
				a[i] += carry;
				if (a[i] >= 10) {
					carry = a[i] / 10;
					a[i] %= 10;
				}
				else 
					carry = 0;
			}
			else
				break;
		}
		
		if (carry > 0) {
			int[] b = new int[a.length + 1];
			b[0] = carry;
			for (int i = 0; i < a.length; i++){
				b[i+1] = a[i];				
			}
			
			a = b;
		}
		
		return a;
		
	}
	
	
	void print(int[] a) {
		for (int i : a){
			System.out.print(i);
			System.out.print("\t");
		}
		System.out.println();
	}
	
	
	public void testFun(){	
		int[] a = { 1, 2, 9, 9 };
		print(increase(a));	
		
		int[] b = { 9, 9 };
		print(increase(b));
		
		int[] c = { 29, 9 };
		print(increase(c));
	}
		
}