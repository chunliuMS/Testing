
package CCI;

import junit.framework.TestCase;

//Given an array of integers, find the the contiguous sequence with the largest sum. Return the sum.
public class CCI_17_8_LargestSumInArray extends TestCase {
		
	int largestSequence(int a[]){
		if (a == null || a.length == 0)
			return 0;
		int max_sum = Integer.MIN_VALUE;
		int sum = 0;
		int start = 0;
		int count = 0;
		int max_start = 0;
		for (int i = 0; i < a.length; i++){
			sum += a[i];
			if (sum > max_sum){
				max_sum = sum;
				max_start = start;
				count = i - max_start + 1;
			}
			if (sum < 0){
				sum = 0;
				start = i + 1;					
			}
		}
		
		System.out.println("start: " + max_start + " count: " + count + " total: " + max_sum);
		return max_sum;
	}	
		
    public void testFun(){    
    	int[] a = { -1, 2, 3, -2, 4, -2, 5, -2, 6, -3, -20, 0, 2, -5, 4, 8, -15};
    	largestSequence(a);
	}
}