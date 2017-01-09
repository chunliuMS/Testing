package EPI;

import java.util.Random;

import junit.framework.TestCase;
/* 
 * Implement an algorithm that takes as input an array of distinct elements and a size and 
 * returns a subset of the given size of the array elements. All subsets should be equally likely. 
*/
				
public class Arrays6_SamplingOfflineData extends TestCase {
		
	int[] sampling(int [] a, int k) {
		if (a == null || a.length <= k)
			return null;
		
		Random gen = new Random();
		for (int i = 0; i < k; i++) {
			int j = gen.nextInt(a.length - i);
			int tem = a[j];
			a[j] = a[a.length - 1 - j];
			a[a.length - 1 - j] = tem;
		}
		
		return a;
	}
	
	
	public void testFun(){	
		int[] a = sampling(new int[] {1, 2, 3, 4, 5, 6}, 4);
		for (int i = 0; i < 4; i++){
			System.out.print(a[a.length - 1 - i]);
			System.out.print(" ");
		}
		
	}		
}