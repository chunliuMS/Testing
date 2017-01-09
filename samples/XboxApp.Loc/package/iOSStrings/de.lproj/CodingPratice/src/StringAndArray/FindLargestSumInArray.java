package StringAndArray;

import junit.framework.TestCase;

//Given an array of integers. Find the contiguous sequence with the largest sum. 
//Print out the sub-array and the sum.
public class FindLargestSumInArray extends TestCase {
	
	public int[] findLargestSum(int[]a){
		if (a == null){
			return a;
		}
		else if (a.length == 1)
		{
			return a;
		}
		
		int maxSum = Integer.MIN_VALUE;
		int maxStart = -1;
		int maxEnd = -1;
		
		int start = 0;
		int sum = 0;
		for (int i = 0; i < a.length; i++){
			sum += a[i];
			if (sum > maxSum){
				maxSum = sum;
				maxStart = start;
				maxEnd = i;				
			}
			
			if (sum < 0){
				sum = 0;
				start = i + 1;
			}			
		}
		
		int[] b = new int[maxEnd - maxStart + 1];
		System.arraycopy(a, maxStart, b, 0, b.length);
		return b;
		
	}
	
	private void print(int[] a){
		StringBuilder sb = new StringBuilder("{");
		int total = 0;
		for (int j = 0; j < a.length; j++){
			sb.append(Integer.toString(a[j])).append(" ");
			total += a[j];
		}
		sb.append("}");
		
		System.out.println("Maximum value is " + Integer.toString(total));
		System.out.println(sb.toString());
		
	}
	
	public void testFun(){
		int[] a = { -3, 2, -1, 1, 0, 3, -1, 0, 3, -5, 4, 2};
		int[] b = findLargestSum(a);
		print(b);
		
		int[] a1 = { 2, 3, -8, -1, 2, 4, -2, 3, -5, 4};
		int[] b1 = findLargestSum(a1);
		print(b1);		
		
	}
}
