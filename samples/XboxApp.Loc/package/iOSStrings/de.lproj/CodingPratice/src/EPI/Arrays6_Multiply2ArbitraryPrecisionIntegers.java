package EPI;
import java.util.ArrayList;
import java.util.Collections;

import junit.framework.TestCase;
/* 
 * Multiple 2 array represented inegers and return the result.
 * For example, if the input is {1, 2} and {1, 0}, the output should be {1, 2, 0}. 
*/
				
public class Arrays6_Multiply2ArbitraryPrecisionIntegers extends TestCase {
		
	ArrayList<Integer> multiply(ArrayList<Integer> a, ArrayList<Integer> b) {
		if (a == null || a.size() == 0 || b == null || b.size() == 0)
			return null;
		Integer a0 = a.get(0);
		Integer b0 = b.get(0);
		
		boolean isNegative = false;
		if (a0 < 0 || b0 < 0) {
			if (a0 < 0 && b0 > 0 || a0 >0 && b0 < 0)
				isNegative = true;
			
			a0 = Math.abs(a0);
			b0 = Math.abs(b0);
		}
		
		Collections.reverse(a);
		Collections.reverse(b);;
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < a.size() + b.size(); i++)
			result.add(0);
		
       	for (int i = 0; i < b.size();  i++) {
			int bv = b.get(i);
			
			if (bv != 0) {
				int carry = 0;
				for (int j = 0; j < a.size(); j++) {
					
					int av = a.get(j);
					int r = result.get(i + j);
					int t = bv * av + carry + r;
					if (t >= 10) {
						carry = t / 10;
						result.set(i + j, t % 10);
					}
					else{
						carry = 0;
						result.set(i + j, t);
					}
				}
				if (carry > 0) {
					int r = result.get(i + a.size());
					result.set(i + a.size(), r + carry);
				}
					
			}		
			
		}
		
		if (isNegative) {
			Integer aa0 = a.get(0);
			aa0 = -aa0;
		}
		
		Collections.reverse(result);
		return result;
		
	}
	
	
	void print(ArrayList<Integer> a) {
		for (int i : a){
			System.out.print(i);
			//System.out.print("\t");
		}
		System.out.println();
	}
	
	ArrayList<Integer> getArray(int[] a) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int v : a) {
			al.add(v);
		}
		
		return al;
	}
	
	public void testFun(){	
		print(multiply(getArray(new int[]{1, 9, 3, 7, 0, 7,7,2, 1}), getArray(new int[] {7, 6, 1,8,3,8, 2,5,7,2,8,7})));
	}
		
}